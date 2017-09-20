package Entities;

public class LecturerEntity {
	private int iLecturerID;
	private String sLecturerName;
	private String sSSN; //Social Security Number ~ CMTND
	private String sAddress;
	private int iLecturerManager;
	private int iDeptID;
	
	public LecturerEntity(int iLecturerID, String sLecturerName, 
							String sSSN, String sAddress, 
							int iLecturerManager,int iDeptID) {
		this.iLecturerID = iLecturerID;
		this.sLecturerName = sLecturerName;
		this.sSSN = sSSN;
		this.sAddress = sAddress;
		this.iLecturerManager = iLecturerManager;
		this.iDeptID = iDeptID;
	}

	public LecturerEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getiLecturerID() {
		return iLecturerID;
	}

	public void setiLecturerID(int iLecturerID) {
		this.iLecturerID = iLecturerID;
	}

	public String getsLecturerName() {
		return sLecturerName;
	}

	public void setsLecturerName(String sLecturerName) {
		this.sLecturerName = sLecturerName;
	}

	public String getsSSN() {
		return sSSN;
	}

	public void setsSSN(String sSSN) {
		this.sSSN = sSSN;
	}

	public String getsAddress() {
		return sAddress;
	}

	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public int getiLecturerManager() {
		return iLecturerManager;
	}

	public void setiLecturerManager(int iLecturerManager) {
		this.iLecturerManager = iLecturerManager;
	}

	public int getiDeptID() {
		return iDeptID;
	}

	public void setiDeptID(int iDeptID) {
		this.iDeptID = iDeptID;
	}
	
	public String toString(){
		return String.format("%d : %s", iLecturerID, sLecturerName);		
	}

}
