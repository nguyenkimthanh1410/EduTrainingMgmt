package Entities;

import java.util.Date;

public class ExamEntity {
	private int iExamID;
	private String sExamName;
	private Date dExamDate;
	
	public ExamEntity(){
		
	}

	public ExamEntity(int iExamID, String sExamName, Date dExamDate) {
		this.iExamID = iExamID;
		this.sExamName = sExamName;
		this.dExamDate = dExamDate;
	}

	public int getiExamID() {
		return iExamID;
	}

	public void setiExamID(int iExamID) {
		this.iExamID = iExamID;
	}

	public String getsExamName() {
		return sExamName;
	}

	public void setsExamName(String sExamName) {
		this.sExamName = sExamName;
	}

	public Date getdExamDate() {
		return dExamDate;
	}

	public void setdExamDate(Date dExamDate) {
		this.dExamDate = dExamDate;
	}
	@Override
	public String toString() {		
		return String.format("%d:%s",iExamID, sExamName);
	}
}
