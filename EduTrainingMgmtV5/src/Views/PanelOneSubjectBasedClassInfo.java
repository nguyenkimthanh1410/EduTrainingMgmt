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
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelOneSubjectBasedClassInfo extends JPanel {
	private JTextField tfSubjectBasedClassID;
	private JComboBox<SubjectEntity> cbSubject;
	private List<SubjectEntity> listSubjects;
	private JComboBox<LecturerEntity> cbLecturer;
	private List<LecturerEntity> listLecturers;
	
	private JPanel panelContent;
	private JLabel lbNote;
	private JTextField tfNote;

	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneSubjectBasedClassInfo(String title) {
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
		
		JLabel lbSubjectBasedClassID = new JLabel("SubjClass ID:");
		
		tfSubjectBasedClassID = new JTextField();
		tfSubjectBasedClassID.setEditable(false);
		tfSubjectBasedClassID.setColumns(40);
		
		JLabel lbLecturer = new JLabel("Lecturer:");
		cbLecturer = new JComboBox<LecturerEntity>();
		
		JLabel lbSubjectID = new JLabel("Subject:");		
		cbSubject = new JComboBox<SubjectEntity>();
		
		lbNote = new JLabel("Note:");
		
		tfNote = new JTextField();
		tfNote.setColumns(10);
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(lbSubjectID)
							.addGap(5))
						.addComponent(lbNote)
						.addComponent(lbLecturer)
						.addComponent(lbSubjectBasedClassID))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(tfNote, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
						.addComponent(cbLecturer, 0, 301, Short.MAX_VALUE)
						.addComponent(cbSubject, Alignment.TRAILING, 0, 301, Short.MAX_VALUE)
						.addComponent(tfSubjectBasedClassID, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
					.addGap(33))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSubjectBasedClassID, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSubjectBasedClassID, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbSubject, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSubjectID, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbLecturer, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbLecturer, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addGap(15)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNote, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbNote, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(36))
		);
		panelContent.setLayout(gl_panelContent);
	}

	//For ID
	public int getSubjectBasedClassID() {
		return Integer.parseInt(tfSubjectBasedClassID.getText());
	}

	public void setSubjectBasedClassID(int id) {
		tfSubjectBasedClassID.setText(Integer.toString(id));		
	}

	

	//Tutorial JComboBox:http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	// For Subject
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

	
	//For JComboBox Lecturer
	public void setListLecturersComboBox(List<LecturerEntity> listLecturers) {
		this.listLecturers = listLecturers;
		for(LecturerEntity lec : listLecturers){
			cbLecturer.addItem(lec);
		}
		
		//set initial value => null
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

	public void setEnableAllFields(boolean b) {
		cbSubject.setEnabled(false);
		cbLecturer.setEnabled(false);	
		tfNote.setEnabled(false);
	}

	public String getNote() {
		return tfNote.getText();
	}

	public void setNote(String sNote) {
		tfNote.setText(sNote);
	}	
}
