package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Entities.MajorEntity;
import Entities.SubjectEntity;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelOneMajorSubjectInfo extends JPanel {
	private JTextField tfMajorSubjectID;
	private JComboBox<MajorEntity> cbMajor;
	private List<MajorEntity> listMajors;	
	private JComboBox<SubjectEntity> cbSubject;
	private List<SubjectEntity> listSubjects;
	private JComboBox<String> cbCompulsory;
	private List<String> listCompulsory = Arrays.asList("true", "false");
	private JPanel panelContent;
	private JLabel lbNote;
	private JTextField tfNote;

	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneMajorSubjectInfo(String title) {
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
		
		JLabel lbMajorSubjectID = new JLabel("Major Subject ID:");
		
		tfMajorSubjectID = new JTextField();
		tfMajorSubjectID.setEditable(false);
		tfMajorSubjectID.setColumns(40);
		
		JLabel lbMajor = new JLabel("Major:");
		cbMajor = new JComboBox<MajorEntity>();
		
		JLabel lbSubjectID = new JLabel("Subject:");		
		cbSubject = new JComboBox<SubjectEntity>();
		
		lbNote = new JLabel("Note:");
		
		tfNote = new JTextField();
		tfNote.setColumns(10);
		
		JLabel lblCompulsory = new JLabel("Compulsory:");		
		cbCompulsory = new JComboBox<String>();
		//Set values to cbCompulsory as it is not variable so often
		setListCompulsoryComboBox(listCompulsory);
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbMajor)
								.addComponent(lbMajorSubjectID))
							.addGap(5))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbNote)
								.addComponent(lblCompulsory)
								.addComponent(lbSubjectID))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
								.addComponent(tfNote, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
								.addComponent(cbSubject, 0, 314, Short.MAX_VALUE)
								.addComponent(cbMajor, Alignment.LEADING, 0, 314, Short.MAX_VALUE)
								.addComponent(tfMajorSubjectID, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
							.addGap(33))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addComponent(cbCompulsory, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfMajorSubjectID, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMajorSubjectID, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbMajor, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMajor, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addGap(8)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbSubject, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSubjectID, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCompulsory)
						.addComponent(cbCompulsory, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNote, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbNote, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(7))
		);
		panelContent.setLayout(gl_panelContent);
	}

	
	//1. Method For MajorSubjectID
	public int getMajorSubjectID() {
		return Integer.parseInt(tfMajorSubjectID.getText());
	}

	public void setMajorSubjectID(int id) {
		tfMajorSubjectID.setText(Integer.toString(id));		
	}

	
	//Tutorial JComboBox:http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	//2. Methods for JComboBox Major
	public void setListMajorsComboBox(List<MajorEntity> listMajors) {
		this.listMajors = listMajors;
		for(MajorEntity lec : listMajors){
			cbMajor.addItem(lec);
		}
		
		//set initial value => null
		cbMajor.setSelectedItem(null);
	}

	public int getMajorID() {
		MajorEntity objSelected = (MajorEntity) cbMajor.getSelectedItem();
		return objSelected.getiMajorID();
	}

	//for editing form
	// given MajorID, find out MajorEntity obj to render on ComboBox
	public void setMajor(int majorID) {
		MajorEntity seeking = null;
		
		for (MajorEntity e : listMajors){
			if(e.getiMajorID() == majorID){
				seeking = e;
			}
		}		
		//set value for combobox
		cbMajor.setSelectedItem(seeking);		
	}

	
	// 3. Methods for Subject ComboBox
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

	//4. Methods for Compulsory ComboBox
	public boolean isCompulsory() {
		String objSelected = (String) cbCompulsory.getSelectedItem();		
		return Boolean.parseBoolean(objSelected);
	}
	
	private void setListCompulsoryComboBox(List<String> listCompulsory) {
		for(String s : listCompulsory){
			cbCompulsory.addItem(s);
		}
		
		//set initial value as null
		cbCompulsory.setSelectedItem(null);		
	}
	
	public void setCompulsory(boolean value){
		String val = Boolean.toString(value);
		cbCompulsory.setSelectedItem(val);
	}

	//5. Methods for Note
	public String getNote() {
		return tfNote.getText();
	}

	public void setNote(String sNote) {
		tfNote.setText(sNote);
	}	
	
	//6. Other methods
	public void setEnableAllFields(boolean b) {
		//tfMajorSubjectID.setEnabled(false);
		cbMajor.setEnabled(false);
		cbSubject.setEnabled(false);
		cbCompulsory.setEnabled(false);	
		tfNote.setEnabled(false);
	}
}
