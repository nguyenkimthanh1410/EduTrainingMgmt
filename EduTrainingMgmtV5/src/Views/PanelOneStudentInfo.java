package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import Entities.SchoolEntity;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelOneStudentInfo extends JPanel {
	private JTextField tfStudentID;
	private JTextField tfStudentName;
	private JTextField tfAddress;
	private JComboBox<SchoolEntity> cbSchool;
	private List<SchoolEntity> listSchools;
	private JDateChooser dateChooser;
	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneStudentInfo(String title) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.ORANGE);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblAddAStudent = new JLabel(title);
		lblAddAStudent.setBackground(new Color(255, 255, 255));
		lblAddAStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblAddAStudent, BorderLayout.NORTH);
		
		JPanel panelContent = new JPanel();
		panelContent.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(panelContent, BorderLayout.WEST);
		
		JLabel lblStudentId = new JLabel("Student ID");
		
		tfStudentID = new JTextField();
		tfStudentID.setEditable(false);
		tfStudentID.setColumns(40);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		
		tfStudentName = new JTextField();
		tfStudentName.setColumns(40);
		
		JLabel lblDateOfBirth = new JLabel("DOB(YYYY-MM-DD):");
		
		JLabel label = new JLabel("Address:");
		
		tfAddress = new JTextField();
		tfAddress.setColumns(40);
		
		JLabel lblSchool = new JLabel("School");
		
		cbSchool = new JComboBox<SchoolEntity>();
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("JDateChooser is selected");
			}
		});
		
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(20)
							.addComponent(lblDateOfBirth))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblStudentId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblStudentName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(57)
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblSchool, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(tfStudentID, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
							.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(tfStudentName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
							.addComponent(tfAddress, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
						.addComponent(cbSchool, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE))
					.addGap(51))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(82)
							.addComponent(lblStudentName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(35)
							.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfStudentID, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblStudentId, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tfStudentName, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(lblDateOfBirth, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(tfAddress, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(lblSchool, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
							.addGap(23))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(cbSchool, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		panelContent.setLayout(gl_panelContent);
	}

	public int getStudentID() {
		return Integer.parseInt(tfStudentID.getText());
	}

	public String getStudentFullName() {
		return tfStudentName.getText();
	}

	public Date getStudentDOB() {
		Date dob = null;
		dob = dateChooser.getDate();		
		return dob;
	}
	

	public String getStudentAddress() {
		return tfAddress.getText();
	}

	//Tutorial JComboBox:
	//http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	public int getSchoolID() {
		SchoolEntity schoolSelected = (SchoolEntity) cbSchool.getSelectedItem();		
		return schoolSelected.getSchoolID();
	}


	public void setListSchoolsComboBox(List<SchoolEntity> listSchools) {
		this.listSchools = listSchools;		
		//Add Item to ComboBox
		//cbSchool.addItem(new School(5, "School Test 1"));
		//cbSchool.addItem(new School(6, "School Test 2"));
		for(SchoolEntity s : listSchools){
			cbSchool.addItem(s);
		}
		
		cbSchool.setSelectedItem(null);//initialize the first value
	}

	public void setStudentID(int id) {
		tfStudentID.setText(Integer.toString(id));		
	}

	public void setStudentFullName(String fullname) {
		tfStudentName.setText(fullname);		
	}

	public void setStudentDOB(Date dob) {
		dateChooser.setDate(dob);
	}

	public void setStudentAddress(String address) {
		tfAddress.setText(address);		
	}

	/**
	 * Find School obj by giving a schoolID
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

	public void setEnableAllFields(boolean b) {
		//tfStudentID.setEnabled(false);
		tfStudentName.setEnabled(false);
		dateChooser.setEnabled(false);
		tfAddress.setEnabled(false);
		cbSchool.setEnabled(false);		
	}
}
