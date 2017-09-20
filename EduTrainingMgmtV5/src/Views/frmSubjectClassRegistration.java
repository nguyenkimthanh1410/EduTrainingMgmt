package Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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

import BaseUltils.IOFIles.DateConverter;
import Controllers.StudentController;
import Controllers.SubjectBasedClassController;
import Controllers.SubjectClassRegistrationController;
import Entities.StudentEntity;
import Entities.SubjectBasedClassEntity;
import Entities.SubjectClassRegistrationEntity;

public class frmSubjectClassRegistration extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private SubjectClassRegistrationController subjectClassRegistrationController;
	private SubjectBasedClassController subjectBasedClassController;
	private StudentController studentController;		
	//private final String titleString = "Subject-based Class Registration";
	private final String titleString = "Student Class Registration";
	
	/**
	 * Create the frame.
	 * @param objConn 
	 */
	public frmSubjectClassRegistration(Connection objConn){
		
		//1. Create Controller to delegate
		subjectClassRegistrationController = new SubjectClassRegistrationController(objConn);
		subjectBasedClassController = new SubjectBasedClassController(objConn);
		studentController = new StudentController(objConn);		
		
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
				SubjectClassRegistrationEntity objInserted = new SubjectClassRegistrationEntity();
				
				//Ask user to provide info of Department				
				//String msg = "Add a new Subject-Class Registration";
				String msg = "Add a new Student Class Registration";
				try {
					objInserted = getUserInputOneNewSubjectClassRegistration(msg);
				
					if(objInserted != null){
						boolean result;
						result = subjectClassRegistrationController.InsertAndUpdateData(objInserted, (byte) 0);					
						if (result){
							JOptionPane.showMessageDialog(getParent(), "Successfully add a new Subject Class Registration in DB",
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
					dftModel = subjectClassRegistrationController.GetDTMAll();
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
							        	
							        	//a. Create a pop-up panel to show the selected obj
							        	String title = "Edit a Subject Class Registration";
							        	PanelOneSubjectClassRegistrationInfo panelEditting;									
										panelEditting = createPanelByFirstColumn(title, id);
																				
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelEditting, title, JOptionPane.OK_CANCEL_OPTION);							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iSubjectClassRegistrationID = panelEditting.getSubjectClassRegistrationID();
							    			int iSubjectBasedClassID = panelEditting.getSubjectBasedClassID();
							    			int iStudentID = panelEditting.getStudentID();							    			
							    			Date dRegistrationDate = panelEditting.getRegistrationDate();
							    			String sNote = panelEditting.getNote();				    			
							    			
							    			SubjectClassRegistrationEntity objEdited = 
							    					new SubjectClassRegistrationEntity(iSubjectClassRegistrationID, 
							    									iSubjectBasedClassID, iStudentID, dRegistrationDate, sNote);
							    										   
							    			//c. Update new info into db
							    			 boolean res;
							    			 try {
												res = subjectClassRegistrationController.InsertAndUpdateData(objEdited, (byte) 1);
											
								    			if(res){
								    				JOptionPane.showMessageDialog(getParent(), "Successfully update this Major-Subject info", 
								    						"Notification", JOptionPane.INFORMATION_MESSAGE);						    				
								    			}else{
								    				JOptionPane.showMessageDialog(getParent(), "Unsuccessfully update Major-Subject info", 
								    						"Error Message", JOptionPane.ERROR_MESSAGE);
								    			}
							    			 } catch (SQLException e) {
												
												System.out.println("MS view edit: " + e.getMessage());
												e.printStackTrace();
							    			 }								
							    		}else{//if user cancelled update
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled update Major-Subject info");
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
							        	PanelOneSubjectClassRegistrationInfo panelDeleting;									
							        	panelDeleting = createPanelByFirstColumn(title, id);
							        	//disable all fiels => for displaying only
							        	panelDeleting.setEnableAllFields(false);
							        	
							        	//b. User update data
							    		int resultInput = 
							    				JOptionPane.showConfirmDialog(getParent(), panelDeleting, title, JOptionPane.OK_CANCEL_OPTION);
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iSubjClassRegisIDSelected = panelDeleting.getSubjectClassRegistrationID();
							    			//c. Update new info into db
							    			boolean deleteResult;						
											try {
												deleteResult = subjectClassRegistrationController.DeleteDataByID(Integer.toString(iSubjClassRegisIDSelected));
												if(deleteResult){
													String msgSuccess = "Successfully delete ID: "+ iSubjClassRegisIDSelected +" from DB";
													JOptionPane.showMessageDialog(null, msgSuccess, "Notification", JOptionPane.INFORMATION_MESSAGE);
												}else{
													String msgFailure = "Fail to delete ID: "+ iSubjClassRegisIDSelected +" from DB.\n"
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


	protected SubjectClassRegistrationEntity getUserInputOneNewSubjectClassRegistration(String title) throws SQLException {
		SubjectClassRegistrationEntity result = null;
		
		//0. Get max deptID in database at the moment
		int maxID = subjectClassRegistrationController.getMaxID();
		System.out.println("maxID: here: " + maxID);
		
		//1. Create a Panel housing form input
		PanelOneSubjectClassRegistrationInfo panelAdd = new PanelOneSubjectClassRegistrationInfo(title);
		
		//2. Set next nextID
		int incrementValue = 1;
		panelAdd.setSubjectClassRegistrationID(maxID + incrementValue);		
		
		//3.1. Set list of SubjectBasedClassEntity
		List<SubjectBasedClassEntity> listSubjectBasedClass = subjectBasedClassController.GetDataAll();
		System.out.println("listSubjectBasedClass: "+ listSubjectBasedClass.size());
		
		System.out.println("List of object SBC:");
		for(int i = 0; i< listSubjectBasedClass.size(); i++ ){
			System.out.println(listSubjectBasedClass.get(i).getiSubjectBasedClassID());
		}
		
		
		panelAdd.setListSubjectBasedClassComboBox(listSubjectBasedClass);
		//3.2. Set list of Students
		List<StudentEntity> listStudents = studentController.GetDataAll();
		panelAdd.setListStudentsComboBox(listStudents);
		
		//4. User input data after setting data for JComboBox
		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAdd, title, JOptionPane.OK_CANCEL_OPTION);
		
		//5. Receive data input
		if(resultInput == JOptionPane.OK_OPTION){
			int iSubjectClassRegistrationID = panelAdd.getSubjectClassRegistrationID();
			int iSubjectBasedClassID = panelAdd.getSubjectBasedClassID();
			int iStudentID = panelAdd.getStudentID();							    			
			Date dRegistrationDate = panelAdd.getRegistrationDate();
			String sNote = panelAdd.getNote();				    			
			result = new SubjectClassRegistrationEntity(iSubjectClassRegistrationID, 
								iSubjectBasedClassID, iStudentID, dRegistrationDate, sNote);
		}else{
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}
	

	private PanelOneSubjectClassRegistrationInfo createPanelByFirstColumn(String title, String id) {
		
		// 1.Filling data into the form about current StudentID    	
		PanelOneSubjectClassRegistrationInfo panelEditting = new PanelOneSubjectClassRegistrationInfo(title);
    	
		//2. Set list of Majors
		//3. Set list of Subjects
		List<SubjectBasedClassEntity> listSubjectBasedClass;
		List<StudentEntity> listStudents;				
		try {
			listSubjectBasedClass = subjectBasedClassController.GetDataAll();
			panelEditting.setListSubjectBasedClassComboBox(listSubjectBasedClass);
	    	
			listStudents = studentController.GetDataAll();
			panelEditting.setListStudentsComboBox(listStudents);
			
			
	    	//3a. Retrieve data from db according to id
			SubjectClassRegistrationEntity objSelected = 
					(SubjectClassRegistrationEntity) subjectClassRegistrationController.GetDataById(id).get(0);
			
	    	//3b. Fill into panelEditing
			panelEditting.setSubjectClassRegistrationID(objSelected.getiSubjectClassRegistrationID());
			panelEditting.setSubjectBasedClass(objSelected.getiSubjectBasedClassID());
			panelEditting.setStudent(objSelected.getiStudentID());
			
			String format= "yyyy-MM-dd";
			Date dateSelected = objSelected.getdRegistrationDate();
			String sDate = DateConverter.convertDateToString(dateSelected, format);
			panelEditting.setRegistrationDate(sDate);
			
			panelEditting.setNote(objSelected.getsNote());
			
		} catch (SQLException e) {
			System.out.println("View SubjectClassRegistration: "+ e.getMessage());
			e.printStackTrace();
		}
		return panelEditting;
	}
}
