package Entities;

public class LecturerSubjectEntity {
	int iLecturerSubjectID;
	int iLecturerID;
	int iSubjectID;	
	
	public LecturerSubjectEntity(int iLecturerSubjectID, int iLecturerID, int iSubjectID) {
		this.iLecturerSubjectID = iLecturerSubjectID;
		this.iLecturerID = iLecturerID;	
		this.iSubjectID = iSubjectID;			
	}

	public LecturerSubjectEntity() {
		// TODO Auto-generated constructor stub
	}


	public int getiLecturerSubjectID() {
		return iLecturerSubjectID;
	}


	public void setiLecturerSubjectID(int iLecturerSubjectID) {
		this.iLecturerSubjectID = iLecturerSubjectID;
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
		return String.format("%d : %d : %d",  iLecturerSubjectID, iLecturerID, iSubjectID);
	}	
}
