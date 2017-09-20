package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Entities.LecturerEntity;
import Entities.SchoolEntity;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

public class PanelOneDeptInfo extends JPanel {
	private JTextField tfDeptID;
	private JTextField tfDeptName;
	private JComboBox<SchoolEntity> cbSchool;
	private List<SchoolEntity> listSchools;
	private JComboBox<LecturerEntity> cbLecturer;
	private List<LecturerEntity> listLecturers;
	
	private JPanel panelContent;

	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneDeptInfo(String title) {
		setMaximumSize(new Dimension(32765, 20000));
		setBackground(Color.ORANGE);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblAddAStudent = new JLabel(title);
		lblAddAStudent.setBackground(new Color(255, 255, 255));
		lblAddAStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblAddAStudent, BorderLayout.NORTH);
		
		panelContent = new JPanel();
		panelContent.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelContent, BorderLayout.CENTER);
		
		JLabel lbDeptID = new JLabel("Deptment ID:");
		
		tfDeptID = new JTextField();
		tfDeptID.setEditable(false);
		tfDeptID.setColumns(40);
		
		JLabel lbDeptName = new JLabel("Department Name:");
		
		tfDeptName = new JTextField();
		tfDeptName.setColumns(40);
		
		JLabel lbSchoolID = new JLabel("School:");		
		cbSchool = new JComboBox<SchoolEntity>();
		
		JLabel lbLecturer = new JLabel("<html>\r\n<p>Supervised By</p>\r\n<p>Lecturer ID:</p>\r\n</html>");
		cbLecturer = new JComboBox<LecturerEntity>();
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lbDeptName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbDeptID)
						.addComponent(lbSchoolID)
						.addComponent(lbLecturer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbLecturer, 0, 274, Short.MAX_VALUE)
						.addComponent(cbSchool, Alignment.LEADING, 0, 274, Short.MAX_VALUE)
						.addComponent(tfDeptName, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfDeptID, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
					.addGap(53))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(lbDeptID, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(tfDeptID, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbDeptName, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(tfDeptName, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbSchoolID, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbSchool, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(cbLecturer, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbLecturer, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
		);
		panelContent.setLayout(gl_panelContent);
	}

	public int getDeptID() {
		return Integer.parseInt(tfDeptID.getText());
	}

	public void setDeptID(int id) {
		tfDeptID.setText(Integer.toString(id));		
	}

	public String getDeptName() {
		return tfDeptName.getText();
	}
	
	public void setDeptName(String fullname) {
		tfDeptName.setText(fullname);		
	}

	//Tutorial JComboBox:
	//http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	public int getSchoolID() {
		SchoolEntity schoolSelected = (SchoolEntity) cbSchool.getSelectedItem();		
		return schoolSelected.getSchoolID();
	}

	
	public void setListSchoolsComboBox(List<SchoolEntity> listSchools) {
		this.listSchools = listSchools;		
		for(SchoolEntity s : listSchools){
			cbSchool.addItem(s);
		}
		
		//initial value as null
		cbSchool.setSelectedItem(null);
	}
	
	/**
	 * Find School obj by giving a schoolID
	 * for Editing form
	 * @param schoolID
	 */
	public void setSchool(int schoolID) {
		//find the School obj having schoolID
		SchoolEntity seekingSchool = null;
		for(SchoolEntity s : listSchools){
			if(s.getSchoolID() == schoolID){
				seekingSchool = s;
			}
		}	
		cbSchool.setSelectedItem(seekingSchool);		
	}

	public void setListLecturersComboBox(List<LecturerEntity> listLecturers) {
		this.listLecturers = listLecturers;
		for(LecturerEntity lec : listLecturers){
			cbLecturer.addItem(lec);
		}
		
		//set initial value
		cbLecturer.setSelectedItem(null);
	}

	public int getDeptSupervisorID() {
		LecturerEntity lecturerSelected = (LecturerEntity) cbLecturer.getSelectedItem();
		return lecturerSelected.getiLecturerID() ;
	}

	//for editing form
	public void setDeptSupervisorID(int deptSupervisorID) {
		LecturerEntity seekingDeptSupervisor = null;
		
		//find DeptSupervisor obj by ID
		for (LecturerEntity le : listLecturers){
			if(le.getiLecturerID() == deptSupervisorID){
				seekingDeptSupervisor = le;
			}
		}
		
		//set value for combobox
		cbLecturer.setSelectedItem(seekingDeptSupervisor);		
	}

	public void setEnableAllFields(boolean b) {
		//tfDeptID.setEnabled(false);;
		tfDeptName.setEnabled(false);
		cbSchool.setEnabled(false);		
		cbLecturer.setEnabled(false);		
	}
	
}
