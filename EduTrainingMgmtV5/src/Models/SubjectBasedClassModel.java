package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.SubjectBasedClassEntity;

public class SubjectBasedClassModel extends BaseModel<SubjectBasedClassEntity>{
	
	public SubjectBasedClassModel(Connection objConnection) {
		super(objConnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<SubjectBasedClassEntity> GetElements() throws SQLException {
		//1. Declare List<LecturerSubjectEntity> to hold the result 
		List<SubjectBasedClassEntity> listResult = new ArrayList<SubjectBasedClassEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spListAllSubjectBasedClass}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		
		while(mResultset.next()){
			int iSubjectBasedCLassID = mResultset.getInt("Class ID");
			int iSubjectID = mResultset.getInt("Subject ID");
			int iLecturerID = mResultset.getInt("Lecturer ID");	
			String sNote = mResultset.getString("Note");
			SubjectBasedClassEntity obj = new SubjectBasedClassEntity(iSubjectBasedCLassID, iSubjectID, iLecturerID, sNote);
			listResult.add(obj);
		}		
		return listResult;
	}

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		String sqlString = "{call spListAllSubjectBasedClass}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<SubjectBasedClassEntity> GetElementById(String sId) throws SQLException {
		//1. Declare List<LecturerSubjectEntity> to hold the result
		List<SubjectBasedClassEntity> listResult = new ArrayList<SubjectBasedClassEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spGetSubjectBasedClassByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("SubjectBasedClassID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();		
		while(mResultset.next()){
			int iSubjectBasedCLassID = mResultset.getInt("Class ID");			 
			int iSubjectID = mResultset.getInt("Subject ID");
			int iLecturerID = mResultset.getInt("Lecturer ID");
			String sNote = mResultset.getString("Note");
			SubjectBasedClassEntity obj = new SubjectBasedClassEntity(iSubjectBasedCLassID, iSubjectID, iLecturerID, sNote);
			listResult.add(obj);
		}
		return listResult;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		String sqlString = "{call spGetSubjectBasedClassByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("SubjectBasedClassID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean InsertElement(SubjectBasedClassEntity objT) throws SQLException {
		String sqlInsert = "{call spInsertASubjectBasedClass(?,?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlInsert);
		mCallStatement.setInt("SubjectBasedClassID", objT.getiSubjectBasedClassID());
		mCallStatement.setInt("SubjectID", objT.getiSubjectID());
		mCallStatement.setInt("LecturerID", objT.getiLecturerID());
		mCallStatement.setString("Note", objT.getsNote());
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean UpdateElement(SubjectBasedClassEntity objT) throws SQLException {
		String sqlUpdate = "{call spUpdateASubjectBasedClass(?,?,?,?)}";
		mCallStatement = mConnection.prepareCall(sqlUpdate);
		mCallStatement.setInt("SubjectBasedClassID", objT.getiSubjectBasedClassID());
		mCallStatement.setInt("SubjectID", objT.getiSubjectID());
		mCallStatement.setInt("LecturerID", objT.getiLecturerID());
		mCallStatement.setString("Note", objT.getsNote());
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean DeleteElement(SubjectBasedClassEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		String sqlDelete = "{call spDeleteByID(?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlDelete);
		mCallStatement.setString("TableName", "LopTinChi");
		mCallStatement.setString("IDColumnName","LopTinChiID");
		mCallStatement.setInt("IDNumber", Integer.parseInt(sID));		
	
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
	public List<SubjectBasedClassEntity> SearchElementByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "LopTinChi");
		mCallStatement.setString("IDColumnName","LopTinChiID");
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;

	}
}
