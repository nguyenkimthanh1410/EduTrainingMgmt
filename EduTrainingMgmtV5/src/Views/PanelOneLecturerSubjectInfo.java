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
import Entities.SubjectEntity;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class PanelOneLecturerSubjectInfo extends JPanel {
	private JTextField tfLecturerSubjectID;
	private JComboBox<SubjectEntity> cbSubject;
	private List<SubjectEntity> listSubjects;
	private JComboBox<LecturerEntity> cbLecturer;
	private List<LecturerEntity> listLecturers;
	
	private JPanel panelContent;

	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneLecturerSubjectInfo(String title) {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setMaximumSize(new Dimension(32765, 20000));
		setBackground(Color.ORANGE);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lbTitle = new JLabel(title);
		lbTitle.setBackground(new Color(255, 255, 255));
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lbTitle, BorderLayout.NORTH);
		
		panelContent = new JPanel();
		panelContent.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelContent, BorderLayout.CENTER);
		
		JLabel lbLecturerSubjectID = new JLabel("ID:");
		
		tfLecturerSubjectID = new JTextField();
		tfLecturerSubjectID.setEditable(false);
		tfLecturerSubjectID.setColumns(40);
		
		JLabel lbSubjectID = new JLabel("Subject:");		
		cbSubject = new JComboBox<SubjectEntity>();
		
		JLabel lbLecturer = new JLabel("Lecturer:");
		cbLecturer = new JComboBox<LecturerEntity>();
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbSubjectID)
						.addComponent(lbLecturer)
						.addComponent(lbLecturerSubjectID, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbLecturer, 0, 306, Short.MAX_VALUE)
						.addComponent(cbSubject, 0, 306, Short.MAX_VALUE)
						.addComponent(tfLecturerSubjectID, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
					.addGap(33))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfLecturerSubjectID, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbLecturerSubjectID, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbLecturer, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbLecturer, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addGap(26)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbSubject, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSubjectID, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addGap(108))
		);
		panelContent.setLayout(gl_panelContent);
	}

	//For ID
	public int getLecturerSubjectID() {
		return Integer.parseInt(tfLecturerSubjectID.getText());
	}

	public void setLecturerSubjectID(int id) {
		tfLecturerSubjectID.setText(Integer.toString(id));		
	}

	//For JComboBox Lecturer
	public void setListLecturersComboBox(List<LecturerEntity> listLecturers) {
		this.listLecturers = listLecturers;
		for(LecturerEntity lec : listLecturers){
			cbLecturer.addItem(lec);
		}		
		
		//set initial value-> null
		cbLecturer.setSelectedItem(null);
	}

	public int getLecturerID() {
		LecturerEntity objSelected = (LecturerEntity) cbLecturer.getSelectedItem();
		return objSelected.getiLecturerID() ;
	}

	//for editing form
	// given LecturerID, find out Lecturer obj to render on ComboBox
	public void setLecturer(int lecturerID) {
		LecturerEntity seeking = null;
		
		//find DeptSupervisor obj by ID
		for (LecturerEntity le : listLecturers){
			if(le.getiLecturerID() == lecturerID){
				seeking = le;
			}
		}
		
		//set value for combobox
		cbLecturer.setSelectedItem(seeking);		
	}
	
	
	//Tutorial JComboBox:
	//http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	public int getSubjectID() {
		SubjectEntity objSelected = (SubjectEntity) cbSubject.getSelectedItem();		
		return objSelected.getiSubjectID();
	}

	
	public void setListSubjectsComboBox(List<SubjectEntity> listSubjects) {
		this.listSubjects = listSubjects;		
		for(SubjectEntity s : listSubjects){
			cbSubject.addItem(s);
		}
		
		//set initial value => null
		cbSubject.setSelectedItem(null);
	}
	
	/**
	 * for Editing form
	 * Find Subject obj, given a schoolID	  
	 * @param schoolID
	 */
	public void setSubject(int subjectID) {
		SubjectEntity seeking = null;
		for(SubjectEntity s : listSubjects){
			if(s.getiSubjectID() == subjectID){
				seeking = s;
			}
		}	
		cbSubject.setSelectedItem(seeking);		
	}

	public void setEnableAllFields(boolean b) {
		cbLecturer.setEnabled(false);
		cbSubject.setEnabled(false);
	}	
}
