package Entities;

import java.util.Date;

public class StudentEntity {
	private int id;
	private String fullname;
	private java.util.Date dob;
	private String address;
	private int schoolID;	
	
	public StudentEntity(){		
	}

	public StudentEntity(int id, String fullname, Date dob, String address, int schoolID) {
		this.id = id;
		this.fullname = fullname;
		this.dob = dob;
		this.address = address;
		this.schoolID = schoolID;		
	}
	
	public StudentEntity(int id, String fullname, Date dob, String address) {
		this.id = id;
		this.fullname = fullname;
		this.dob = dob;
		this.address = address;		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public java.util.Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}

	@Override
	public String toString() {
		return String.format("%d:%s:%s",id, fullname, dob);		
		
	}
}
