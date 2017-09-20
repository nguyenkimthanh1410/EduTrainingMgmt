package Entities;

public class SubjectEntity {
	private int iSubjectID;
	private String sSubjectName;
	private String sDescription;
	
	public SubjectEntity(){
		
	}
	public SubjectEntity(int iSubjectID, String sSubjectName, String sDescription){
		this.iSubjectID = iSubjectID;
		this.sSubjectName = sSubjectName;
		this.sDescription = sDescription;
	}
	public int getiSubjectID() {
		return iSubjectID;
	}
	public void setiSubjectID(int iSubjectID) {
		this.iSubjectID = iSubjectID;
	}
	public String getsSubjectName() {
		return sSubjectName;
	}
	public void setsSubjectName(String sSubjectName) {
		this.sSubjectName = sSubjectName;
	}
	public String getsDescription() {
		return sDescription;
	}
	public void setsDescription(String sDescription) {
		this.sDescription = sDescription;
	}
	
	@Override
	public String toString() {
		return String.format("%d: %s", iSubjectID, sSubjectName);
	}
	

}
