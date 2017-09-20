package Entities;

public class MajorEntity {
	private int iMajorID;
	private String sMajorName;
	private int iDepartmentID;
	
	public MajorEntity(){
		
	}
	
	public MajorEntity(int iMajorID, String sMajorName, int iDepartmentID) {
		super();
		this.iMajorID = iMajorID;
		this.sMajorName = sMajorName;
		this.iDepartmentID = iDepartmentID;
	}

	public int getiMajorID() {
		return iMajorID;
	}

	public void setiMajorID(int iMajorID) {
		this.iMajorID = iMajorID;
	}


	public String getsMajorName() {
		return sMajorName;
	}


	public void setsMajorName(String sMajorName) {
		this.sMajorName = sMajorName;
	}


	public int getiDepartmentID() {
		return iDepartmentID;
	}


	public void setiDepartmentID(int iDepartmentID) {
		this.iDepartmentID = iDepartmentID;
	}
	

	@Override
	public String toString() {
		return String.format("%d - %s", iMajorID, sMajorName);
	}


}
