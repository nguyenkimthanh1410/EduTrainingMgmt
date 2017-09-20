package Models;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BaseUltils.IOFIles.DateConverter;
import Entities.ExamEntity;
import Entities.SubjectEntity;
public class ExamModel extends BaseModel<ExamEntity> {

	public ExamModel(Connection objConnection) {
		super(objConnection);
	}

	@Override
	public List<ExamEntity> GetElements() throws SQLException {
		List<ExamEntity> listExams = new ArrayList<ExamEntity>();
		
		String sqlQuery = "{call spListAllExams}";
		mCallStatement = mConnection.prepareCall(sqlQuery);
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			int iExamID = mResultset.getInt("Exam ID");
			String sExamName = mResultset.getString("Exam Name");
			Date dExamDate = mResultset.getDate("Exam Date"); //convert sql.Date of ResultSet -> java.Date
			ExamEntity objExam = new ExamEntity(iExamID, sExamName, dExamDate);
			listExams.add(objExam);			
		}
		System.out.println("model : " + listExams.size());
		return listExams;
	}
	
	public ResultSet GetElementsRS() throws SQLException {
		mCallStatement = mConnection.prepareCall("{call spListAllExams}");
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}


	@Override
	public List<ExamEntity> GetElementById(String sId) throws SQLException {
		//1. Create a list to hold result
		List<ExamEntity> listExams = new ArrayList<ExamEntity>();
		
		//2. Communicate with db to retrieve data
		String sqlQuery = "{call spGetExamByID(?)}";		
		mCallStatement = mConnection.prepareCall(sqlQuery);		
		mCallStatement.setInt("ExamID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			int iExamID = mResultset.getInt("Exam ID");
			String sExamName = mResultset.getString("Exam Name");
			Date dExamDate = mResultset.getDate("Exam Date"); //convert sql.Date of ResultSet -> java.Date
			ExamEntity objExam = new ExamEntity(iExamID, sExamName, dExamDate);
			listExams.add(objExam);			
		}
		System.out.println("model : " + listExams.size());
		return listExams;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		String sqlQuery = "{call spGetExamByID(?)}";		
		mCallStatement = mConnection.prepareCall(sqlQuery);		
		mCallStatement.setInt("ExamID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	
	@Override
	public boolean InsertElement(ExamEntity objT) throws SQLException {
		int numOfRowsAffected = 0;

		String sqlStr = "{call spInsertAnExam(?, ?, ?)}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setInt("ExamID", objT.getiExamID());
		mCallStatement.setString("ExamName", objT.getsExamName());
		
		Date utilDate = objT.getdExamDate();		
		mCallStatement.setDate("ExamDate", DateConverter.covertUtilDateToSqlDate(utilDate));
		
		numOfRowsAffected = mCallStatement.executeUpdate();
						
		System.out.println("\nNumber of Row affected: " + numOfRowsAffected);
		if(numOfRowsAffected >= 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean UpdateElement(ExamEntity objT) throws SQLException {
		int numOfRowsAffected = 0;
		String sqlStr = "{call spUpdateAnExam(?,?,?)}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setInt("ExamID", objT.getiExamID());
		mCallStatement.setString("ExamName", objT.getsExamName());
		
		Date utilDate = objT.getdExamDate();		
		mCallStatement.setDate("ExamDate", DateConverter.covertUtilDateToSqlDate(utilDate));
		
		numOfRowsAffected = mCallStatement.executeUpdate();
		System.out.println("\nNumber of Row affected: " + numOfRowsAffected);
				
		if(numOfRowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean DeleteElement(ExamEntity objT) throws SQLException {
		return false;
	}

	
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		int numberRowsAffected = 0;		
		String sqlString = "{call spDeleteByID(?,?,?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setString("TableName", "KyThi");
		mCallStatement.setString("IDColumnName","KyThiID");
		mCallStatement.setInt("IDNumber", Integer.parseInt(sID));			
	
		numberRowsAffected = mCallStatement.executeUpdate();			
		if(numberRowsAffected >= 1){
			return true;
		}else{
			return false;
		}		
	}


	@Override
	public List<ExamEntity> SearchElementByName(String sName) throws SQLException {
		//1. Create a list to hold the result
		List<ExamEntity> listResult = new ArrayList<ExamEntity>();
		
		//2. Communicate with db to retrieve data
		String sqlStr = "{call spGetExamByName(?)}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setString("ExamName", sName);
		
		mResultset = mCallStatement.executeQuery();		
		while (mResultset.next()){
			int iExamID = mResultset.getInt("Exam ID");
			String sExamName = mResultset.getString("Exam Name");
			Date dExamDate = mResultset.getDate("Exam Date"); //convert sql.Date of ResultSet -> java.Date
			ExamEntity objExam = new ExamEntity(iExamID, sExamName, dExamDate);
			listResult.add(objExam);
		}
		return listResult;
	}

	
	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		String sqlStr = "{call spGetExamByName(?)}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setString("ExamName", sName);
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	
	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "KyThi");
		mCallStatement.setString("IDColumnName","KyThiID");
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;

	}	

}
