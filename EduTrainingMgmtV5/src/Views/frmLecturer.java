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
import Entities.DepartmentEntity;
import Entities.LecturerEntity;

public class frmLecturer extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private DepartmentController departmentController;
	private LecturerController lecturerController;
	private final String titleString = "Lecturer Management";
	
	/**
	 * Create the frame.
	 * @param objConn 
	 */
	public frmLecturer(Connection objConn){
		
		//1. Create Controller to delegate
		departmentController = new DepartmentController(objConn);
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
				LecturerEntity objLec = new LecturerEntity();
				
				//Ask user to provide info of Lecturer				
				String msg = "Enter info Lecturer";
				try {
					objLec = getUserInputOneNewLecturer(msg);
				
					if(objLec != null){
						boolean result;
						result = lecturerController.InsertAndUpdateData(objLec, (byte) 0);					
						if (result){
							JOptionPane.showMessageDialog(getParent(), "Successfully add a new Lecturer in DB",
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
		
		//4. Handling button List all Student
		JButton btnListAll = new JButton("LIST ALL");
		btnListAll.setToolTipText("<html>\r\n<p>Note: To Edit or Delete a record.</p>\r\n<p>Click LIST ALL button > Right-click on a desired record</p>\r\n</html>");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				DefaultTableModel dftModel;
				try {
					dftModel = lecturerController.GetDTMAll();
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
							        
							           	//a. Create a pop-up panel to show the selected student info
							        	String titleEdit = "Edit a Lecturer";
							        	PanelOneLecturerInfo panelEditting;
									
										panelEditting = createPanelByFirstColumn(titleEdit, id);					        					        	
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelEditting, msg, JOptionPane.OK_CANCEL_OPTION);
							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			//Add value when editing
							    			int iLecturerID = panelEditting.getLecturerID();
							    			String sLecturerName = panelEditting.getLecturerName();
							    			String sSSN = panelEditting.getSSN();
							    			String sAddress = panelEditting.getAddress();
							    			int iLecturerManager = panelEditting.getManagedBy();
							    			int iDeptID = panelEditting.getDepartmentID();					    			
							    			LecturerEntity elementEdited = new LecturerEntity(iLecturerID, sLecturerName, sSSN, sAddress, iLecturerManager, iDeptID);
							    			
							    			//c. Update new info into db
							    			 boolean res;
							    			 try {
												res = lecturerController.InsertAndUpdateData(elementEdited, (byte) 1);
											
								    			if(res){
								    				JOptionPane.showMessageDialog(getParent(), "Successfully update Lecturer info", "Notification", JOptionPane.INFORMATION_MESSAGE);						    				
								    			}else{
								    				JOptionPane.showMessageDialog(getParent(), "Unsuccessfully update Lecturer info", "Error Message", JOptionPane.ERROR_MESSAGE);
								    			}
							    			 } catch (SQLException e) {										
												System.out.println("Lecturer view edit: " + e.getMessage());
												e.printStackTrace();
							    			 }								
							    		}else{
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled update Lecturer info");
							    		}
							        }//if row & col clicked on JTable are valid									
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
							        
							        	//a. Create a pop-up panel to show the selected info
							        	String title = "Are you sure to Delete this following record?";
							        	PanelOneLecturerInfo panelDeleting;									
										panelDeleting = createPanelByFirstColumn(title, id);
										//disable all fields
										panelDeleting.setEnableAllFields(false);
										
							        	//b. User update data
							    		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelDeleting, title, JOptionPane.OK_CANCEL_OPTION);
							    		
							    		if(resultInput == JOptionPane.OK_OPTION){
							    			//Add value when editing
							    			int iLecturerID = panelDeleting.getLecturerID();
							    			String sLecturerName = panelDeleting.getLecturerName();							    										    			
							    			//c. Update new info into db
							    			boolean deleteResult;						
											try {
												deleteResult = lecturerController.DeleteDataByID(Integer.toString(iLecturerID));
												if(deleteResult){
													String msgSuccess = "Successfully delete Lecturer: "+ iLecturerID +" from DB";
													JOptionPane.showMessageDialog(null, msgSuccess);
												}else{
													String msgError = "Fail to delete Lecturer ID: "+ iLecturerID +" from DB.\n"
															+ "This Lecturer has been used in other relations ";
													JOptionPane.showMessageDialog(getParent(), msgError, "Error Message", JOptionPane.ERROR_MESSAGE);
												}												
											} catch (SQLException e) {//DeleteDataByID()											
												e.printStackTrace();
											}																			
							    		}else{
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled that deletion");
							    		}
							        }//if row & col selected are valid in deleteItem
																		
								}//actionPerformed()				        		
				        	});//addActionListener() of deleteItem			        	
				        	
					    }//mouseClicked()
					});//addMouseListener of jtableData 
					
				}catch (SQLException e1) {//GetDTMAll()
					e1.printStackTrace();
				}	
			}//end actionPerformed() for ListAll btn
		});	//end addActionListener() for ListAll btn
		panelMenu.add(btnListAll);
	
	
		//5. Handling button Search by Name
		JButton btnSearch = new JButton("SEARCH BY NAME");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1. Create a Panel for get String name
				JPanel panelSearchInput = new JPanel();
				panelSearchInput.add(new JLabel("Lecturer Name: "));
				JTextField tfLecturerName = new JTextField(30);
				panelSearchInput.add(tfLecturerName);
				int opt = JOptionPane.showConfirmDialog(getParent(), panelSearchInput, 
						"Search for Name", JOptionPane.OK_CANCEL_OPTION);
				
				if(opt == JOptionPane.OK_OPTION){
					String seekingName = tfLecturerName.getText();
					
					//2. Get ResultSet from db
					//3. Display
					DefaultTableModel dftModel;
					try {
						dftModel = lecturerController.GetDTMByName(seekingName);
					
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
						System.out.println("Lecturer View search: " + e1.getMessage());
						e1.printStackTrace();
					}					
				}else{
					JOptionPane.showMessageDialog(null, "You've cancelled this query");
				}
			}
		});
		panelMenu.add(btnSearch);
		
		
	/*
		//6. Handling button Delete Button
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//1. Create a Panel for get StudentID to delete
				JPanel panel = new JPanel();
				JComboBox<LecturerEntity> cb = new JComboBox<LecturerEntity>();
				panel.add(new JLabel("Lecturer:"));
				panel.add(cb);
				List<LecturerEntity> listLecturers;
				String msgError ="";
				try {
					listLecturers = lecturerController.GetDataAll();
								
					for(LecturerEntity le : listLecturers){
						cb.addItem(le);
					}
					
					//2. User select option
					//3. Delete from db
					int opt = JOptionPane.showConfirmDialog(getParent(), panel, 
							"Deleting Lecturer", JOptionPane.OK_CANCEL_OPTION);
					
					if(opt == JOptionPane.OK_OPTION){
						LecturerEntity lecturerSelected = (LecturerEntity) cb.getSelectedItem();
						int id = lecturerSelected.getiLecturerID();
						JOptionPane.showMessageDialog(null, "You choose to delete LecturerID: " + id);
						
						boolean deleteResult;						
							deleteResult = lecturerController.DeleteDataByID(Integer.toString(id));
							if(deleteResult){
								String msg = "Successfully delete Lecturer: "+ lecturerSelected +" from DB";
								JOptionPane.showMessageDialog(null, msg);
							}else{
								msgError = "Fail to delete Lecturer ID: "+ lecturerSelected +" from DB.\n"
										+ "This Lecturer has been used in other relations ";
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
		contentPane.add(panelRenderData, BorderLayout.CENTER);
		panelRenderData.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 678, 426);
		panelRenderData.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);		
	}

	/**
	 * Purpose: To obtain Entity from data user key in a form
	 * @param msg
	 * @return LecturerEntity
	 * @throws SQLException
	 */
	protected LecturerEntity getUserInputOneNewLecturer(String msg) throws SQLException {
		LecturerEntity result=null;
		
		//0. Get max deptID in database at the moment
		int maxLecturerID = lecturerController.getMaxID();
		System.out.println("MAX LecturerID: " + maxLecturerID);
		
		//1. Create a Panel housing form input
		String title = "Add a Lecturer";
		PanelOneLecturerInfo panelAdd = new PanelOneLecturerInfo(title);
		
		//2. Set next ID
		int incrementValue = 1;
		panelAdd.setLecturerID(maxLecturerID + incrementValue);		
		
		//3. Set list of Lecturer
		List<LecturerEntity> listLecturers = lecturerController.GetDataAll();
		panelAdd.setListLecturersComboBox(listLecturers);
		
		//4. Set list of Department
		List<DepartmentEntity> listDepts = departmentController.GetDataAll();
		panelAdd.setListDepartmentsComboBox(listDepts);
						
		//5. User input data after setting data for JComboBox
		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAdd, msg, JOptionPane.OK_CANCEL_OPTION);
		
		//6. Receive data input
		if(resultInput == JOptionPane.OK_OPTION){
			int iLecturerID = panelAdd.getLecturerID();
			String sLecturerName = panelAdd.getLecturerName();
			String sSSN = panelAdd.getSSN() ; 
			String sAddress = panelAdd.getAddress();
			int iLecturerManager = panelAdd.getManagedBy();
			int iDeptID = panelAdd.getDepartmentID();			
			result = new LecturerEntity(iLecturerID, sLecturerName, sSSN, sAddress, iLecturerManager, iDeptID);
		}else{
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}
	
	/**
	 * Purpose: Create a Panel render info of Entity based on its ID
	 * @param title
	 * @param id
	 * @return PanelOneLecturerInfo
	 */
	private PanelOneLecturerInfo createPanelByFirstColumn(String title, String id) {
		
		// 1.Filling data into the form about current StudentID    	
    	PanelOneLecturerInfo panel = new PanelOneLecturerInfo(title);
    	
    	//2. Set list of Lecturers
    	//3. Set list of Departments 
    	List<LecturerEntity> listLecturers;
    	List<DepartmentEntity> listDepartments;
		
		try {
			listLecturers = lecturerController.GetDataAll();
			panel.setListLecturersComboBox(listLecturers);
	    	
			listDepartments = departmentController.GetDataAll();
			panel.setListDepartmentsComboBox(listDepartments);
			
			
	    	//3a. Retrieve Student data from db
			LecturerEntity selected = (LecturerEntity) lecturerController.GetDataById(id).get(0);
			
	    	//3b. Fill into panelEditing
			panel.setLecturerID(selected.getiLecturerID());
			panel.setLecturerName(selected.getsLecturerName());
			panel.setSSN(selected.getsSSN());
			panel.setAddress(selected.getsAddress());
			panel.setManagedBy(selected.getiLecturerManager());
			panel.setDepartment(selected.getiDeptID());			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return panel;
	}
}
