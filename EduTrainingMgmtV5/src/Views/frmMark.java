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

import Controllers.ExamController;
import Controllers.MarkController;
import Controllers.SubjectClassRegistrationController;
import Entities.ExamEntity;
import Entities.MarkEntity;
import Entities.SubjectClassRegistrationEntity;

public class frmMark extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private MarkController markController;
	private ExamController examController;
	private SubjectClassRegistrationController subjectClassRegistrationController;
	
	private final String titleString = "Mark Management";
	
	/**
	 * Create the frame.
	 * @param objConn 
	 */
	public frmMark(Connection objConn){
		
		//1. Create Controller to delegate
		markController = new MarkController(objConn);
		examController = new ExamController(objConn);
		subjectClassRegistrationController = new SubjectClassRegistrationController(objConn);
		
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
				MarkEntity objMark = new MarkEntity();
				
				//Ask user to provide info of Department				
				String title = "Add a new Mark";
				try {
					objMark = getUserInputOneMark(title);
				
					if(objMark != null){//valid Mark
						boolean result;
						result = markController.InsertAndUpdateData(objMark, (byte) 0);					
						if (result){
							JOptionPane.showMessageDialog(getParent(), "Successfully add a new Mark for Student in Subject-based class in an Exam",
															"Message", JOptionPane.INFORMATION_MESSAGE);
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
					dftModel = markController.GetDTMAll();
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
							        	String title = "Edit a Mark";
							        	PanelOneMarkInfo panel;
									
										panel = createPanelByFirstColumn(title, id);					        					        	
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panel, title, JOptionPane.OK_CANCEL_OPTION);
							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iMarkID = panel.getMarkID();
							    			int iExamID = panel.getExamID();
							    			int iSubjectClassRegistrationID = panel.getSubjectClassRegistrationID();
							    			float mark = panel.getMark();			
							    			MarkEntity objEdited = new MarkEntity(iMarkID, iExamID, iSubjectClassRegistrationID, mark);							    			
							    			
							    			//c. Update new info into db
							    			boolean res;
							    			try {
							    				res = markController.InsertAndUpdateData(objEdited, (byte) 1);
											
								    			if(res){
								    				JOptionPane.showMessageDialog(getParent(), "Successfully update this Mark info", 
								    						"Notification", JOptionPane.INFORMATION_MESSAGE);						    				
								    			}else{
								    				JOptionPane.showMessageDialog(getParent(), "Unsuccessfully update Mark info", 
								    						"Error Message", JOptionPane.ERROR_MESSAGE);
								    			}
							    			 } catch (SQLException e) {
												
												System.out.println("Dept view edit: " + e.getMessage());
												e.printStackTrace();
							    			 }								
							    		}else{//if user cancelled update
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled update Mark info");
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
							        	PanelOneMarkInfo panelDeleting;									
							        	panelDeleting = createPanelByFirstColumn(title, id);
							        	//disable all fiels => for displaying only
							        	panelDeleting.setEnableAllFields(false);
							        	
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelDeleting, title, JOptionPane.OK_CANCEL_OPTION);
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iMarkID = panelDeleting.getMarkID();
							    			//c. Update new info into db
							    			boolean deleteResult;						
											try {
												deleteResult = markController.DeleteDataByID(Integer.toString(iMarkID));
												if(deleteResult){
													String msgSuccess = "Successfully delete ID: "+ iMarkID +" from DB";
													JOptionPane.showMessageDialog(null, msgSuccess, "Notification", JOptionPane.INFORMATION_MESSAGE);
												}else{
													String msgFailure = "Fail to delete ID: "+ iMarkID +" from DB.\n"
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


	protected MarkEntity getUserInputOneMark(String title) throws SQLException {
		MarkEntity result=null;
		
		//0. Get max deptID in database at the moment
		int maxID = markController.getMaxID();
		System.out.println("maxID: " + maxID);
		
		//1. Create a Panel housing form input
		PanelOneMarkInfo panelAdd = new PanelOneMarkInfo(title);
		
		//2. Set next nextID
		int incrementValue = 1;
		panelAdd.setMarkID(maxID + incrementValue);		
		
		//3. Set list of Exams
		List<ExamEntity> listExams = examController.GetDataAll();
		panelAdd.setListExamsComboBox(listExams);
		
		//4. Set list of SubjectClassRegistration
		List<SubjectClassRegistrationEntity> listSubjectClassRegistrations = subjectClassRegistrationController.GetDataAll();
		panelAdd.setListSubjectClassRegistrationComboBox(listSubjectClassRegistrations);
				
		//5. User input data after setting data for JComboBox
		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAdd, title, JOptionPane.OK_CANCEL_OPTION);
		
		//6. Receive data input
		if(resultInput == JOptionPane.OK_OPTION){
			int iMarkID = panelAdd.getMarkID();
			int iExamID = panelAdd.getExamID();
			int iSubjectClassRegistrationID = panelAdd.getSubjectClassRegistrationID();
			float mark = panelAdd.getMark();			
			result = new MarkEntity(iMarkID, iExamID, iSubjectClassRegistrationID, mark);
		}else{
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}
	

	private PanelOneMarkInfo createPanelByFirstColumn(String title, String id) {
		
		// 1.Filling data into the form about current StudentID    	
		PanelOneMarkInfo panel = new PanelOneMarkInfo(title);
    	
    	//2. Set list of Exams
    	//3. Set list of SubjectClassRegistration 
		List<ExamEntity> listExams;
		List<SubjectClassRegistrationEntity> listSubjectClassRegistrations;
		
		try {
			listExams = examController.GetDataAll();
			panel.setListExamsComboBox(listExams);
	    	
			listSubjectClassRegistrations = subjectClassRegistrationController.GetDataAll();
			panel.setListSubjectClassRegistrationComboBox(listSubjectClassRegistrations);
			
			
	    	//3a. Retrieve Student data from db
			MarkEntity objSelected = (MarkEntity) markController.GetDataById(id).get(0);
			
	    	//3b. Fill into panel
			panel.setMarkID(objSelected.getiMarkID());
			panel.setExam(objSelected.getiExamID());
			panel.setSubjectClassRegistration(objSelected.getiSubjectClassRegistrationID());
			panel.setMark(objSelected.getMark());
			
		} catch (SQLException e) {
			System.out.println("View Mark: "+ e.getMessage());
			e.printStackTrace();
		}
		return panel;
	}
}
