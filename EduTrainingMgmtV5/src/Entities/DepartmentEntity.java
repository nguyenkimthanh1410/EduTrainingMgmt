package Entities;

public class DepartmentEntity {
	private int iDeptID;
	private String sDeptName;
	private int iSchoolID;
	private int iDeptSuperviorID;
	
	public DepartmentEntity(int iDeptID, String sDeptName, int iSchoolID, int iDeptSupervisorID) {
		this.iDeptID = iDeptID;
		this.sDeptName = sDeptName;
		this.iSchoolID = iSchoolID;
		this.iDeptSuperviorID = iDeptSupervisorID;
	}

	public DepartmentEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getiDeptID() {
		return iDeptID;
	}

	public void setiDeptID(int iDeptID) {
		this.iDeptID = iDeptID;
	}

	public String getsDeptName() {
		return sDeptName;
	}

	public void setsDeptName(String sDeptName) {
		this.sDeptName = sDeptName;
	}

	public int getiSchoolID() {
		return iSchoolID;
	}

	public void setiSchoolID(int iSchoolID) {
		this.iSchoolID = iSchoolID;
	}

	public int getiDeptSupervisorID() {
		return iDeptSuperviorID;
	}

	public void setiDeptSupervisorID(int iDeptHeadID) {
		this.iDeptSuperviorID = iDeptHeadID;
	}

	@Override
	public String toString() {
		return String.format("%d : %s", iDeptID, sDeptName);
	}
}
