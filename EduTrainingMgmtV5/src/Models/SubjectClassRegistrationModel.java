package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entities.SubjectClassRegistrationEntity;

public class SubjectClassRegistrationModel extends BaseModel<SubjectClassRegistrationEntity>{
	
	public SubjectClassRegistrationModel(Connection objConnection) {
		super(objConnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SubjectClassRegistrationEntity> GetElements() throws SQLException {
		//1. Declare List<MajorSubjectEntity> to hold the result 
		List<SubjectClassRegistrationEntity> listResult = new ArrayList<SubjectClassRegistrationEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spListAllSubjectClassRegistration}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		
		while(mResultset.next()){
			//get value					
			int iSubjectClassRegistrationID = mResultset.getInt("SCR ID"); 
			int iSubjectBasedClassID = mResultset.getInt("Class ID"); 
			int iStudentID = mResultset.getInt("Student ID");
			Date dRegistrationDate = mResultset.getDate("Registed on");
			String sNote = mResultset.getString("Note");
			
			SubjectClassRegistrationEntity obj =
					new SubjectClassRegistrationEntity(iSubjectClassRegistrationID, iSubjectBasedClassID,
														iStudentID, dRegistrationDate, sNote); 
			listResult.add(obj);
		}		
		return listResult;
	}

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		String sqlString = "{call spListAllSubjectClassRegistration}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<SubjectClassRegistrationEntity> GetElementById(String sId) throws SQLException {
		//1. Declare List<SubjectClassRegistrationEntity> to hold the result
		List<SubjectClassRegistrationEntity> listResult = new ArrayList<SubjectClassRegistrationEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spGetSubjectClassRegistrationByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("SubjectClassRegistrationID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();		
		while(mResultset.next()){
			//get value					
			int iSubjectClassRegistrationID = mResultset.getInt("SCR ID"); 
			int iSubjectBasedClassID = mResultset.getInt("Class ID"); 
			int iStudentID = mResultset.getInt("Student ID");
			Date dRegistrationDate = mResultset.getDate("Registed on");
			String sNote = mResultset.getString("Note");
			
			SubjectClassRegistrationEntity obj =
					new SubjectClassRegistrationEntity(iSubjectClassRegistrationID, iSubjectBasedClassID,
														iStudentID, dRegistrationDate, sNote); 
			listResult.add(obj);
		}
		return listResult;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		String sqlString = "{call spGetSubjectClassRegistrationByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("SubjectBasedClassID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean InsertElement(SubjectClassRegistrationEntity objT) throws SQLException {
		String sqlInsert = "{call [spInsertASubjectClassRegistration](?,?,?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlInsert);
		mCallStatement.setInt("SubjectClassRegistrationID",objT.getiSubjectClassRegistrationID());
		mCallStatement.setInt("SubjectBasedClassID", objT.getiSubjectBasedClassID());
		mCallStatement.setInt("StudentID", objT.getiStudentID());
		
		//convert java.util.Date into java.sql.Date
		//https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
		java.util.Date utilDOB = objT.getdRegistrationDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDOB.getTime());		
		mCallStatement.setDate("RegistrationDate", sqlDate);
		
		mCallStatement.setString("Note", objT.getsNote());		
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean UpdateElement(SubjectClassRegistrationEntity objT) throws SQLException {
		String sqlUpdate = "{call [spUpdateASubjectClassRegistration](?,?,?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlUpdate);
		mCallStatement.setInt("SubjectClassRegistrationID",objT.getiSubjectClassRegistrationID());
		mCallStatement.setInt("SubjectBasedClassID", objT.getiSubjectBasedClassID());
		mCallStatement.setInt("StudentID", objT.getiStudentID());
		
		//convert java.util.Date into java.sql.Date
		//https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
		java.util.Date utilDOB = objT.getdRegistrationDate();
		java.sql.Date sqlDate = new java.sql.Date(utilDOB.getTime());		
		mCallStatement.setDate("RegistrationDate", sqlDate);
		
		mCallStatement.setString("Note", objT.getsNote());		
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean DeleteElement(SubjectClassRegistrationEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		String sqlDelete = "{call spDeleteByID(?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlDelete);
		mCallStatement.setString("TableName", "DangKyLopTinChiSinhVien");
		mCallStatement.setString("IDColumnName","LopTinChiSinhVienID");
		mCallStatement.setInt("IDNumber",Integer.parseInt(sID));		
	
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubjectClassRegistrationEntity> SearchElementByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "DangKyLopTinChiSinhVien");
		mCallStatement.setString("IDColumnName","LopTinChiSinhVienID");	
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}		
		return IDMax;
	}	
}
