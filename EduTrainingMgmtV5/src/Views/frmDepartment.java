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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controllers.DepartmentController;
import Controllers.LecturerController;
import Controllers.SchoolController;
import Entities.DepartmentEntity;
import Entities.LecturerEntity;
import Entities.SchoolEntity;
public class frmDepartment extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private DepartmentController departmentController;
	private SchoolController schoolController;
	private LecturerController lecturerController;
	private final String titleString = "Department Management";
	
	/**
	 * Create the frame.
	 * @param objConn 
	 */
	public frmDepartment(Connection objConn){
		
		//1. Create Controller to delegate
		departmentController = new DepartmentController(objConn);
		schoolController = new SchoolController(objConn);
		lecturerController = new LecturerController(objConn);
		
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
		
		//3. Handling button Add a Department
		JButton btnAdd = new JButton ("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {				
				DepartmentEntity objDept = new DepartmentEntity();
				
				//Ask user to provide info of Department				
				String msg = "Enter info Department";
				try {
					objDept = getUserInputOneNewDepartment(msg);
				
					if(objDept != null){
						boolean result;
						result = departmentController.InsertAndUpdateData(objDept, (byte) 0);					
						if (result){
							JOptionPane.showMessageDialog(getParent(), "Successfully add a new department in DB",
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
		
		//4. Handling button List All, Edit, and Delete
		JButton btnListAll = new JButton("LIST ALL");
		btnListAll.setToolTipText("<html>\r\n<p>Note: To Edit or Delete a record.</p>\r\n<p>Click LIST ALL button > Right-click on a\r\r\ndesired record</p>\r\n</html>");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				DefaultTableModel dftModel;
				try {
					dftModel = departmentController.GetDTMAll();
					jtableData.setModel(dftModel);
								
					//Add Listener to jtableData: //Ref: https://stackoverflow.com/questions/7350893/click-event-on-jtable-java
					//1.jtableData returns (row,col) in zero-based when being clicked
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
							        	String titleEdit = "Edit a Department";
							        	PanelOneDeptInfo panelEditting;
									
										panelEditting = createPanelByFirstColumn(titleEdit, id);					        					        	
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelEditting, titleEdit, JOptionPane.OK_CANCEL_OPTION);
							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iDeptID = panelEditting.getDeptID();
							    			String sDeptName = panelEditting.getDeptName();
							    			int iSchoolID = panelEditting.getSchoolID();
							    			int iDeptSupervisorID = panelEditting.getDeptSupervisorID();
							    		
							    			DepartmentEntity deptEdited = new DepartmentEntity(iDeptID, sDeptName, iSchoolID, iDeptSupervisorID);
							    			//JOptionPane.showMessageDialog(getParent(), studentEdited.toString());
							    			
							    			//c. Update new info into db
							    			 boolean res;
							    			 try {
												res = departmentController.InsertAndUpdateData(deptEdited, (byte) 1);
											
								    			if(res){
								    				JOptionPane.showMessageDialog(getParent(), "Successfully update department info", "Notification", JOptionPane.INFORMATION_MESSAGE);						    				
								    			}else{
								    				JOptionPane.showMessageDialog(getParent(), "Unsuccessfully update department info", "Error Message", JOptionPane.ERROR_MESSAGE);
								    			}
							    			 } catch (SQLException e) {												
												System.out.println("Dept view edit: " + e.getMessage());
												e.printStackTrace();
							    			 }								
							    		}else{
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled update Department info");
							    		}
							        } //if row & col selected from JTable are valid									
								}//actionPerformed()				        		
				        	});//addActionListener of editItem

					    	
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
							        	
							        	//a. Create a pop-up panel to show the selected student info
							        	String title = "Delete a Department";
							        	PanelOneDeptInfo panelDeleting;									
							        	panelDeleting = createPanelByFirstColumn(title, id);
							        	//disable all fields
							        	panelDeleting.setEnableAllFields(false);
							        	
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelDeleting, title, JOptionPane.OK_CANCEL_OPTION);
							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			int iDeptID = panelDeleting.getDeptID();
							    			String sDeptName = panelDeleting.getDeptName(); 			
							    			
							    			//c. Update new info into db
							    			boolean deleteResult;						
											try {
												deleteResult = departmentController.DeleteDataByID(Integer.toString(iDeptID));
												if(deleteResult){
													String msgSuccess = "Successfully delete DeptID: "+ iDeptID +" from DB";
													JOptionPane.showMessageDialog(null, msgSuccess, "Notification", JOptionPane.INFORMATION_MESSAGE);
												}else{
													String msgFailure = "Fail to delete DeptID: "+ iDeptID +" from DB.\n"
															+ "This department has been used in other relations ";
													JOptionPane.showMessageDialog(getParent(), msgFailure, "Error Message", JOptionPane.ERROR_MESSAGE);
												}
											} catch (SQLException e) {//DeleteDataByID()												
												e.printStackTrace();
											}																	
							    		}else{//if user cancelled
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled that deletion");
							    		}
							        } //if row & col selected from JTable are valid
								}//actionPerformed()				        		
				        	});//addActionListener()				        	
					    	
					    }//mouseClicked()
					});//addMouseListener() of JTable
					
				}catch (SQLException e1) {//GetDTMAll()
					e1.printStackTrace();
				}	
			}
		});	
		panelMenu.add(btnListAll);
	
	
		//5. Handling button Search by Name
		JButton btnSearchDept = new JButton("SEARCH BY NAME");
		btnSearchDept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1. Create a Panel for get String name
				JPanel panelSearchInput = new JPanel();
				panelSearchInput.add(new JLabel("Department Name: "));
				JTextField tfDeptName = new JTextField(30);
				panelSearchInput.add(tfDeptName);
				int opt = JOptionPane.showConfirmDialog(getParent(), panelSearchInput, 
						"Search for Name", JOptionPane.OK_CANCEL_OPTION);
				
				if(opt == JOptionPane.OK_OPTION){
					String seekingName = tfDeptName.getText();
					
					//2. Get ResultSet from db
					//3. Display
					DefaultTableModel dftModel;
					try {
						dftModel = departmentController.GetDTMByName(seekingName);
					
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
						System.out.println("Dept View search: " + e1.getMessage());
						e1.printStackTrace();
					}					
				}else{
					JOptionPane.showMessageDialog(null, "You've cancelled this query");
				}
			}
		});
		panelMenu.add(btnSearchDept);
		
		/*
		//6. Handling button Delete Button
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//1. Create a Panel for get StudentID to delete
				JPanel panelDept = new JPanel();
				JComboBox<DepartmentEntity> cb = new JComboBox<DepartmentEntity>();
				panelDept.add(new JLabel("Department:"));
				panelDept.add(cb);
				List<DepartmentEntity> listDepts;
				String msgError ="";
				try {
					listDepts = departmentController.GetDataAll();
								
					for(DepartmentEntity de : listDepts){
						cb.addItem(de);
					}
					
					//2. User select option
					//3. Delete from db
					int opt = JOptionPane.showConfirmDialog(getParent(), panelDept, 
							"Deleting Department", JOptionPane.OK_CANCEL_OPTION);
					
					if(opt == JOptionPane.OK_OPTION){
						DepartmentEntity deptSelected = (DepartmentEntity) cb.getSelectedItem();
						int id = deptSelected.getiDeptID();
						JOptionPane.showMessageDialog(null, "You choose to delete DeptID: " + id);
						
						boolean deleteResult;						
							deleteResult = departmentController.DeleteDataByID(Integer.toString(id));
							if(deleteResult){
								String msg = "Successfully delete DeptID: "+ deptSelected +" from DB";
								JOptionPane.showMessageDialog(null, msg);
							}else{
								msgError = "Fail to delete DeptID: "+ deptSelected +" from DB.\n"
										+ "This department has been used in other relations ";
								JOptionPane.showMessageDialog(getParent(), msgError, "Error Message", JOptionPane.ERROR_MESSAGE);
							}						
					}else{
						JOptionPane.showMessageDialog(getParent(), "You've cancelled that deletion");
					}
				} catch (SQLException e) {
					//e.printStackTrace();
					System.out.println("View says: " + msgError);
				}
			}
		});		
		panelMenu.add(btnDelete);
		 */
		
	
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
		scrollPane.setBounds(23, 11, 672, 414);
		panelRenderData.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);		
	}


	/**
	 * Purpose: To obtain Entity from data user key in a form
	 * @param msg
	 * @return DepartmentEntity
	 * @throws SQLException
	 */
	protected DepartmentEntity getUserInputOneNewDepartment(String msg) throws SQLException {
		DepartmentEntity resultDept=null;
		
		//0. Get max deptID in database at the moment
		int maxDeptID = departmentController.getMaxID();
		System.out.println("MAX deptID: " + maxDeptID);
		
		//1. Create a Panel housing form input
		String title = "Add a Department";
		PanelOneDeptInfo panelAddDeptment = new PanelOneDeptInfo(title);
		
		//2. Set next ID
		int incrementValue = 1;
		panelAddDeptment.setDeptID(maxDeptID + incrementValue);		
		
		//3. Set list of School
		List<SchoolEntity> listSchools = schoolController.GetDataAll();
		panelAddDeptment.setListSchoolsComboBox(listSchools);
		
		//4. Set list of Lecturer
		List<LecturerEntity> listLecturers = lecturerController.GetDataAll();
		panelAddDeptment.setListLecturersComboBox(listLecturers);
				
		//5. User input data after setting data for JComboBox
		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAddDeptment, msg, JOptionPane.OK_CANCEL_OPTION);
		
		//6. Receive data input
		if(resultInput == JOptionPane.OK_OPTION){
			int iDeptID = panelAddDeptment.getDeptID();
			String sDeptName = panelAddDeptment.getDeptName();
			int iSchoolID = panelAddDeptment.getSchoolID();
			int iDeptHeadID = panelAddDeptment.getDeptSupervisorID();			
			resultDept = new DepartmentEntity(iDeptID, sDeptName, iSchoolID, iDeptHeadID);//add values here
		}else{
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return resultDept;
	}
	

	/**
	 * Purpose: Create a Panel render info of Entity based on its ID
	 * @param title
	 * @param id
	 * @return PanelOneDeptInfo
	 */
	private PanelOneDeptInfo createPanelByFirstColumn(String title, String id) {
		
		// 1.Filling data into the form about current StudentID    	
    	PanelOneDeptInfo panelEditting = new PanelOneDeptInfo(title);
    	
    	//2. Set list of School
    	//3. Set list of DeptSupervisor 
		List<SchoolEntity> listSchools;
		List<LecturerEntity> listLecturers;
		try {
			listSchools = schoolController.GetDataAll();
			panelEditting.setListSchoolsComboBox(listSchools);
			
			listLecturers = lecturerController.GetDataAll();
			panelEditting.setListLecturersComboBox(listLecturers);
	    	
	    	//3a. Retrieve Student data from db
			DepartmentEntity deptSelected = (DepartmentEntity) departmentController.GetDataById(id).get(0);
			
	    	//3b. Fill into panelEditing
			panelEditting.setDeptID(deptSelected.getiDeptID());
			panelEditting.setDeptName(deptSelected.getsDeptName());
			panelEditting.setSchool(deptSelected.getiSchoolID());
			panelEditting.setDeptSupervisorID(deptSelected.getiDeptSupervisorID());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return panelEditting;
	}
}
