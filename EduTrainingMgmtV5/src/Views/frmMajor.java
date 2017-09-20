package Views;

import java.awt.BorderLayout;
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
import Controllers.MajorController;
import Entities.DepartmentEntity;
import Entities.MajorEntity;
import java.awt.Font;

public class frmMajor extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private MajorController majorController;
	private DepartmentController departmentController;
	private JButton btnAdd;
	private JButton btnListAll;
	private JButton btnSearch;	
	private final String titleString = "Major Management";
	private JLabel lbTitle;

	
	/**
	 * Create the frame.
	 * 
	 * @param objConn
	 */
	public frmMajor(Connection objConn) {

		// 1. Create Controller to delegate
		majorController = new MajorController(objConn);
		departmentController = new DepartmentController(objConn);

		// 2. Params for Presentation
		setTitle(titleString);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 871, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new GridLayout(8, 1, 5, 5));// (row, col)

		// 3. Handling button Add a Major
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {				
				MajorEntity objMajor = new MajorEntity();
							
				String msg = "Enter info Major";
				try {
					objMajor = getUserInputOneNewMajor(msg);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(objMajor != null){
					boolean result = majorController.InsertAndUpdateData(objMajor, (byte) 0);
					if (result){
						JOptionPane.showMessageDialog(getParent(), "Successfully add a new major in DB",
														"Message", JOptionPane.INFORMATION_MESSAGE);
					}					
				}			
			}
		});
		panelMenu.add(btnAdd);

		// 4. Handling button List all Major
		btnListAll = new JButton("LIST ALL");
		btnListAll.setToolTipText("<html>\r\n<p>Note: To Edit or Delete a record.</p>\r\n<p>Click LIST ALL button > Right-click on a\r\r\ndesired record</p>\r\n</html>");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dftModel;
				try {
					dftModel = majorController.GetDTMAll();
					jtableData.setModel(dftModel);

					// Add Listener to jtableData: //Ref:
					// https://stackoverflow.com/questions/7350893/click-event-on-jtable-java
					// 1. jtableData returns (row,col) in zero-based when being clicked
					// 2. Map row value with MajorID in DataVector using elementAt()
					//3.1. Pop-up Editing Panel to let user edit info OR
					//3.2. Pop-up Deleting confirmation
					// 4. Capture new data, and update to db
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
										String msg = "row=" + row + ", col=" + col;
										String id = (String) ((Vector) vector.elementAt(row)).elementAt(0);
										msg += ", id = " + id;
										// JOptionPane.showMessageDialog(null, msg);

										// a. Create a pop-up panel to show the selected
										// Major info and its department it belongs to
										String titleEdit = "Edit a Major";
										PanelOneMajorInfo panelEditting;

										panelEditting = createPanelByFirstColumn(titleEdit, id);
										// b. User update data
										int resultInput = JOptionPane.showConfirmDialog(getParent(), panelEditting, msg,
												JOptionPane.OK_CANCEL_OPTION);

										if (resultInput == JOptionPane.OK_OPTION) {
											int MajorID = panelEditting.getMajorID();
											String MajorFullName = panelEditting.getMajorName();
											int DepartmentID = panelEditting.getiDepartment();
											MajorEntity MajorEdited = new MajorEntity(MajorID, MajorFullName, DepartmentID);
											// JOptionPane.showMessageDialog(getParent(),
											// MajorEdited.toString());

											// c. Update new info into db
											boolean res = majorController.InsertAndUpdateData(MajorEdited, (byte) 1);
											if (res) {
												JOptionPane.showMessageDialog(getParent(), "Successfully update Major info");
											}
										}else{
											panelEditting.setVisible(true);
											JOptionPane.showMessageDialog(getParent(), "You've cancelled updating Major");
										}
									}//if row & col selected from panelEditing are valid									
								}//actionPerformed()				        		
				        	});//addActionListener() of editItem

				        	
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
							        	
							        	PanelOneMajorInfo panelDeleting;
							        	panelDeleting = createPanelByFirstColumn(title, id);
										//disable all data fields => only for display
							        	panelDeleting.setEnableAllFields(false);
							        	
							        	// b. User update data
										int resultInput = JOptionPane.showConfirmDialog(getParent(), panelDeleting, title,
												JOptionPane.OK_CANCEL_OPTION);

										if (resultInput == JOptionPane.OK_OPTION) {
											int MajorID = panelDeleting.getMajorID();
											String MajorFullName = panelDeleting.getMajorName();
											
											// c. Update new info into db
											boolean deleteResult;
											try {
												deleteResult = majorController.DeleteDataByID(Integer.toString(MajorID));
												if (deleteResult) {
													String msgSuccess = "Successfully delete MajorID: " + MajorID + " from DB";
													JOptionPane.showMessageDialog(null, msgSuccess);
												} else {
													String messageFailure = "Fail to delete Major ID: " + MajorID + " from DB.\n "
															+ "as this major has been used in other relations";
													JOptionPane.showMessageDialog(null, messageFailure, "Error Message", JOptionPane.ERROR_MESSAGE);
												}
											} catch (SQLException e) {//DeleteDataByID()											
												e.printStackTrace();
											}											
										}else{
											panelDeleting.setVisible(true);
							    			JOptionPane.showMessageDialog(getParent(), "You've cancelled the deletion");
										}
							        }//if row & col selected are valid in deleteItem									
								}//actionPerformed()				        		
				        	});//addActionListener() of deleteItem							
							
						}//mouseClicked()
					});//addMouseListener() of JTable

				} catch (SQLException e1) {//GetDTMAll()
					e1.printStackTrace();
				}
			}
		});
		panelMenu.add(btnListAll);
		

		// 5. Handling button Search by Name
		btnSearch = new JButton("SEARCH BY NAME");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Create a Panel for get String name
				JPanel panelSearchInput = new JPanel();
				panelSearchInput.add(new JLabel("Major Name: "));
				JTextField tfMajortName = new JTextField(30);
				panelSearchInput.add(tfMajortName);
				int opt = JOptionPane.showConfirmDialog(getParent(), panelSearchInput, "Search for Name",
						JOptionPane.OK_CANCEL_OPTION);

				if (opt == JOptionPane.OK_OPTION) {
					String seekingName = tfMajortName.getText();

					DefaultTableModel dftModel;

					try {
						dftModel = majorController.GetDTMByName(seekingName);

						JPanel panelSearchResult = new JPanel(new BorderLayout());
						JTable jtbSearchResult = new JTable();
						jtbSearchResult.setModel(dftModel);

						panelSearchResult.add(new JLabel("Search Results: "), BorderLayout.NORTH);
						JScrollPane scrollPane = new JScrollPane();
						panelSearchResult.add(scrollPane, BorderLayout.CENTER);
						scrollPane.setViewportView(jtbSearchResult);

						JOptionPane.showMessageDialog(getParent(), panelSearchResult, "Search Result",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(getParent(), "You've cancelled the Search query");
				}
			}
		});
		panelMenu.add(btnSearch);

		/*
		// 6. Handling button Delete Button
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 1. Create a Panel for get MajorID to delete
				JPanel panelMajor = new JPanel();
				JComboBox<MajorEntity> cb = new JComboBox<MajorEntity>();
				panelMajor.add(new JLabel("Major:"));
				panelMajor.add(cb);
				List<MajorEntity> listMajors;
				try {
					listMajors = majorController.GetDataAll();

					for (MajorEntity s : listMajors) {
						cb.addItem(s);
					}
					int opt = JOptionPane.showConfirmDialog(getParent(), panelMajor, "Deleting Major",
							JOptionPane.OK_CANCEL_OPTION);

					if (opt == JOptionPane.OK_OPTION) {
						MajorEntity majorselected = (MajorEntity) cb.getSelectedItem();
						int id = majorselected.getiMajorID();
						JOptionPane.showMessageDialog(null, "You choose to delete MajorID: " + id);
						boolean deleteResult;

						deleteResult = majorController.DeleteDataByID(Integer.toString(id));
						if (deleteResult) {
							String msg = "Successfully delete MajorID: " + majorselected + " from DB";
							JOptionPane.showMessageDialog(null, msg);
						} else {
							String message = "Fail to delete" + majorselected + "from DB.\n "
									+ "as this major has been used in other relations";
							JOptionPane.showMessageDialog(null, message);
						}
					}else{
						JOptionPane.showMessageDialog(getParent(), "You've cancelled the deletion transaction");
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

		panelMenu.add(btnDelete);
		 */
		
		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		
		lbTitle = new JLabel(titleString);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTitle.add(lbTitle);

		JPanel panelRenderData = new JPanel();
		contentPane.add(panelRenderData, BorderLayout.CENTER);
		panelRenderData.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 678, 427);
		panelRenderData.add(scrollPane);

		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);
	}
	
	/**
	 * Purpose: Get MajorEntity object which is created by data user input into a form
	 * @param msg
	 * @return MajorEntity
	 * @throws SQLException
	 */
	protected MajorEntity getUserInputOneNewMajor(String msg) throws SQLException {
		MajorEntity resultMajor = null;

		int maxID = majorController.getMaxID();
		System.out.println("maxID: " + maxID);

		String title = "Add a Major";
		PanelOneMajorInfo panelAdd = new PanelOneMajorInfo(title);

		int incrementValue = 1;
		panelAdd.setNextMajorID(maxID + incrementValue);
		
		List<DepartmentEntity> listDepartments = departmentController.GetDataAll();		
		//System.out.println("View Major: " + listDepartments.size());
		
		panelAdd.setListDepartmentComboBox(listDepartments);
		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAdd, msg, JOptionPane.OK_CANCEL_OPTION);

		if (resultInput == JOptionPane.OK_OPTION) {
			int iMajorID = panelAdd.getMajorID();
			String sMajorName = panelAdd.getMajorName();
			int iDepartmentID = panelAdd.getiDepartment();
			resultMajor = new MajorEntity(iMajorID, sMajorName, iDepartmentID);
		} else {
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return resultMajor;
	}

	/**
	 * Purpose: Create a Panel holding data of a MajorEntity
	 * @param title
	 * @param MajorID
	 * @return PanelOneMajorInfo
	 */
	private PanelOneMajorInfo createPanelByFirstColumn(String title, String MajorID) {

		PanelOneMajorInfo panelEditting = new PanelOneMajorInfo(title);

		List<DepartmentEntity> listDepartment;
		try {
			listDepartment = departmentController.GetDataAll();
			panelEditting.setListDepartmentComboBox(listDepartment);
			
			//Get MajorEntity from majorID
			MajorEntity majorSelected = (MajorEntity) majorController.GetDataById(MajorID).get(0);
			// b. Fill into panelEditing
			panelEditting.setMajorID(majorSelected.getiMajorID());
			panelEditting.setMajorName(majorSelected.getsMajorName());
			panelEditting.setDepartmentID(majorSelected.getiDepartmentID());
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return panelEditting;
	}
	}
