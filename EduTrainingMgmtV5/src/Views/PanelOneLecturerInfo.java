package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import Entities.DepartmentEntity;
import Entities.LecturerEntity;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelOneLecturerInfo extends JPanel {
	private List<LecturerEntity> listLecturers;
	private List<DepartmentEntity> listDepartments;
	
	private JPanel panelContent;
	private JTextField tfLecturerID;
	private JLabel lbLecturerName;
	private JTextField tfLecturerName;
	private JLabel lbSSN;
	private JTextField tfSSN;
	private JLabel lbAddress;
	private JTextField tfAddress;
	private JLabel lbManagedBy;
	private JLabel lblDepartment;
	private JComboBox<LecturerEntity> cbLecturer;	
	private JComboBox<DepartmentEntity> cbDepartment;

	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneLecturerInfo(String title) {
		setMaximumSize(new Dimension(32765, 20000));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.ORANGE);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblAddAStudent = new JLabel(title);
		lblAddAStudent.setBackground(new Color(255, 255, 255));
		lblAddAStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblAddAStudent, BorderLayout.NORTH);
		
		panelContent = new JPanel();
		add(panelContent, BorderLayout.CENTER);
		
		JLabel lbLecturerID = new JLabel("Lecturer ID:");
		
		tfLecturerID = new JTextField();
		tfLecturerID.setEditable(false);
		tfLecturerID.setColumns(10);
		
		lbLecturerName = new JLabel("Lecturer Name:");
		
		tfLecturerName = new JTextField();
		tfLecturerName.setColumns(10);
		
		lbSSN = new JLabel("SSN:");
		
		tfSSN = new JTextField();
		tfSSN.setColumns(10);
		
		lbAddress = new JLabel("Address:");
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		
		lbManagedBy = new JLabel("<html>\r\n<p>Managed By</p>\r\n<p>Lecturer ID:</p>\r\n</html>");
		
		lblDepartment = new JLabel("Department:");
		
		cbLecturer = new JComboBox<LecturerEntity>();
		
		cbDepartment = new JComboBox<DepartmentEntity>();
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(lbManagedBy, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panelContent.createSequentialGroup()
								.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_panelContent.createSequentialGroup()
										.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
											.addComponent(lbLecturerName)
											.addComponent(lblDepartment)
											.addComponent(lbAddress))
										.addGap(21))
									.addComponent(lbSSN, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_panelContent.createSequentialGroup()
								.addComponent(lbLecturerID)
								.addGap(22))))
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(tfAddress, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
						.addComponent(cbDepartment, Alignment.LEADING, 0, 266, Short.MAX_VALUE)
						.addComponent(cbLecturer, Alignment.LEADING, 0, 266, Short.MAX_VALUE)
						.addComponent(tfSSN, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
						.addComponent(tfLecturerName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
						.addComponent(tfLecturerID, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
					.addGap(45))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbLecturerID, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLecturerID, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbLecturerName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfLecturerName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSSN, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSSN, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(lbAddress, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addGap(27))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(tfAddress, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
							.addGap(18)))
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(lbManagedBy, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbLecturer, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDepartment, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbDepartment, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelContent.setLayout(gl_panelContent);
	}

	public int getLecturerID() {
		return Integer.parseInt(tfLecturerID.getText());
	}

	public void setLecturerID(int id) {
		tfLecturerID.setText(Integer.toString(id));		
	}

	public String getLecturerName() {
		return tfLecturerName.getText();
	}
	
	public void setLecturerName(String fullname) {
		tfLecturerName.setText(fullname);		
	}

	public String getSSN(){
		return tfSSN.getText();
	}
	
	public void setSSN(String SSN){
		tfSSN.setText(SSN);
	}
	
	public String getAddress(){
		return tfAddress.getText();
	}
	
	public void setAddress(String address){
		tfAddress.setText(address);
	}
	
	//Tutorial JComboBox:
	//http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	public int getManagedBy() {
		LecturerEntity lecturerSelected = (LecturerEntity) cbLecturer.getSelectedItem();		
		return lecturerSelected.getiLecturerID();
	}

	
	public void setListLecturersComboBox(List<LecturerEntity> listLecturers) {
		this.listLecturers = listLecturers;		
		for(LecturerEntity le : listLecturers){
			cbLecturer.addItem(le);
		}
		
		//set initial value
		cbLecturer.setSelectedItem(null);
	}
	
	/**
	 * Find Lecturer obj by giving a LecturerID
	 * for Editing form for Managed by 
	 * @param lecturerID
	 */
	public void setManagedBy(int lecturerID) {
		//find the Lecturer obj having lecturerID
		LecturerEntity seekingEntity = null;
		for(LecturerEntity element : listLecturers){
			if(element.getiLecturerID() == lecturerID){
				seekingEntity = element;
			}
		}	
		cbLecturer.setSelectedItem(seekingEntity);		
	}
	
	
	//for Department 
	public void setListDepartmentsComboBox(List<DepartmentEntity> listDepartments) {
		this.listDepartments = listDepartments;
		for(DepartmentEntity element : listDepartments){
			cbDepartment.addItem(element);
		}		
		//set initial value
		cbDepartment.setSelectedItem(null);
		
	}

	public int getDepartmentID() {
		DepartmentEntity selected = (DepartmentEntity) cbDepartment.getSelectedItem();
		return selected.getiDeptID();
	}

	//for editing form
	//given LecturerID, => deptID ==> Need to find out DepartmentEntity to put into cbDept
	public void setDepartment(int deptID) {
		DepartmentEntity seeking = null;
		
		for (DepartmentEntity element : listDepartments){
			if(element.getiDeptID() == deptID){
				seeking = element;
			}
		}		
		//set value for combobox
		cbDepartment.setSelectedItem(seeking);		
	}

	public void setEnableAllFields(boolean b) {
		//tfLecturerID.setEnabled(false);
		tfLecturerName.setEnabled(false);		
		tfSSN.setEnabled(false);
		tfAddress.setEnabled(false);		
		cbLecturer.setEnabled(false);	
		cbDepartment.setEnabled(false);		
	}
}
