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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.SchoolController;
import Controllers.StudentController;
import Entities.SchoolEntity;
import Entities.StudentEntity;
public class frmStudent extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private StudentController studentController;
	private SchoolController schoolController;
	private final String titleString = "Student Management";
	
	/**
	 * Create the frame.
	 * @param objConn 
	 */
	public frmStudent(Connection objConn){
		
		//1. Create Controller to delegate
		studentController = new StudentController(objConn);
		schoolController = new SchoolController(objConn);
		
		//2. Params for Presentation
		setTitle(titleString);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 826, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new GridLayout(8, 1, 5, 5));//(row, col)
		
		//3. Handling button Add a Student
		JButton btnAdd = new JButton ("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {				
				StudentEntity objStudent = new StudentEntity();
				
				//Ask user to provide info of Student				
				String msg = "Enter info student";
				try {
					objStudent = getUserInputOneNewStudent(msg);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(objStudent != null){
					boolean result = studentController.InsertAndUpdateData(objStudent, (byte) 0);
					if (result){
						JOptionPane.showMessageDialog(getParent(), "Successfully add a new student in DB",
														"Message", JOptionPane.INFORMATION_MESSAGE);
					}else{
						System.out.println("Fail to add: check");
					}
				}			
			}
		});
		panelMenu.add(btnAdd);
		
		//4. Handling button List All
		JButton btnListAll = new JButton("LIST ALL");
		btnListAll.setToolTipText("<html>\r\n<p>Note: To Edit or Delete a record.</p>\r\n<p>Click LIST ALL button > Right-click on desired record</p>\r\n</html>");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				DefaultTableModel dftModel;
				try {
					dftModel = studentController.GetDTMAll();
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
								public void actionPerformed(ActionEvent e) {
									//find the location of click
									int row = jtableData.rowAtPoint(evt.getPoint());
							        int col = jtableData.columnAtPoint(evt.getPoint());
							        if (row >= 0 && col >= 0) {
							        	String msg = "row=" +row + ", col=" + col;				        	
							        	String id = (String) ((Vector) vector.elementAt(row)).elementAt(0);
							        	msg += ", id = "+id;
							        	System.out.println(msg);
							
										//a. Create a pop-up panel to show the selected student info
							        	String titleEdit = "Edit a Student";
							        	PanelOneStudentInfo panelEditting;
									
										panelEditting = createPanelByFirstColumn(titleEdit, id);					        					        	
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelEditting, titleEdit, JOptionPane.OK_CANCEL_OPTION);
							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int studentID = panelEditting.getStudentID();
							    			String studentFullName = panelEditting.getStudentFullName();
							    			java.util.Date studentDOB = panelEditting.getStudentDOB();
							    			String studentAddress = panelEditting.getStudentAddress();
							    			int schoolID = panelEditting.getSchoolID();
							    			StudentEntity studentEdited = new StudentEntity(studentID, studentFullName, studentDOB, studentAddress, schoolID);
							    			//JOptionPane.showMessageDialog(getParent(), studentEdited.toString());
							    			
							    			//c. Update new info into db
							    			 boolean res = studentController.InsertAndUpdateData(studentEdited, (byte) 1);
							    			if(res){
							    				JOptionPane.showMessageDialog(getParent(), "Successfully update student info");
							    			}				    			
							    		}else{
							    			panelEditting.setVisible(true);
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled updating student info");
							    		}
							        }//if row & col selected are valid
								}//actionPerformed()				        		
				        	});//addActionListener of editItem JMenuItem
					        	
				        	
				        	//3.2 Delete menu
				        	JMenuItem deleteItem = new JMenuItem("DELETE");
				        	popupMenu.add(deleteItem);
				        	deleteItem.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {
									//a. find the location of click
									int row = jtableData.rowAtPoint(evt.getPoint());
							        int col = jtableData.columnAtPoint(evt.getPoint());
							        if (row >= 0 && col >= 0) {
							        	String msg = "row=" +row + ", col=" + col;				        	
							        	String id = (String) ((Vector) vector.elementAt(row)).elementAt(0);
							        	msg += ", id = "+id;
							        	System.out.println(msg);								
							   
										String title = "Are you sure to Delete this following record?";
							        	PanelOneStudentInfo panel;									
							        	panel = createPanelByFirstColumn(title, id);
							        	//disable all data
							        	panel.setEnableAllFields(false);
							      
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panel, title, JOptionPane.OK_CANCEL_OPTION);
							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int studentID = panel.getStudentID();
							    			//c. Update new info into db
							    			 boolean res;
											try {
												res = studentController.DeleteDataByID(Integer.toString(studentID));
												if(res){
													String msgSuccess = "Successfully delete StudentID: "+ studentID +" from DB";
													JOptionPane.showMessageDialog(null, msgSuccess);
												}else{
													String msgFailure = "Fail to delete StudentID: "+ studentID +" from DB.\n"
															+ "This student has been used in other relations ";
													JOptionPane.showMessageDialog(null, msgFailure, "Failure Message", JOptionPane.ERROR_MESSAGE);
												}
											} catch (SQLException e1) {
												e1.printStackTrace();
											}							    			
							    		}else{
							    			panel.setVisible(true);
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled the deletion");
							    		}										
									}//if row & col selected are valid
								}//actionPerformed()
					        });//addActionListener() of deleteItem JMenuItem
				        	
					    }//mouseClicked()				    			    
					});//addMouseListener() of JTable				
				}catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});	
		panelMenu.add(btnListAll);	
				
		//5. Handling button Search by Name
		JButton btnSearchStudent = new JButton("SEARCH BY NAME");
		btnSearchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1. Create a Panel for get String name
				JPanel panelSearchInput = new JPanel();
				panelSearchInput.add(new JLabel("Student Name: "));
				JTextField tfStudentName = new JTextField(30);
				panelSearchInput.add(tfStudentName);
				int opt = JOptionPane.showConfirmDialog(getParent(), panelSearchInput, 
						"Search for Name", JOptionPane.OK_CANCEL_OPTION);
				
				if(opt == JOptionPane.OK_OPTION){
					String seekingName = tfStudentName.getText();
					
					//2. Get ResultSet from db
					//3. Display
					DefaultTableModel dftModel;
					try {
						dftModel = studentController.GetDTMByName(seekingName);
					
						JPanel panelSearchResult = new JPanel(new BorderLayout());
						JTable jtbSearchResult = new JTable();
						jtbSearchResult.setModel(dftModel);
						
						panelSearchResult.add(new JLabel("Search Results: "), BorderLayout.NORTH);					
						JScrollPane scrollPane = new JScrollPane();
						panelSearchResult.add(scrollPane, BorderLayout.CENTER);
						scrollPane.setViewportView(jtbSearchResult);
						
						JOptionPane.showMessageDialog(getParent(), panelSearchResult,
								"Search Result", JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}								
			}
		});
		panelMenu.add(btnSearchStudent);
		
		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel(titleString);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTitle.add(lbTitle);
		
		JPanel panelRenderData = new JPanel();
		contentPane.add(panelRenderData, BorderLayout.CENTER);
		panelRenderData.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 636, 390);
		panelRenderData.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);		
	}

	/**
	 * Purpose: To obtain Entity from data user key in a form
	 * @param msg
	 * @return
	 * @throws SQLException
	 */
	protected StudentEntity getUserInputOneNewStudent(String msg) throws SQLException {
		StudentEntity resultStudent=null;
		
		//0. Get max studentID in database at the moment
		int maxID = studentController.getMaxID();
		System.out.println("maxID: " + maxID);
		
		//1. Create a Panel housing form input
		String title = "Add a Student";
		PanelOneStudentInfo panelAdd = new PanelOneStudentInfo(title);
		
		//2. Set next studentid
		int incrementValue = 1;
		panelAdd.setStudentID(maxID + incrementValue);		
		
		//3. Set list of School
		List<SchoolEntity> listSchools = schoolController.GetDataAll();
		panelAdd.setListSchoolsComboBox(listSchools);
		
		//4. User input data after setting data for JComboBox
		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAdd, msg, JOptionPane.OK_CANCEL_OPTION);
		
		//5. Receive data input
		if(resultInput == JOptionPane.OK_OPTION){
			int studentID = panelAdd.getStudentID();
			String studentFullName = panelAdd.getStudentFullName();
			Date studentDOB = panelAdd.getStudentDOB();
			String studentAddress = panelAdd.getStudentAddress();
			int schoolID = panelAdd.getSchoolID();
			resultStudent = new StudentEntity(studentID, studentFullName, studentDOB, studentAddress, schoolID);			
		}else{
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return resultStudent;
	}
	
	
	/**
	 * Purpose: Create a Panel render info of Entity based on its ID
	 * @param title
	 * @param id
	 * @return
	 */
	private PanelOneStudentInfo createPanelByFirstColumn(String title, String id) {
		
		// Filling data into the form about current StudentID    	
    	PanelOneStudentInfo panelEditting = new PanelOneStudentInfo(title);
    	
    	//c. Set list of School
		List<SchoolEntity> listSchools;
		try {
			listSchools = schoolController.GetDataAll();
			panelEditting.setListSchoolsComboBox(listSchools);				    		
	    	
	    	//a. Retrieve Student data from db
	    	StudentEntity studentSelected = (StudentEntity) studentController.GetDataById(id).get(0);
	    	//b. Fill into panelEditing
	    	panelEditting.setStudentID(studentSelected.getId());
	    	panelEditting.setStudentFullName(studentSelected.getFullname());
	    	panelEditting.setStudentDOB(studentSelected.getDob());
	    	panelEditting.setStudentAddress(studentSelected.getAddress());
	    	panelEditting.setSchool(studentSelected.getSchoolID());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return panelEditting;
	}
}
