package Models;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.SubjectEntity;
public class SubjectModel extends BaseModel<SubjectEntity> {

	public SubjectModel(Connection objConnection) {
		super(objConnection);
	}

	@Override
	public List<SubjectEntity> GetElements() throws SQLException {
		List<SubjectEntity> listSubject = new ArrayList<>();
		mCallStatement = mConnection.prepareCall("{call spListAllSubjects}");
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			int monID = mResultset.getInt("Subject ID");
			String tenMon = mResultset.getString("Subject Name");
			String moTa = mResultset.getString("Description");
			SubjectEntity objSubject = new SubjectEntity(monID, tenMon, moTa);
			listSubject.add(objSubject);			
		}
		System.out.println("model : " + listSubject.size());
		return listSubject;
	}
	
	public ResultSet GetElementsRS() throws SQLException {
		mCallStatement = mConnection.prepareCall("{call spListAllSubjects}");
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}


	@Override
	public List<SubjectEntity> GetElementById(String sId) throws SQLException {
		//1. Create a list to hold result
		List<SubjectEntity> listSubject = new ArrayList<>();
		
		//2. Communicate with db to retrieve data
		mCallStatement = mConnection.prepareCall("{call spGetSubjectByID(?)}");
		mCallStatement.setInt("SubjectID", Integer.parseInt(sId));
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			int monID = mResultset.getInt("Subject ID");
			String tenMon = mResultset.getString("Subject Name");
			String moTa = mResultset.getString("Description");
			SubjectEntity objSubject = new SubjectEntity(monID, tenMon, moTa);
			listSubject.add(objSubject);			
		}
		System.out.println("model : " + listSubject.size());
		return listSubject;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		mCallStatement = mConnection.prepareCall("{call spGetSubjectByID(?)}");
		mCallStatement.setInt("SubjectID", Integer.parseInt(sId));
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	
	@Override
	public boolean InsertElement(SubjectEntity objT) throws SQLException {
		int numOfRowsAffected = 0;
		try {
			mCallStatement = this.mConnection.prepareCall("{call spInsertASubject(?,?,?)}");
			mCallStatement.setInt("SubjectID", objT.getiSubjectID());
			mCallStatement.setString("SubjectName", objT.getsSubjectName());
			mCallStatement.setString("Description", objT.getsDescription());
			
			numOfRowsAffected = mCallStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		System.out.println("\nNumber of Row affected: " + numOfRowsAffected);
		if(numOfRowsAffected >= 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean UpdateElement(SubjectEntity objT) throws SQLException {
		int numOfRowsAffected = 0;
		String sqlInsert = "{call spUpdateASubject(?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlInsert);
			mCallStatement.setInt("SubjectID", objT.getiSubjectID());
			mCallStatement.setString("SubjectName", objT.getsSubjectName());
			mCallStatement.setString("Description", objT.getsDescription());
			
			numOfRowsAffected = mCallStatement.executeUpdate();
			System.out.println("\nNumber of Row affected in UpdateStudent: " + numOfRowsAffected);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		if(numOfRowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean DeleteElement(SubjectEntity objT) throws SQLException {
		return false;
	}

	
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		int numberRowsAffected = 0;		
		String sqlString = "{call spDeleteByID(?,?,?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setString("TableName", "MonHoc");
		mCallStatement.setString("IDColumnName","MonID");
		mCallStatement.setInt("IDNumber", Integer.parseInt(sID));			
	
		numberRowsAffected = mCallStatement.executeUpdate();
			
		if(numberRowsAffected >= 1){
			return true;
		}else{
			return false;
		}		
	}


	@Override
	public List<SubjectEntity> SearchElementByName(String sName) throws SQLException {
		//1. Create a list to hold the result
		List<SubjectEntity> listResult = new ArrayList<>();
		
		//2. Communicate with db to retrieve data
		mCallStatement = this.mConnection.prepareCall("{call spGetSubjectByName(?)}");
		mCallStatement.setString("SubjectName", sName);
		mResultset = mCallStatement.executeQuery();
		
		while (mResultset.next()){
			int monID = mResultset.getInt("Subject ID");
			String tenMon = mResultset.getString("Subject Name");
			String moTa = mResultset.getString("Description");
			SubjectEntity objSubject = new SubjectEntity(monID, tenMon, moTa);
			listResult.add(objSubject);
		}
		return listResult;
	}

	
	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		mCallStatement = this.mConnection.prepareCall("{call spGetSubjectByName(?)}");
		mCallStatement.setString("SubjectName", sName);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	
	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "MonHoc");
		mCallStatement.setString("IDColumnName","MonID");
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;

	}	

}
