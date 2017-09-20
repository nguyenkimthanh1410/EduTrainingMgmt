package Views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

public class PanelOneSubjectInfo extends JPanel {
	private JTextField tfSubjectID;
	private JTextField tfSubjectName;	
	private JTextField tfDescription;

	public PanelOneSubjectInfo(String title) {
		setBackground(Color.ORANGE);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblAddAStudent = new JLabel(title);
		lblAddAStudent.setBackground(new Color(255, 255, 255));
		lblAddAStudent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblAddAStudent, BorderLayout.NORTH);
		
		JPanel panelContent = new JPanel();
		panelContent.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelContent.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(panelContent, BorderLayout.CENTER);
		
		JLabel lbSubjectD = new JLabel("Subject ID:");
		
		tfSubjectID = new JTextField();
		tfSubjectID.setEditable(false);
		tfSubjectID.setColumns(40);
		
		JLabel lbSubjectName = new JLabel("Subject Name:");
		
		tfSubjectName = new JTextField();
		tfSubjectName.setColumns(40);
		
		JLabel lbDescription = new JLabel("Description:");
		
		tfDescription = new JTextField();
		tfDescription.setColumns(10);
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbSubjectD)
						.addComponent(lbSubjectName)
						.addComponent(lbDescription))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addComponent(tfSubjectName, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
						.addComponent(tfDescription, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
						.addComponent(tfSubjectID, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
					.addGap(36))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSubjectID, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
						.addComponent(lbSubjectD, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbSubjectName, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfSubjectName, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbDescription, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfDescription, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(125))
		);
		panelContent.setLayout(gl_panelContent);
	}

	
	public int getSubjectID() {
		return Integer.parseInt(tfSubjectID.getText());
	}

	public void setSubjectID(int id) {
		tfSubjectID.setText(Integer.toString(id));
	}

	public String getSubjectName() {
		return tfSubjectName.getText();
	}
	
	public void setSubjectName(String sSubjectName) {
		tfSubjectName.setText(sSubjectName);
	}
	
	public String getDescription(){
		return tfDescription.getText();
	}
	
	public void setDescription(String sDescription){
		tfDescription.setText(sDescription);
	}

	public void setEnableAllFields(boolean b) {
		//tfSubjectID.setEnabled(false);
		tfSubjectName.setEnabled(false);	
		tfDescription.setEditable(false);
	}	
}
