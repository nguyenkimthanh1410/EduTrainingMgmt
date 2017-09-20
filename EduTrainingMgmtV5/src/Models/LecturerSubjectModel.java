package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.LecturerSubjectEntity;

public class LecturerSubjectModel extends BaseModel<LecturerSubjectEntity>{
	
	public LecturerSubjectModel(Connection objConnection) {
		super(objConnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<LecturerSubjectEntity> GetElements() throws SQLException {
		//1. Declare List<LecturerSubjectEntity> to hold the result 
		List<LecturerSubjectEntity> listResult = new ArrayList<LecturerSubjectEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spListAllLecturersSubjects}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		
		while(mResultset.next()){
			int iLecturerSubjectID = mResultset.getInt("LecSub ID");
			int iLecturerID = mResultset.getInt("Lecturer ID"); 
			int iSubjectID = mResultset.getInt("Subject ID");			
			LecturerSubjectEntity obj = new LecturerSubjectEntity(iLecturerSubjectID, iLecturerID, iSubjectID);
			listResult.add(obj);
		}
		
		return listResult;
	}

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		String sqlString = "{call spListAllLecturersSubjects}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	@Override
	public List<LecturerSubjectEntity> GetElementById(String sId) throws SQLException {
		//1. Declare List<LecturerSubjectEntity> to hold the result
		List<LecturerSubjectEntity> listResult = new ArrayList<LecturerSubjectEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spGetLecturerSubjectByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("LecturerSubjectID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();		
		while(mResultset.next()){
			int iLecturerSubjectID = mResultset.getInt("LecSub ID");
			int iLecturerID = mResultset.getInt("Lecturer ID"); 
			int iSubjectID = mResultset.getInt("Subject ID");			
			LecturerSubjectEntity obj = new LecturerSubjectEntity(iLecturerSubjectID, iLecturerID, iSubjectID);
			listResult.add(obj);
		}
		return listResult;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertElement(LecturerSubjectEntity objT) throws SQLException {
		String sqlInsert = "{call spInsertLecturerSubject(?,?,?)}";
		mCallStatement = mConnection.prepareCall(sqlInsert);
		mCallStatement.setInt("LecturerSubjectID", objT.getiLecturerSubjectID());
		mCallStatement.setInt("LecturerID", objT.getiLecturerID());
		mCallStatement.setInt("SubjectID", objT.getiSubjectID());
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean UpdateElement(LecturerSubjectEntity objT) throws SQLException {
		String sqlInsert = "{call spUpdateALecturerSubject(?,?,?)}";
		mCallStatement = mConnection.prepareCall(sqlInsert);
		mCallStatement.setInt("LecturerSubjectID", objT.getiLecturerSubjectID());
		mCallStatement.setInt("LecturerID", objT.getiLecturerID());
		mCallStatement.setInt("SubjectID", objT.getiSubjectID());
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean DeleteElement(LecturerSubjectEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		String sqlDelete = "{call spDeleteByID(?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlDelete);
		mCallStatement.setString("TableName", "GiaoVienMonHoc");
		mCallStatement.setString("IDColumnName","GiaoVienMonHocID");
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
	public List<LecturerSubjectEntity> SearchElementByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";

		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "GiaoVienMonHoc");
		mCallStatement.setString("IDColumnName","GiaoVienMonHocID");	
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;
	}
}
