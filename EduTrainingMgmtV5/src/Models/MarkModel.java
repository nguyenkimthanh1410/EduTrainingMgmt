package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.MarkEntity;

public class MarkModel extends BaseModel<MarkEntity>{
	
	public MarkModel(Connection objConnection) {
		super(objConnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MarkEntity> GetElements() throws SQLException {
		//1. Declare List<LecturerSubjectEntity> to hold the result 
		List<MarkEntity> listResult = new ArrayList<MarkEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spListAllMarks}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		
		while(mResultset.next()){
			int iMarkID = mResultset.getInt("Mark ID");
			int iExamID = mResultset.getInt("Exam ID");
			int iSubjectClassID = mResultset.getInt("StudentCode@Exam");//SubjectClassRegistrationID for each student
			float mark = mResultset.getFloat("Mark");
			
			MarkEntity obj = new MarkEntity(iMarkID, iExamID, iSubjectClassID, mark);
			listResult.add(obj);
		}
		
		return listResult;
	}

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		String sqlString = "{call spListAllMarks}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		
		return mResultset;
	}

	@Override
	public List<MarkEntity> GetElementById(String sId) throws SQLException {
		//1. Declare a list to hold the result
		List<MarkEntity> listResult = new ArrayList<MarkEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spGetMarkByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("MarkID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();		
		while(mResultset.next()){
			int iMarkID = mResultset.getInt("Mark ID");
			int iExamID = mResultset.getInt("Exam ID");
			int iSubjectClassID = mResultset.getInt("StudentCode@Exam");//SubjectClassRegistrationID for each student
			float mark = mResultset.getFloat("Mark");
			
			MarkEntity obj = new MarkEntity(iMarkID, iExamID, iSubjectClassID, mark);
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
	public boolean InsertElement(MarkEntity objT) throws SQLException {
		String sqlInsert = "{call [spInsertAMark](?,?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlInsert);
		mCallStatement.setInt("MarkID", objT.getiMarkID());
		mCallStatement.setInt("ExamID", objT.getiExamID());
		mCallStatement.setInt("SubjectClassRegistrationID", objT.getiSubjectClassRegistrationID());
		mCallStatement.setFloat("Mark", objT.getMark());
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean UpdateElement(MarkEntity objT) throws SQLException {
		String sqlInsert = "{call [spUpdateAMark](?,?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlInsert);
		mCallStatement.setInt("MarkID", objT.getiMarkID());
		mCallStatement.setInt("ExamID", objT.getiExamID());
		mCallStatement.setInt("SubjectClassRegistrationID", objT.getiSubjectClassRegistrationID());
		mCallStatement.setFloat("Mark", objT.getMark());
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean DeleteElement(MarkEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		String sqlDelete = "{call spDeleteByID(?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlDelete);
		mCallStatement.setString("TableName", "DiemThi");
		mCallStatement.setString("IDColumnName","DiemThiID");
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
	public List<MarkEntity> SearchElementByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";

		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "DiemThi");
		mCallStatement.setString("IDColumnName","DiemThiID");	
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;
	}
}
