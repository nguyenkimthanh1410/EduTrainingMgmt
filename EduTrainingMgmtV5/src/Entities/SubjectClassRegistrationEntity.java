package Entities;

import java.util.Date;

public class SubjectClassRegistrationEntity {
	
	int iSubjectClassRegistrationID;
	int iSubjectBasedClassID;
	int iStudentID;
	Date dRegistrationDate;
	String sNote;
	
	public SubjectClassRegistrationEntity(int iSubjectClassRegistrationID, int iSubjectBasedClassID, 
											int iStudentID, Date dRegistrationDate, String sNote) {
		this.iSubjectClassRegistrationID = iSubjectClassRegistrationID;
		this.iSubjectBasedClassID = iSubjectBasedClassID;
		this.iStudentID = iStudentID;
		this.dRegistrationDate = dRegistrationDate;
		this.sNote = sNote;
	}
	
	public SubjectClassRegistrationEntity(){
		
	}

	public int getiSubjectClassRegistrationID() {
		return iSubjectClassRegistrationID;
	}

	public void setiSubjectClassRegistrationID(int iSubjectClassRegistrationID) {
		this.iSubjectClassRegistrationID = iSubjectClassRegistrationID;
	}

	public int getiSubjectBasedClassID() {
		return iSubjectBasedClassID;
	}

	public void setiSubjectBasedClassID(int iSubjectBasedClassID) {
		this.iSubjectBasedClassID = iSubjectBasedClassID;
	}

	public int getiStudentID() {
		return iStudentID;
	}

	public void setiStudentID(int iStudentID) {
		this.iStudentID = iStudentID;
	}

	public Date getdRegistrationDate() {
		return dRegistrationDate;
	}

	public void setdRegistrationDate(Date dRegistrationDate) {
		this.dRegistrationDate = dRegistrationDate;
	}

	public String getsNote() {
		return sNote;
	}

	public void setsNote(String sNote) {
		this.sNote = sNote;
	}
	
	@Override
	public String toString() {
		return String.format("%d:%d:%d", iSubjectClassRegistrationID,iSubjectBasedClassID,iStudentID);
	}
}
