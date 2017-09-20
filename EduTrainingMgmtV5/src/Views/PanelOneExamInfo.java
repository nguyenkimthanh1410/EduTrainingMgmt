package Views;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import BaseUltils.IOFIles.DateConverter;
import SwingUI.DatePickerExample;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class PanelOneExamInfo extends JPanel {
	private JTextField tfExamID;
	private JTextField tfExamName;	
	private JDateChooser dateChooser;
	
	public PanelOneExamInfo(String title) {
		setBackground(Color.ORANGE);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitle = new JLabel(title);
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblTitle, BorderLayout.NORTH);
		
		JPanel panelContent = new JPanel();
		panelContent.setAlignmentX(0.0f);
		panelContent.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelContent, BorderLayout.CENTER);
		
		JLabel lbExamID = new JLabel("Exam ID:");
		
		tfExamID = new JTextField();
		tfExamID.setEditable(false);
		tfExamID.setColumns(40);
		
		JLabel lbExamName = new JLabel("Exam Name:");
		
		tfExamName = new JTextField();
		tfExamName.setColumns(40);
		
		JLabel lbDate = new JLabel("Date (YYYY-MM-DD):");
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		
		
		GroupLayout gl_panelContent = new GroupLayout(panelContent);
		gl_panelContent.setHorizontalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelContent.createSequentialGroup()
							.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
								.addComponent(lbExamName, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbExamID, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panelContent.createSequentialGroup()
							.addComponent(lbDate)
							.addGap(13)))
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(tfExamName, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING)
							.addComponent(tfExamID, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
					.addGap(46))
		);
		gl_panelContent.setVerticalGroup(
			gl_panelContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContent.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lbExamID, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tfExamID, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbDate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelContent.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfExamName, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbExamName, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(82))
		);
		panelContent.setLayout(gl_panelContent);
	}

	
	public int getExamID() {
		return Integer.parseInt(tfExamID.getText());
	}

	public void setExamID(int id) {
		tfExamID.setText(Integer.toString(id));
	}

	public String getExamName() {
		return tfExamName.getText();
	}
	
	public void setExamName(String sExamName) {
		tfExamName.setText(sExamName);
	}
	
	public java.util.Date getDate(){
		return dateChooser.getDate();
	}
	
	public void setDate(java.util.Date dDate){
		dateChooser.setDate(dDate);
	}

	public void setEnableAllFields(boolean b) {
		//tfSubjectID.setEnabled(false);
		dateChooser.setEnabled(false);
		tfExamName.setEnabled(false);		
	}	
}
