package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BaseUltils.SQLCons.SQLConnections;

public class frmMainApps extends JFrame {

	// Params for connection db
	private String ServerName = "KIM-PC";
	private String DatabaseName = "EduTrainingMgmtV5";
	private String UserName = "sa";
	private String PassWord = "meorua99";
	private int PortNumber = 1433;
	private Connection objConn;

	// Swing params
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMainApps frame = new frmMainApps();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmMainApps() {

		// 0. Initial setup
		// 0.a. Params for Presentation
		setTitle("ETM Java App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.CENTER);
		panelMenu.setLayout(new GridLayout(3, 4, 10, 10));// (row, col)

		// 0.b. Open a DB connection
		SQLConnections sqlConn = new SQLConnections();
		try {
			// if want to change DB: change the default value here before
			// opening a connection
			objConn = sqlConn.OpenConections(ServerName, DatabaseName, UserName, PassWord, PortNumber);
		} catch (Exception exc) {
			System.out.println("Failed to connect to " + ServerName + "\\" + DatabaseName + ": " + exc.getMessage());
		}

		// 1. Button Student - @Kim
		JButton btnStudent = new JButton("1.Students");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frmStudent frameStudent = new frmStudent(objConn);
				frameStudent.setVisible(true);
			}
		});
		panelMenu.add(btnStudent);

		// 2. Button Lecturer - @An
		JButton btnLecturer = new JButton("2.Lecturers");
		btnLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLecturer frameLecturer = new frmLecturer(objConn);
				frameLecturer.setVisible(true);
			}
		});
		panelMenu.add(btnLecturer);

		// 3. Button Major - @Dac
		JButton btnMajor = new JButton("3.Majors");
		btnMajor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frmMajor frameMajor = new frmMajor(objConn);
				frameMajor.setVisible(true);
			}
		});
		panelMenu.add(btnMajor);

		// 4. Button btnDepartment - @Tu
		JButton btnDepartment = new JButton("4.Departments");
		btnDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDepartment frameDepartment = new frmDepartment(objConn);
				frameDepartment.setVisible(true);
			}
		});
		panelMenu.add(btnDepartment);

		// 5. Button Subject - @Duong
		JButton btnSubject = new JButton("5.Subjects");
		btnSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSubject frameSubject = new frmSubject(objConn);
				frameSubject.setVisible(true);
			}
		});
		panelMenu.add(btnSubject);

		// 6. Button btnLecturerSubject: tblGiaoVienMonHoc - @Tat
		JButton btnLecturerSubject = new JButton("<html>\r\n<p>6.Lecturers-Subjects</p>\r\n<p> Assignment</p>\r\n<hmtl>");
		btnLecturerSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLecturerSubject frameLecturerSubject = new frmLecturerSubject(objConn);
				frameLecturerSubject.setVisible(true);
			}
		});
		panelMenu.add(btnLecturerSubject);

		// 7. Button btnMajorSubject: tblChuyenNghanhMonHoc -@Kim
		JButton btnMajorSubject = new JButton("<html>\r\n<p>7.Majors-Subjects</p>\r\n<p>Regulations</p>\r\n</html>");
		btnMajorSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMajorSubject frameMajorSubject = new frmMajorSubject(objConn);
				frameMajorSubject.setVisible(true);
			}
		});
		panelMenu.add(btnMajorSubject);

		// 8. Button btnSubjectBasedClass: TinChi - @Quynh Anh
		JButton btnSubjectBasedClass = new JButton("8.Subject-based Classes");
		btnSubjectBasedClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSubjectBasedClass frameSubjectBasedClass = new frmSubjectBasedClass(objConn);
				frameSubjectBasedClass.setVisible(true);
			}
		});
		panelMenu.add(btnSubjectBasedClass);
		

		// 9. Button btnStudentClassRegistration: tblDangkyLopTinChi -@Kim
		JButton btnStudentClassRegistration = new JButton("<html>\r\n<p>9.Student Class</p>\r\n<p>Registration</p>\r\n</html>");
		btnStudentClassRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmSubjectClassRegistration frameSubjectClassRegistration = new  frmSubjectClassRegistration(objConn); 
				frameSubjectClassRegistration.setVisible(true);
				//JOptionPane.showConfirmDialog(getParent(), delayImplementation,"Notification", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panelMenu.add(btnStudentClassRegistration);

		// 10. Button btnExamination: tblKyThi -@Kim
		JButton btnExamination = new JButton("10.Examinations");
		btnExamination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("btnExam is clicked");
				frmExam frameExam = new frmExam(objConn);
				frameExam.setVisible(true);			
			}
		});
		panelMenu.add(btnExamination);

		// 11. Button btnMark: tblDiemThi -@Kim
		JButton btnMark = new JButton("11.Marks");
		btnMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMark frameMark = new frmMark(objConn);
				frameMark.setVisible(true);
			}
		});
		panelMenu.add(btnMark);

		// 12. Button btnReports: Bao cao - @Kim
		JButton btnReports = new JButton("12.Reports");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmReports objfrmReports = new frmReports(objConn);
				objfrmReports.setVisible(true);
			}
		});
		panelMenu.add(btnReports);

		JPanel panelTitle = new JPanel();
		contentPane.add(panelTitle, BorderLayout.NORTH);

		JLabel lbTitle = new JLabel("ETM Java App");
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelTitle.add(lbTitle);

		JPanel panelFooter = new JPanel();
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		JLabel lbFooter = new JLabel("<html>\r\n<p>Note: To Edit or Delete a record (1-11). Click LIST ALL button > Right-click on desired record</p>\r\n<p aligned = \"center\">Footer - Status - Explanation if any</p>\r\n</html>\r\n");
		lbFooter.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelFooter.add(lbFooter);

	}

}
