package Entities;

public class MarkEntity {
	int iMarkID;
	int iExamID;
	int iSubjectClassRegistrationID;
	float mark;
	
	public MarkEntity(int iMarkID, int iExamID, int iSubjectClassRegistrationID, float mark) {
		this.iMarkID = iMarkID;
		this.iExamID = iExamID;
		this.iSubjectClassRegistrationID = iSubjectClassRegistrationID;
		this.mark = mark;
	}

	public MarkEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getiMarkID() {
		return iMarkID;
	}

	public void setiMarkID(int iMarkID) {
		this.iMarkID = iMarkID;
	}

	public int getiExamID() {
		return iExamID;
	}

	public void setiExamID(int iExamID) {
		this.iExamID = iExamID;
	}

	public int getiSubjectClassRegistrationID() {
		return iSubjectClassRegistrationID;
	}

	public void setiSubjectClassRegistrationID(int iSubjectClassID) {
		this.iSubjectClassRegistrationID = iSubjectClassID;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}
	
	@Override
	public String toString() {
		String s = String.format("%d:%d:%d", iMarkID, iExamID, iSubjectClassRegistrationID);
		return s;
	}
	
}
