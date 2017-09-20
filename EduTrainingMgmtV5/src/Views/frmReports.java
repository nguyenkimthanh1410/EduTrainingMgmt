package Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controllers.ReportController;

public class frmReports extends JFrame {

	private JPanel contentPane;
	private JTable jtableData;
	private ReportController reportController;
	private final String titleString = "Reports";	

	/**
	 * Create the frame.
	 */
	public frmReports(Connection objConn) {
		setResizable(false);		
		//1. Create Controller to delegate
		reportController = new ReportController(objConn);
		
		//2. Params for Presentation
		setTitle(titleString);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 884, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		panelMenu.setLayout(new GridLayout(8, 1, 0, 0));//(row, col)
		
		//3. Handling button Student Achievement
		JButton btnStudentAchievement = new JButton ("1.Student Achievement");
		btnStudentAchievement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				DefaultTableModel dftModel;
				//1: spReportStudentMarkExamSubjectLecturer
				byte bOpt = 1;
				dftModel = reportController.GetDTMAll(bOpt);
				jtableData.setModel(dftModel);											
			}
		});
		panelMenu.add(btnStudentAchievement);
		
		
		//3. Handling button Lecturer-Subject
		JButton btnLectureSubject = new JButton ("2.Lecturer-Subject Assignment");
		btnLectureSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				DefaultTableModel dftModel;
				//2: [spReportLecturersSubjectsAssigment]
				//2: Lecturer-Subject"
				byte bOpt = 2;
				dftModel = reportController.GetDTMAll(bOpt);
				jtableData.setModel(dftModel);											
			}
		});
		panelMenu.add(btnLectureSubject);
		
		
		//3. Handling button Major Subject Regulation
		JButton btnMajorSubjectRegulation = new JButton ("3.Major-Subject Regulation");
		btnMajorSubjectRegulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				DefaultTableModel dftModel;
				//3: [spReportMajorSubjectRegulation]
				byte bOpt = 3;
				dftModel = reportController.GetDTMAll(bOpt);
				jtableData.setModel(dftModel);											
			}
		});
		panelMenu.add(btnMajorSubjectRegulation);
		
		
		
		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel(titleString);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTitle.add(lbTitle);
		
		JPanel panelRenderData = new JPanel();
		contentPane.add(panelRenderData, BorderLayout.CENTER);
		panelRenderData.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 628, 420);
		panelRenderData.add(scrollPane);
		
		jtableData = new JTable();
		scrollPane.setViewportView(jtableData);				
	}
}
