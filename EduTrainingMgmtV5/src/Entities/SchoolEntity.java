package Entities;

public class SchoolEntity {
	private int schoolID;
	private String schoolName;
	private String address;
	private String email;
	private String phone;
	
	public SchoolEntity (){
		
	}
	public SchoolEntity (int schoolID, String schoolName){
		this.schoolID = schoolID;
		this.schoolName = schoolName;
	}
	public SchoolEntity (int schoolID, String schoolName, String address, String email, String phone) {
		super();
		this.schoolID = schoolID;
		this.schoolName = schoolName;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	
	public String toString(){
		return String.format("%d : %s", schoolID, schoolName);
	}
	
	public int getSchoolID() {
		return schoolID;
	}
	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
}
