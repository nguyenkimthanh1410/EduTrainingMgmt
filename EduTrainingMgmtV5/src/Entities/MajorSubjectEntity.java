package Entities;

public class MajorSubjectEntity {
	
	int iMajorSubjectID;
	int iMajorID;
	int iSubjectID;
	boolean boolCompulsory;
	String sNote;
	
	public MajorSubjectEntity(int iMajorSubjectID, int iMajorID, int iSubjectID, boolean boolCompulsory, String sNote) {
		this.iMajorSubjectID = iMajorSubjectID;
		this.iMajorID = iMajorID;
		this.iSubjectID = iSubjectID;
		this.boolCompulsory = boolCompulsory;
		this.sNote = sNote;		
	}

	public MajorSubjectEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getiMajorSubjectID() {
		return iMajorSubjectID;
	}

	public void setiMajorSubjectID(int iMajorSubjectID) {
		this.iMajorSubjectID = iMajorSubjectID;
	}

	public int getiMajorID() {
		return iMajorID;
	}

	public void setiMajorID(int iMajorID) {
		this.iMajorID = iMajorID;
	}

	public int getiSubjectID() {
		return iSubjectID;
	}

	public void setiSubjectID(int iSubjectID) {
		this.iSubjectID = iSubjectID;
	}

	public boolean isBoolCompulsory() {
		return boolCompulsory;
	}

	public void setBoolCompulsory(boolean boolCompulsory) {
		this.boolCompulsory = boolCompulsory;
	}

	public String getsNote() {
		return sNote;
	}

	public void setsNote(String sNote) {
		this.sNote = sNote;
	}

	@Override
	public String toString() {
		return "MajorSubjectEntity [iMajorSubjectID=" + iMajorSubjectID + ", iMajorID=" + iMajorID + ", iSubjectID="
				+ iSubjectID + ", boolCompulsory=" + boolCompulsory + ", sNote=" + sNote + "]";
	}	
}
