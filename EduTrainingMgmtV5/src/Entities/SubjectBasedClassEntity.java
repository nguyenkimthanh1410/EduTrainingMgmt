package Entities;

public class SubjectBasedClassEntity {
	int iSubjectBasedCLassID;
	int iSubjectID;
	int iLecturerID;
	String sNote;
	
	public SubjectBasedClassEntity(int iSubjectBasedCLassID, int iSubjectID, int iLecturerID, String sNote) {
		this.iSubjectBasedCLassID = iSubjectBasedCLassID;
		this.iSubjectID = iSubjectID;
		this.iLecturerID = iLecturerID;		
		this.sNote = sNote;
	}

	public SubjectBasedClassEntity() {
		// TODO Auto-generated constructor stub
	}


	public int getiSubjectBasedClassID() {
		return iSubjectBasedCLassID;
	}


	public void setiSubjectBasedClassID(int iSubjectBasedClassID) {
		this.iSubjectBasedCLassID = iSubjectBasedClassID;
	}


	public int getiLecturerID() {
		return iLecturerID;
	}


	public void setiLecturerID(int iLecturerID) {
		this.iLecturerID = iLecturerID;
	}


	public int getiSubjectID() {
		return iSubjectID;
	}


	public void setiSubjectID(int iSubjectID) {
		this.iSubjectID = iSubjectID;
	}
	
	@Override
	public String toString() {
		return String.format("%d:%d:%d",this.iSubjectBasedCLassID, this.iSubjectID, this.iLecturerID);
	}

	public String getsNote() {
		return sNote;
	}

	public void setsNote(String sNote) {
		this.sNote = sNote;
	}	
}
