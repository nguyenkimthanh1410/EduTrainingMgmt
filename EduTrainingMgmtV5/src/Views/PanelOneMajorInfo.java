package Views;

import Entities.DepartmentEntity;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;

public class PanelOneMajorInfo extends JPanel {
	private JTextField tfMajorID;
	private JTextField tfMajorName;
	private JComboBox<DepartmentEntity> cbDepartmentID;
	private List<DepartmentEntity> listDepartments;
	private JPanel panelContent;

	public PanelOneMajorInfo(String title) {
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setBackground(Color.ORANGE);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblAddAStudent = new JLabel(title);
		lblAddAStudent.setBackground(new Color(255, 200, 0));
		lblAddAStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblAddAStudent, BorderLayout.NORTH);
		
		panelContent = new JPanel();
		panelContent.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(panelContent, BorderLayout.CENTER);
		
		JLabel lbMajorID = new JLabel("Major ID:");
		
		tfMajorID = new JTextField();
		tfMajorID.setEditable(false);
		tfMajorID.setColumns(40);
		
		JLabel lbMajorName = new JLabel("Major Name:");
		
		tfMajorName = new JTextField();
		tfMajorName.setColumns(40);
		
		JLabel lbDepartment = new JLabel("Department:");
		
		cbDepartmentID = new JComboBox<DepartmentEntity>();
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelContent.createSequentialGroup()
					.addGap(54)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(lbMajorID, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbDepartment)
								.addComponent(lbMajorName))
							.addGap(5)))
					.addGap(5)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbDepartmentID, Alignment.LEADING, 0, 282, Short.MAX_VALUE)
						.addComponent(tfMajorName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(tfMajorID, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
					.addGap(41))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfMajorID, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMajorID, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfMajorName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMajorName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbDepartmentID, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbDepartment, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(108))
		);
		panelContent.setLayout(gl_panelContent);
	}

	
	public int getMajorID() {
		return Integer.parseInt(tfMajorID.getText());
	}

	public String getMajorName() {
		return tfMajorName.getText();
	}

	public int getiDepartment() {
		DepartmentEntity departmentSelected = (DepartmentEntity) cbDepartmentID.getSelectedItem();
		return departmentSelected.getiDeptID();
	}

	public void setNextMajorID(int i) {
		tfMajorID.setText(Integer.toString(i));
	}

	public void setListDepartmentComboBox(List<DepartmentEntity> listDepartments) {
		this.listDepartments = listDepartments;
		for (DepartmentEntity dept : listDepartments) {
			cbDepartmentID.addItem(dept);
		}
		
		//set initial value
		cbDepartmentID.setSelectedItem(null);		
	}

	public void setMajorID(int id) {
		tfMajorID.setText(Integer.toString(id));
	}

	public void setMajorName(String sMajorName) {
		tfMajorName.setText(sMajorName);
	}

	public void setDepartmentID(int idepartmentID) {
		DepartmentEntity seekingDepartment = null;
		for (DepartmentEntity dept : listDepartments) {
			if (dept.getiDeptID() == idepartmentID) {
				seekingDepartment = dept;
			}
		}
		cbDepartmentID.setSelectedItem(seekingDepartment);
	}


	public void setEnableAllFields(boolean b) {
		//tfMajorID.setEnabled(false);
		tfMajorName.setEnabled(false);
		cbDepartmentID.setEnabled(false);		
	}
}
