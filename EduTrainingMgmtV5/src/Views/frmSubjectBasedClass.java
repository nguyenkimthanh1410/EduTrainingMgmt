package Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controllers.LecturerController;
import Controllers.SubjectBasedClassController;
import Controllers.SubjectController;
import Entities.SubjectBasedClassEntity;
import Entities.LecturerEntity;
import Entities.SubjectEntity;

public class frmSubjectBasedClass extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private SubjectBasedClassController subjectBasedClassController;
	private SubjectController subjectController;
	private LecturerController lecturerController;	
	private final String titleString = "Subject-based Class";
	
	/**
	 * Create the frame.
	 * @param objConn 
	 */
	public frmSubjectBasedClass(Connection objConn){
		
		//1. Create Controller to delegate
		subjectBasedClassController = new SubjectBasedClassController(objConn);
		lecturerController = new LecturerController(objConn);
		subjectController = new SubjectController(objConn);		
		
		//2. Params for Presentation
		setTitle(titleString);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 871, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new GridLayout(8, 1, 5, 5));//(row, col)
		
		//3. Handling button Add a Lecturer-Subject Assignment
		JButton btnAdd = new JButton ("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {				
				SubjectBasedClassEntity objSubjectBasedClass = new SubjectBasedClassEntity();
				
				//Ask user to provide info of Department				
				String msg = "Add a new Subject-based Class";
				try {
					objSubjectBasedClass = getUserInputOneNewSubjectBasedClass(msg);
				
					if(objSubjectBasedClass != null){
						boolean result;
						result = subjectBasedClassController.InsertAndUpdateData(objSubjectBasedClass, (byte) 0);					
						if (result){
							JOptionPane.showMessageDialog(getParent(), "Successfully add a new Subject-based Class in DB",
															"Message", JOptionPane.INFORMATION_MESSAGE);
						}else{
							System.out.println("Fail to add -> check log");
						}
					}
				}catch (SQLException e) {
					System.out.println("View add says: " + e.getMessage());
					e.printStackTrace();
				}							
			}
		});
		panelMenu.add(btnAdd);
		
		
		//4. Handling button List ALL, EDIT, DELETE
		JButton btnListAll = new JButton("LIST ALL");
		btnListAll.setToolTipText("<html>\r\n<p>Note: To Edit or Delete a record.</p>\r\n<p>Click LIST ALL button > Right-click on a\r\r\ndesired record</p>\r\n</html>");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				DefaultTableModel dftModel;
				try {
					dftModel = subjectBasedClassController.GetDTMAll();
					jtableData.setModel(dftModel);
								
					//Add Listener to jtableData: //Ref: https://stackoverflow.com/questions/7350893/click-event-on-jtable-java
					//1. jtableData returns (row,col) in zero-based when being clicked
					//2. Map row value with StudentID in DataVector using elementAt()
					//3.1. Pop-up Editing Panel to let user edit info OR
					//3.2. Pop-up Deleting confirmation
					//4. Capture new data, and update to db
					Vector vector = dftModel.getDataVector();				
					jtableData.addMouseListener(new java.awt.event.MouseAdapter() {
					    @Override
					    public void mouseClicked(java.awt.event.MouseEvent evt) {
					    
					    	//3. Create JPopup and 2 JMenuItem for Edit and delete					      
				        	JPopupMenu popupMenu = new JPopupMenu("Contextual Dialog");
				        	jtableData.setComponentPopupMenu(popupMenu);
				        	
				        	//3.1. Editing
				        	JMenuItem editItem = new JMenuItem("EDIT");
				        	popupMenu.add(editItem);
				        	editItem.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {

							        int row = jtableData.rowAtPoint(evt.getPoint());
							        int col = jtableData.columnAtPoint(evt.getPoint());
							        if (row >= 0 && col >= 0) {
							        	String msg = "row=" +row + ", col=" + col;				        	
							        	String id = (String) ((Vector) vector.elementAt(row)).elementAt(0);
							        	msg += ", id = "+id;
							        	//JOptionPane.showMessageDialog(null, msg);
							        	
							        	//a. Create a pop-up panel to show the selected student info
							        	String title = "Edit a Subject-Based Class";
							        	PanelOneSubjectBasedClassInfo panelEditting;									
										panelEditting = createPanelByFirstColumn(title, id);
																				
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelEditting, msg, JOptionPane.OK_CANCEL_OPTION);							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iSubjectBasedCLassID = panelEditting.getSubjectBasedClassID();
							    			int iSubjectID = panelEditting.getSubjectID();				
							    			int iLecturerID = panelEditting.getLecturerID();
							    			String sNote = panelEditting.getNote();
							    			SubjectBasedClassEntity objEdited = 
							    				new SubjectBasedClassEntity(iSubjectBasedCLassID, iSubjectID, iLecturerID, sNote);
							    			
							    			//JOptionPane.showMessageDialog(getParent(), studentEdited.toString());
							    			
							    			//c. Update new info into db
							    			 boolean res;
							    			 try {
												res = subjectBasedClassController.InsertAndUpdateData(objEdited, (byte) 1);
											
								    			if(res){
								    				JOptionPane.showMessageDialog(getParent(), "Successfully update this Subject-Based Class info", 
								    						"Notification", JOptionPane.INFORMATION_MESSAGE);						    				
								    			}else{
								    				JOptionPane.showMessageDialog(getParent(), "Unsuccessfully update Subject-Based Class info", 
								    						"Error Message", JOptionPane.ERROR_MESSAGE);
								    			}
							    			 } catch (SQLException e) {
												
												System.out.println("Dept view edit: " + e.getMessage());
												e.printStackTrace();
							    			 }								
							    		}else{//if user cancelled update
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled update Department info");
							    		}	    		
							        }//if row & col selected from JTable are valid								
								}//actionPerformed()				        		
				        	});//addActionListener() of editItem JMenuItem
					    	
					    	
				        	//3.2 Delete menu
				        	JMenuItem deleteItem = new JMenuItem("DELETE");
				        	popupMenu.add(deleteItem);
				        	deleteItem.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent arg0) {
									int row = jtableData.rowAtPoint(evt.getPoint());
							        int col = jtableData.columnAtPoint(evt.getPoint());
							        if (row >= 0 && col >= 0) {
							        	String msg = "row=" +row + ", col=" + col;				        	
							        	String id = (String) ((Vector) vector.elementAt(row)).elementAt(0);
							        	msg += ", id = "+id;
							        	//JOptionPane.showMessageDialog(null, msg);

							        	//a. Create a pop-up panel to show the selected info
							        	String title = "Are you sure to Delete this following record?";
							        	PanelOneSubjectBasedClassInfo panelDeleting;									
							        	panelDeleting = createPanelByFirstColumn(title, id);
							        	//disable all fiels => for displaying only
							        	panelDeleting.setEnableAllFields(false);
							        	
							        	//b. User update data
							    		int resultInput = 
							    				JOptionPane.showConfirmDialog(getParent(), panelDeleting, title, JOptionPane.OK_CANCEL_OPTION);
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iSubjectBasedClassID = panelDeleting.getSubjectBasedClassID();
							    			//c. Update new info into db
							    			boolean deleteResult;						
											try {
												deleteResult = subjectBasedClassController.DeleteDataByID(Integer.toString(iSubjectBasedClassID));
												if(deleteResult){
													String msgSuccess = "Successfully delete ID: "+ iSubjectBasedClassID +" from DB";
													JOptionPane.showMessageDialog(null, msgSuccess, "Notification", JOptionPane.INFORMATION_MESSAGE);
												}else{
													String msgFailure = "Fail to delete ID: "+ iSubjectBasedClassID +" from DB.\n"
															+ "This department has been used in other relations ";
													JOptionPane.showMessageDialog(getParent(), msgFailure, "Error Message", JOptionPane.ERROR_MESSAGE);
												}
											} catch (SQLException e) {//DeleteDataByID()												
												e.printStackTrace();
											}																	
										}else{
											JOptionPane.showMessageDialog(getParent(), "You've cancelled that deletion");
										}									    		
							        }//if row & col selected from JTable are valid									
								}//actionPerformed()				        		
				        	});//addActionListener() of deleteItem JMenuItem
					        
					    }//mouseClicked()
					});//addMouseListener() of JTable
					
				}catch (SQLException e1) {//GetDTMAll()
					e1.printStackTrace();
				}	
			}
		});	
		panelMenu.add(btnListAll);
	
		
		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel(titleString);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTitle.add(lbTitle);
		
		JPanel panelRenderData = new JPanel();
		panelRenderData.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panelRenderData, BorderLayout.CENTER);
		panelRenderData.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 726, 414);
		panelRenderData.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);		
	}


	protected SubjectBasedClassEntity getUserInputOneNewSubjectBasedClass(String title) throws SQLException {
		SubjectBasedClassEntity result = null;
		
		//0. Get max deptID in database at the moment
		int maxID = subjectBasedClassController.getMaxID();
		System.out.println("maxID: " + maxID);
		
		//1. Create a Panel housing form input
		PanelOneSubjectBasedClassInfo panelAdd = new PanelOneSubjectBasedClassInfo(title);
		
		//2. Set next nextID
		int incrementValue = 1;
		panelAdd.setSubjectBasedClassID(maxID + incrementValue);		
		
		//3.1. Set list of Subjects
		List<SubjectEntity> listSubjects = subjectController.GetDataAll();
		panelAdd.setListSubjectsComboBox(listSubjects);
		//3.2. Set list of Lecturers
		List<LecturerEntity> listLecturers = lecturerController.GetDataAll();
		panelAdd.setListLecturersComboBox(listLecturers);
				
		//5. User input data after setting data for JComboBox
		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAdd, title, JOptionPane.OK_CANCEL_OPTION);
		
		//6. Receive data input
		if(resultInput == JOptionPane.OK_OPTION){
			int iSubjectBasedCLassID = panelAdd.getSubjectBasedClassID(); 
			int iSubjectID = panelAdd.getSubjectID();
			int iLecturerID = panelAdd.getLecturerID();	
			String sNote = panelAdd.getNote();
			result = new SubjectBasedClassEntity(iSubjectBasedCLassID, iSubjectID, iLecturerID, sNote);
		}else{
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}
	

	private PanelOneSubjectBasedClassInfo createPanelByFirstColumn(String title, String id) {
		
		// 1.Filling data into the form about current StudentID    	
		PanelOneSubjectBasedClassInfo panelEditting = new PanelOneSubjectBasedClassInfo(title);
    	
		//2. Set list of Subjects 
		//3. Set list of Lecturers    	
		List<SubjectEntity> listSubjects;
		List<LecturerEntity> listLecturers;		
		try {
			listSubjects = subjectController.GetDataAll();
			panelEditting.setListSubjectsComboBox(listSubjects);
			
			listLecturers = lecturerController.GetDataAll();
			panelEditting.setListLecturersComboBox(listLecturers);
	    	
	    	//3a. Retrieve data from db according to id
			SubjectBasedClassEntity objSelected = (SubjectBasedClassEntity) subjectBasedClassController.GetDataById(id).get(0);
			
	    	//3b. Fill into panelEditing
			panelEditting.setSubjectBasedClassID(objSelected.getiSubjectBasedClassID());
			panelEditting.setSubject(objSelected.getiSubjectID());			
			panelEditting.setLecturer(objSelected.getiLecturerID());
			panelEditting.setNote(objSelected.getsNote());
			
		} catch (SQLException e) {
			System.out.println("View SubjBasedCLass: "+ e.getMessage());
			e.printStackTrace();
		}
		return panelEditting;
	}
}
