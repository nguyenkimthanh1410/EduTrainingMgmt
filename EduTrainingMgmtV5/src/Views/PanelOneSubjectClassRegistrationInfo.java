package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Entities.StudentEntity;
import Entities.SubjectBasedClassEntity;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import BaseUltils.IOFIles.DateConverter;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;

public class PanelOneSubjectClassRegistrationInfo extends JPanel {
	private JTextField tfSubjectClassRegistrationID;
	private JComboBox<SubjectBasedClassEntity> cbSubjectBasedClass;
	private List<SubjectBasedClassEntity> listSubjectBasedClass;	
	private JComboBox<StudentEntity> cbStudent;
	private List<StudentEntity> listStudents;	
	private JPanel panelContent;
	private JLabel lbNote;
	private JTextField tfNote;
	private JLabel lblRegistedOn;
	private JDateChooser dateChooser;

	/**
	 * Create the panel.
	 * @param title 
	 */
	public PanelOneSubjectClassRegistrationInfo(String title) {
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
		
		JLabel lbSubjClassRegID = new JLabel("SCR ID:");
		
		tfSubjectClassRegistrationID = new JTextField();
		tfSubjectClassRegistrationID.setEditable(false);
		tfSubjectClassRegistrationID.setColumns(40);
		
		JLabel lbSubjectClass = new JLabel("Subject-based Class ID:");
		cbSubjectBasedClass = new JComboBox<SubjectBasedClassEntity>();
		
		JLabel lbStudent = new JLabel("Student:");		
		cbStudent = new JComboBox<StudentEntity>();
		
		lbNote = new JLabel("Note:");
		
		tfNote = new JTextField();
		tfNote.setColumns(10);
		
		lblRegistedOn = new JLabel("<html>\r\n<p>Registration Date:</p>\r\n<p>(YYYY-MM-DD)</p>\r\n</html>");
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelContent.createSequentialGroup()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbSubjectClass)
								.addComponent(lbSubjClassRegID))
							.addGap(5))
						.addComponent(lbStudent)
						.addComponent(lbNote)
						.addComponent(lblRegistedOn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(tfNote, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
						.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
						.addComponent(cbStudent, 0, 286, Short.MAX_VALUE)
						.addComponent(cbSubjectBasedClass, 0, 286, Short.MAX_VALUE)
						.addComponent(tfSubjectClassRegistrationID, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(33))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSubjectClassRegistrationID, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSubjClassRegID, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbSubjectBasedClass, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSubjectClass, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addGap(15)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbStudent, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbStudent, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRegistedOn, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfNote, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbNote, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelContent.setLayout(gl_panelContent);
	}

	
	//1. Method For SubjectClassRegistrationID
	public int getSubjectClassRegistrationID() {
		return Integer.parseInt(tfSubjectClassRegistrationID.getText());
	}

	public void setSubjectClassRegistrationID(int id) {
		tfSubjectClassRegistrationID.setText(Integer.toString(id));		
	}

	
	//Tutorial JComboBox:http://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples
	//2. Methods for JComboBox SubjectBasedClass
	public void setListSubjectBasedClassComboBox(List<SubjectBasedClassEntity> listSubjectBasedClass) {
		this.listSubjectBasedClass = listSubjectBasedClass;
		for(SubjectBasedClassEntity entity : listSubjectBasedClass){
			cbSubjectBasedClass.addItem(entity);
		}
		
		//set initial value => null
		cbSubjectBasedClass.setSelectedItem(null);
	}

	public int getSubjectBasedClassID() {
		SubjectBasedClassEntity objSelected = (SubjectBasedClassEntity) cbSubjectBasedClass.getSelectedItem();
		return objSelected.getiSubjectBasedClassID();
	}

	//for editing form
	// given SubjectBasedClassEntity id, find out SubjectBasedClassEntity obj to render on ComboBox
	public void setSubjectBasedClass(int subjectBasedClassID) {
		SubjectBasedClassEntity seeking = null;
		
		for (SubjectBasedClassEntity e : listSubjectBasedClass){
			if(e.getiSubjectBasedClassID() == subjectBasedClassID){
				seeking = e;
			}
		}		
		//set value for combobox
		cbSubjectBasedClass.setSelectedItem(seeking);		
	}

	
	// 3. Methods for Student ComboBox
	public int getStudentID() {
		StudentEntity objSelected = (StudentEntity) cbStudent.getSelectedItem();		
		return objSelected.getId();
	}
	
	public void setListStudentsComboBox(List<StudentEntity> listStudents) {
		this.listStudents = listStudents;		
		for(StudentEntity s : listStudents){
			cbStudent.addItem(s);
		}
		
		//set initial value => null
		cbStudent.setSelectedItem(null);
	}
	
	/**
	 * for Editing form
	 * Find Student obj, given a StudentID	  
	 * @param studentID
	 */
	public void setStudent(int studentID) {
		StudentEntity seeking = null;
		for(StudentEntity s : listStudents){
			if(s.getId() == studentID){
				seeking = s;
			}
		}	
		cbStudent.setSelectedItem(seeking);		
	}

	//4. Methods for Date
	public Date getRegistrationDate() {
		return dateChooser.getDate();
	}
	
	public void setRegistrationDate(String sRegistrationDate) {
		String format = "yyyy-MM-dd";
		Date dateDB = DateConverter.convertStringToDate(sRegistrationDate, format);
		dateChooser.setDate(dateDB);
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
		cbSubjectBasedClass.setEnabled(false);
		cbStudent.setEnabled(false);
		dateChooser.setEnabled(false);	
		tfNote.setEnabled(false);
	}
}
