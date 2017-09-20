package Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
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
import Controllers.SubjectController;
import Entities.SubjectEntity;

public class frmSubject extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private SubjectController subjectController;	
	private JButton btnAdd;
	private JButton btnListAll;
	private JButton btnSearch;
	private final String titleString = "Subject Management";
	private JLabel lbTitle;
	
	/**
	 * Create the frame.
	 * 
	 * @param objConn
	 */
	public frmSubject(Connection objConn) {

		// 1. Create Controller to delegate
		subjectController = new SubjectController(objConn);
		
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

		// 3. Handling button Add
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {				
				SubjectEntity objSubject = new SubjectEntity();							
				String msg = "Enter info Subject";
				try {
					objSubject = getUserInputOneNewSubject(msg);
					if(objSubject != null){
						boolean result = subjectController.InsertAndUpdateData(objSubject, (byte) 0);
						if (result){
							JOptionPane.showMessageDialog(getParent(), "Successfully add a new major in DB",
															"Notification", JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(getParent(), "Unsuccessfully add a new major in DB",
									"Error Message", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException e) {//catch Exception when retrieving data from DB update form input
					e.printStackTrace();
				}							
			}
		});
		panelMenu.add(btnAdd);
		

		// 4. Handling button List All, Edit, Delete
		btnListAll = new JButton("LIST ALL");
		btnListAll.setToolTipText("<html>\r\n<p>Note: To Edit or Delete a record.</p>\r\n<p>Click LIST ALL button > Right-click on a\r\r\ndesired record</p>\r\n</html>");
		btnListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dftModel;
				try {
					dftModel = subjectController.GetDTMAll();
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
										String title = "Edit a Subject";
										PanelOneSubjectInfo panel;

										panel = createPanelByFirstColumn(title, id);
										// b. User update data
										int resultInput = JOptionPane.showConfirmDialog(getParent(), panel, msg,
												JOptionPane.OK_CANCEL_OPTION);

										if (resultInput == JOptionPane.OK_OPTION) {
											int iSubjectID = panel.getSubjectID();
											String sSubjectName = panel.getSubjectName();
											String sDescription = panel.getDescription();
											SubjectEntity editedEntity = new SubjectEntity(iSubjectID, sSubjectName, sDescription);
											// JOptionPane.showMessageDialog(getParent(),
											// MajorEdited.toString());

											// c. Update new info into db
											boolean res;
											try {
												res = subjectController.InsertAndUpdateData(editedEntity, (byte) 1);
												if (res) {
													JOptionPane.showMessageDialog(getParent(), "Successfully update Subject info", 
															"Notification", JOptionPane.INFORMATION_MESSAGE);
												}else{
													JOptionPane.showMessageDialog(getParent(), "Unsuccessfully update Subject info", 
															"Error Message", JOptionPane.ERROR_MESSAGE);													
												}
											} catch (SQLException e) {//InsertAndUpdateData()												
												e.printStackTrace();
											}											
										}else{
											panel.setVisible(true);
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
							        	System.out.println("You selected : " + msg);
							        
							        	//a. Create a pop-up panel to show the selected info
							        	String title = "Are you sure to Delete this following record?";
							        	
							        	PanelOneSubjectInfo panel;
							        	panel = createPanelByFirstColumn(title, id);
							        	//disable all fiels => to display only
							        	panel.setEnableAllFields(false);
							        	
										// b. User update data
										int resultInput = JOptionPane.showConfirmDialog(getParent(), panel, title,
												JOptionPane.OK_CANCEL_OPTION);

										if (resultInput == JOptionPane.OK_OPTION) {
											int subjectID = panel.getSubjectID();											
											
											// c. Update new info into db
											boolean deleteResult;
											try {
												deleteResult = subjectController.DeleteDataByID(Integer.toString(subjectID));
												if (deleteResult) {
													String msgSuccess = "Successfully delete MajorID: " + subjectID + " from DB";
													JOptionPane.showMessageDialog(null, msgSuccess);
												} 
											} catch (SQLException e) {//DeleteDataByID()											
												String messageFailure = "Fail to delete Subject ID: " + subjectID + " from DB.\n "
														+ "as this subject has been used in other relations";
												JOptionPane.showMessageDialog(null, messageFailure, "Error Message", JOptionPane.ERROR_MESSAGE);
											}											
										}else{
							    			panel.setVisible(true);
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
				panelSearchInput.add(new JLabel("Subject Name: "));
				JTextField tfName = new JTextField(30);
				panelSearchInput.add(tfName);
				int opt = JOptionPane.showConfirmDialog(getParent(), panelSearchInput, "Search for Name",
						JOptionPane.OK_CANCEL_OPTION);

				if (opt == JOptionPane.OK_OPTION) {
					String seekingName = tfName.getText();
					DefaultTableModel dftModel;
					try {
						dftModel = subjectController.GetDTMByName(seekingName);

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
	 * Purpose: Get SubjectEntity object which is created by data user input into a form
	 * @param msg
	 * @return SubjectEntity
	 * @throws SQLException
	 */
	protected SubjectEntity getUserInputOneNewSubject(String msg) throws SQLException {
		SubjectEntity result = null;

		int maxID = subjectController.getMaxID();
		System.out.println("maxID: " + maxID);

		String title = "Add a Subject";
		PanelOneSubjectInfo panelAdd = new PanelOneSubjectInfo(title);

		int incrementValue = 1;
		panelAdd.setSubjectID(maxID + incrementValue);

		int resultInput = JOptionPane.showConfirmDialog(getParent(), panelAdd, msg, JOptionPane.OK_CANCEL_OPTION);

		if (resultInput == JOptionPane.OK_OPTION) {
			int iSubjectID = panelAdd.getSubjectID();
			String sSubjectName = panelAdd.getSubjectName();
			String sDescription = panelAdd.getDescription();			
			result = new SubjectEntity(iSubjectID, sSubjectName, sDescription);
		} else {
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}

	
	/**
	 * Purpose: Create a Panel holding data of a SubjectEntity
	 * @param title
	 * @param MajorID
	 * @return
	 */
	private PanelOneSubjectInfo createPanelByFirstColumn(String title, String SubjectID) {

		PanelOneSubjectInfo panel = new PanelOneSubjectInfo(title);		
		//1. Communicate with Controller to get SubjectEntity by ID 
		try {
			SubjectEntity selectedEntity = (SubjectEntity) subjectController.GetDataById(SubjectID).get(0);
			// 2. Fill into panel with info of 
			panel.setSubjectID(selectedEntity.getiSubjectID());
			panel.setSubjectName(selectedEntity.getsSubjectName());
			panel.setDescription(selectedEntity.getsDescription());			
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
		return panel;
	}
}
