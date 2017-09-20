package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.StudentEntity;

public class StudentModel 
				extends BaseModel<StudentEntity> {

	public StudentModel(Connection objConnection){
		super(objConnection);
	}
	
	@Override
	public List<StudentEntity> GetElements() throws SQLException {
		List<StudentEntity> listStudent = new ArrayList<>();
		
		String sqlListStudent = "{call spListAllStudents}";
		mCallStatement = mConnection.prepareCall(sqlListStudent);
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			int id = mResultset.getInt("Student ID");
			String fullname = mResultset.getString("Full Name");
			java.util.Date dob = mResultset.getDate("DOB");
			String address = mResultset.getString("Address");
			int schoolID = mResultset.getInt("School ID");
			StudentEntity objS = new StudentEntity(id,fullname, dob, address, schoolID);
			listStudent.add(objS);
		}
		return listStudent;		
	}

	
	@Override
	public ResultSet GetElementsRS() throws SQLException {
		List<StudentEntity> listStudent = new ArrayList<>();
		
		String sqlListStudent = "{call spListAllStudents}";
		mCallStatement = mConnection.prepareCall(sqlListStudent);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	
	@Override
	public List<StudentEntity> GetElementById(String sId) {
		StudentEntity foundStudent = null;
		//1. Get ResultSet from DB
		String sqlStr = "{call spGetStudentByID(?)}";
	
		try {
			mCallStatement = this.mConnection.prepareCall(sqlStr);
			mCallStatement.setInt("StudentID", Integer.parseInt(sId));
			
			mResultset = mCallStatement.executeQuery();
			
			while(mResultset.next()){
				int id = mResultset.getInt("Student ID");
				String fullname = mResultset.getString("Full Name");
				java.util.Date dob = mResultset.getDate("DOB");
				String address = mResultset.getString("Address");
				int schoolID = mResultset.getInt("School ID");
				foundStudent = new StudentEntity(id,fullname, dob, address, schoolID);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		//2. Binding value of ResultSet into Student obj
		List<StudentEntity> listResult = new ArrayList<StudentEntity>();
		listResult.add(foundStudent);
		return listResult;
	}

	
	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		//1. Get ResultSet from DB
		String sqlStr = "{call spGetStudentByID(?)}";
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setInt("StudentID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	
	
	@Override
	public boolean InsertElement(StudentEntity objT) {
		int numOfRowsAffected = 0;
		
		String sqlInsertAStudent = "{call spInsertAStudent(?,?,?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlInsertAStudent);
			mCallStatement.setInt("StudentID", objT.getId());
			mCallStatement.setString("FullName", objT.getFullname());
			
			//convert java.util.Date into java.sql.Date
			//https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
			java.util.Date utilDOB = objT.getDob();
			java.sql.Date sqlDate = new java.sql.Date(utilDOB.getTime());
			mCallStatement.setDate("DOB", sqlDate);
			
			mCallStatement.setString("Address", objT.getAddress());
			mCallStatement.setInt("SchoolID", objT.getSchoolID());
			
			numOfRowsAffected = mCallStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		System.out.println("\nNumber of Row affected: " + numOfRowsAffected);
		if(numOfRowsAffected>=1){
			return true;
		}else{
			return false;
		}
	}
	

	@Override
	public boolean UpdateElement(StudentEntity objT) {
		int numOfRowsAffected = 0;
		String sqlInsertAStudent = "{call spUpdateAStudent(?,?,?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlInsertAStudent);
			mCallStatement.setInt("StudentID", objT.getId());
			mCallStatement.setString("FullName", objT.getFullname());
			
			//convert java.util.Date into java.sql.Date
			//https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
			java.util.Date utilDOB = objT.getDob();
			java.sql.Date sqlDate = new java.sql.Date(utilDOB.getTime());
			mCallStatement.setDate("DOB", sqlDate);
			
			mCallStatement.setString("Address", objT.getAddress());
			mCallStatement.setInt("SchoolID", objT.getSchoolID());
			
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
	public boolean DeleteElement(StudentEntity objT) {
		return false;
	}


	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		int numberRowsAffected = 0;
		
		String sqlString = "{call spDeleteByID(?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlString);
			mCallStatement.setString("TableName", "SinhVien");
			mCallStatement.setString("IDColumnName","SinhVienID");
			mCallStatement.setInt("IDNumber", Integer.parseInt(sID));			
			
			numberRowsAffected = mCallStatement.executeUpdate();
		} catch (SQLException e) {			
			//e.printStackTrace();
			System.out.println("Student Model say:");
		}	
		if(numberRowsAffected>=1){
			System.out.println("Model says: Deleted rows: "+numberRowsAffected );
			return true;
		}else{
			return false;
		}		
	}



	@Override
	public List<StudentEntity> SearchElementByName(String sName) throws SQLException {
		List<StudentEntity> listResult = new ArrayList<StudentEntity>();
		//1. Get ResultSet from DB
		String sqlStr = "{call spGetStudentByName(?)}";
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("StudentName", sName);				
		mResultset = mCallStatement.executeQuery();			
		
		while(mResultset.next()){
			int id = mResultset.getInt("Student ID");
			String fullname = mResultset.getString("Full Name");
			java.util.Date dob = mResultset.getDate("DOB");
			String address = mResultset.getString("Address");
			int schoolID = mResultset.getInt("School ID");
			StudentEntity objS = new StudentEntity(id,fullname, dob, address, schoolID);
			listResult.add(objS);
		}				
		return listResult;
	}

	
	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		List<StudentEntity> listResult = new ArrayList<StudentEntity>();
		//1. Get ResultSet from DB
		String sqlStr = "{call spGetStudentByName(?)}";
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("StudentName", sName);				
		mResultset = mCallStatement.executeQuery();			
		
		return mResultset;
	}

	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "SinhVien");
		mCallStatement.setString("IDColumnName","SinhVienID");
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;
	}	
}
