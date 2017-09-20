package Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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

import Controllers.ExamController;
import Entities.ExamEntity;

public class frmExam extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private ExamController examController;	
	private JButton btnAdd;
	private JButton btnListAll;
	private JButton btnSearch;
	private final String titleString = "Examination Management";
	private JLabel lbTitle;
	
	/**
	 * Create the frame. 
	 * @param objConn
	 */
	public frmExam(Connection objConn) {

		// 1. Create Controller to delegate
		examController = new ExamController(objConn);
		
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
				ExamEntity objEntity = new ExamEntity();							
				String msg = "Enter info Examination";
				try {
					objEntity = getUserInputOneNewSubject(msg);
					if(objEntity != null){
						boolean result = examController.InsertAndUpdateData(objEntity, (byte) 0);
						if (result){
							JOptionPane.showMessageDialog(getParent(), "Successfully add a new Exam in DB",
															"Notification", JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(getParent(), "Unsuccessfully add a new Exam in DB",
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
					dftModel = examController.GetDTMAll();
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
										PanelOneExamInfo panel;

										panel = createPanelByFirstColumn(title, id);
										// b. User update data
										int resultInput = JOptionPane.showConfirmDialog(getParent(), panel, msg,
												JOptionPane.OK_CANCEL_OPTION);

										if (resultInput == JOptionPane.OK_OPTION) {
											int iExamID = panel.getExamID();
											String sExamName = panel.getExamName();
											Date dExamDate = panel.getDate();
											ExamEntity editedEntity = new ExamEntity(iExamID, sExamName, dExamDate);
											
											// c. Update new info into db
											boolean res;
											try {
												res = examController.InsertAndUpdateData(editedEntity, (byte) 1);
												if (res) {
													JOptionPane.showMessageDialog(getParent(), "Successfully update Exam info", 
															"Notification", JOptionPane.INFORMATION_MESSAGE);
												}else{
													JOptionPane.showMessageDialog(getParent(), "Unsuccessfully update Exam info", 
															"Error Message", JOptionPane.ERROR_MESSAGE);													
												}
											} catch (SQLException e) {//InsertAndUpdateData()												
												e.printStackTrace();
											}											
										}else{
											panel.setVisible(true);
											JOptionPane.showMessageDialog(getParent(), "You've cancelled updating Exam");
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
							        	
							        	PanelOneExamInfo panel;
							        	panel = createPanelByFirstColumn(title, id);
							        	//disable all fiels => to display only
							        	panel.setEnableAllFields(false);
							        	
										// b. User update data
										int resultInput = JOptionPane.showConfirmDialog(getParent(), panel, title,
												JOptionPane.OK_CANCEL_OPTION);

										if (resultInput == JOptionPane.OK_OPTION) {
											int iExamID = panel.getExamID();
											// c. Update new info into db
											boolean deleteResult;
											try {
												deleteResult = examController.DeleteDataByID(Integer.toString(iExamID));
												if (deleteResult) {
													String msgSuccess = "Successfully delete Exam ID: " + iExamID + " from DB";
													JOptionPane.showMessageDialog(null, msgSuccess);
												}
											} catch (SQLException e) {//DeleteDataByID()											
												String messageFailure = "Fail to delete Exam ID: " + iExamID + " from DB.\n "
														+ "as this subject has been used in other relations";
												JOptionPane.showMessageDialog(null, messageFailure, "Error Message", JOptionPane.ERROR_MESSAGE);
												//e.printStackTrace();
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
				panelSearchInput.add(new JLabel("Exam Name: "));
				JTextField tfName = new JTextField(30);
				panelSearchInput.add(tfName);
				int opt = JOptionPane.showConfirmDialog(getParent(), panelSearchInput, "Search for Name",
						JOptionPane.OK_CANCEL_OPTION);

				if (opt == JOptionPane.OK_OPTION) {
					String seekingName = tfName.getText();
					DefaultTableModel dftModel;
					try {
						dftModel = examController.GetDTMByName(seekingName);

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
		
		System.out.println("Constructor for frmExam");
	}
	
	
	/**
	 * Purpose: Get ExamEntity object which is created by data user input into a form
	 * @param msg
	 * @return ExamEntity
	 * @throws SQLException
	 */
	protected ExamEntity getUserInputOneNewSubject(String msg) throws SQLException {
		ExamEntity result = null;

		int maxID = examController.getMaxID();
		System.out.println("maxID: " + maxID);

		String title = "Add a Exam";
		PanelOneExamInfo panel = new PanelOneExamInfo(title);

		int incrementValue = 1;
		panel.setExamID(maxID + incrementValue);

		int resultInput = JOptionPane.showConfirmDialog(getParent(), panel, msg, JOptionPane.OK_CANCEL_OPTION);

		if (resultInput == JOptionPane.OK_OPTION) {
			int iExamID = panel.getExamID();
			String sExamName = panel.getExamName();
			Date dExamDate = panel.getDate();
			if(dExamDate != null){
				result = new ExamEntity(iExamID, sExamName, dExamDate);
			}else{
				JOptionPane.showMessageDialog(getParent(), "Date must be selected",
						"Error Message", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(getParent(), "You've cancelled the insertion into DB",
					"Notification", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}

	
	/**
	 * Purpose: Create a Panel holding data of a ExamEntity
	 * @param title
	 * @param ExamID
	 * @return
	 */
	private PanelOneExamInfo createPanelByFirstColumn(String title, String ExamID) {

		PanelOneExamInfo panel = new PanelOneExamInfo(title);		
		//1. Communicate with Controller to get ExamEntity by ID 
		try {
			ExamEntity selectedEntity = (ExamEntity) examController.GetDataById(ExamID).get(0);
			// 2. Fill into panel with info of 
			
			panel.setExamID(selectedEntity.getiExamID());
			panel.setDate(selectedEntity.getdExamDate());
			panel.setExamName(selectedEntity.getsExamName());						
		} catch (SQLException e) {			
			e.printStackTrace();
		}	
		return panel;
	}
}
