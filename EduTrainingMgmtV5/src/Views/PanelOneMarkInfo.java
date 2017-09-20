package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Entities.ExamEntity;
import Entities.SubjectClassRegistrationEntity;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelOneMarkInfo extends JPanel {
	private JTextField tfMarkID;
	private JComboBox<ExamEntity> cbExam;
	private List<ExamEntity> listExams;
	private JComboBox<SubjectClassRegistrationEntity> cbSubjectClassRegistration;
	private List<SubjectClassRegistrationEntity> listSubjectClassRegistrations;
	
	private JPanel panelContent;
	private JTextField tfMark;

	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneMarkInfo(String title) {
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
		
		JLabel lbMarkID = new JLabel("Mark ID:");
		
		tfMarkID = new JTextField();
		tfMarkID.setEditable(false);
		tfMarkID.setColumns(40);
		
		JLabel lbExam = new JLabel("Exam:");		
		cbExam = new JComboBox<ExamEntity>();
		
		JLabel lbSubjectClassRegistration = new JLabel("SCR ID:");
		lbSubjectClassRegistration.setToolTipText("<html>\r\n<p>The code is formated by</p>\r\n<p>ClassID: SubjectID:StudentID</p>\r\n<p>When a student registers into a Subject-based class</p>\r\n<p>He/She will be given a SCR ID uniquely</p>\r\n</html>");
		cbSubjectClassRegistration = new JComboBox<SubjectClassRegistrationEntity>();
		
		JLabel lbMark = new JLabel("Mark:");
		
		tfMark = new JTextField();
		tfMark.setColumns(10);
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addContainerGap(110, Short.MAX_VALUE)
							.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
								.addComponent(lbMark, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lbExam, Alignment.TRAILING))
							.addGap(24))
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panelContent.createSequentialGroup()
									.addComponent(lbSubjectClassRegistration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(10))
								.addGroup(gl_panelContent.createSequentialGroup()
									.addComponent(lbMarkID)
									.addGap(18)))))
					.addGap(5)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(cbSubjectClassRegistration, 0, 236, Short.MAX_VALUE)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelContent.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(tfMark, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
								.addComponent(cbExam, 0, 236, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(tfMarkID, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addGap(41))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfMarkID, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMarkID, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbSubjectClassRegistration, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSubjectClassRegistration, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbExam, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbExam, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfMark, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbMark, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(7))
		);
		panelContent.setLayout(gl_panelContent);
	}

	//For ID
	public int getMarkID() {
		return Integer.parseInt(tfMarkID.getText());
	}

	public void setMarkID(int id) {
		tfMarkID.setText(Integer.toString(id));		
	}
	
	//Tutorial JComboBox:
	//http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	
	//For Exam ComboBox
	public int getExamID() {
		ExamEntity objSelected = (ExamEntity) cbExam.getSelectedItem();		
		return objSelected.getiExamID();
	}

	
	public void setListExamsComboBox(List<ExamEntity> listExams) {
		this.listExams = listExams;		
		for(ExamEntity e : listExams){
			cbExam.addItem(e);
		}
		
		//set initial value => null
		cbExam.setSelectedItem(null);
	}
	
	/**
	 * for Editing form
	 * Find ExamEntity obj, given a ExamID	  
	 * @param examID
	 */
	public void setExam(int examID) {
		ExamEntity seeking = null;
		for(ExamEntity e : listExams){
			if(e.getiExamID() == examID){
				seeking = e;
			}
		}	
		cbExam.setSelectedItem(seeking);		
	}


	//For JComboBox SubjectClassRegistrationEntity
	public void setListSubjectClassRegistrationComboBox(List<SubjectClassRegistrationEntity> listSubjectClassRegistrations) {
		this.listSubjectClassRegistrations = listSubjectClassRegistrations;
		for(SubjectClassRegistrationEntity entity : listSubjectClassRegistrations){
			cbSubjectClassRegistration.addItem(entity);
		}		
		
		//set initial value-> null
		cbSubjectClassRegistration.setSelectedItem(null);
	}

	public int getSubjectClassRegistrationID() {
		SubjectClassRegistrationEntity objSelected = (SubjectClassRegistrationEntity) cbSubjectClassRegistration.getSelectedItem();
		return objSelected.getiSubjectClassRegistrationID() ;
	}

	//for editing form
	// given SubjectClassRegistrationID, find out SubjectClassRegistrationEntity obj to render on ComboBox
	public void setSubjectClassRegistration(int SubjectClassRegistrationID) {
		SubjectClassRegistrationEntity seeking = null;
		
		//find DeptSupervisor obj by ID
		for (SubjectClassRegistrationEntity entity : listSubjectClassRegistrations){
			if(entity.getiSubjectClassRegistrationID() == SubjectClassRegistrationID ){
				seeking = entity;
			}
		}
		
		//set value for combobox
		cbSubjectClassRegistration.setSelectedItem(seeking);		
	}
		
	//For Mark
	public void setMark(float mark){
		tfMark.setText(Float.toString(mark));
	}
	
	public float getMark(){
		return Float.parseFloat(tfMark.getText());
	}
	
	//disable all fields for Read-only purpose
	public void setEnableAllFields(boolean b) {
		cbExam.setEnabled(false);
		cbSubjectClassRegistration.setEnabled(false);
		tfMark.setEnabled(false);		
	}	
}
