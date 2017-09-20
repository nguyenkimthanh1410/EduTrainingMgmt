package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entities.LecturerEntity;

public class LecturerModel extends BaseModel<LecturerEntity>{

	public LecturerModel(Connection objConnection) {
		super(objConnection);
	}

	@Override
	public List<LecturerEntity> GetElements() throws SQLException {
		
		List<LecturerEntity> listLecturers = new ArrayList<>();
		
		String sqlListLecturer = "{call spListAllLecturers}";
		mCallStatement = mConnection.prepareCall(sqlListLecturer);
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			int iLecturerID = mResultset.getInt("Lecturer ID");
			String sLecturerName = mResultset.getString("Lecturer Name");
			String sSSN = mResultset.getString("SSN"); //Social Security Number ~ CMTND
			String sAddress = mResultset.getString("Address");
			int iLecturerManager = mResultset.getInt("Supervised By ID");
			int iDeptID = mResultset.getInt("Dept ID");
			LecturerEntity objL = new LecturerEntity(iLecturerID, sLecturerName, sSSN, sAddress, iLecturerManager, iDeptID);
			listLecturers.add(objL);
		}
		System.out.println("LecturerModel says: listAllLecturers: " +listLecturers.size() );
		return listLecturers;		
	}
	
	

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		String sqlListLecturer = "{call spListAllLecturers}";
		mCallStatement = mConnection.prepareCall(sqlListLecturer);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}


	@Override
	public List<LecturerEntity> GetElementById(String sId) throws SQLException {
		List<LecturerEntity> listResult = new ArrayList<LecturerEntity>();
		
		//1. Get ResultSet from DB
		//2. Return list Entity meet the requirement of ID
		String sqlStr = "{call spGetLecturerByID(?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlStr);
			mCallStatement.setInt("LecturerID", Integer.parseInt(sId));
			
			mResultset = mCallStatement.executeQuery();
			
			while(mResultset.next()){
				int iLecturerID = mResultset.getInt("Lecturer ID");
				String sLecturerName = mResultset.getString("Lecturer Name");
				String sSSN = mResultset.getString("SSN");
				String sAddress = mResultset.getString("Address");
				int iLecturerManager = mResultset.getInt("Supervised By ID");
				int iDeptID = mResultset.getInt("Dept ID");
				
				LecturerEntity found = new LecturerEntity(iLecturerID, sLecturerName, sSSN, sAddress, iLecturerManager, iDeptID);
				listResult.add(found);
			}
		} catch (SQLException e) {
			System.out.println("Model GetElementById: "+ e.getMessage());
			e.printStackTrace();
		}		
		return listResult;
	}
	
	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		String sqlStr = "{call spGetLecturerByID(?)}";
	
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setInt("LecturerID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}
	

	@Override
	public boolean InsertElement(LecturerEntity objT) throws SQLException {
		int numOfRowsAffected = 0;
		
		String sqlInsertADepartment = "{call spInsertALecturer(?,?,?,?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlInsertADepartment);			
			mCallStatement.setInt("LecturerID", objT.getiLecturerID());
			mCallStatement.setString("LecturerName", objT.getsLecturerName());
			mCallStatement.setString("SSN", objT.getsSSN());
			mCallStatement.setString("Address", objT.getsAddress());
			mCallStatement.setInt("LecturerManager", objT.getiLecturerManager());
			mCallStatement.setInt("DeptID", objT.getiDeptID());
			
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
	public boolean UpdateElement(LecturerEntity objT) throws SQLException {
		int numOfRowsAffected = 0;
		
		String sqlInsertADepartment = "{call spUpdateALecturer(?,?,?,?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlInsertADepartment);			
			mCallStatement.setInt("LecturerID", objT.getiLecturerID());
			mCallStatement.setString("LecturerName", objT.getsLecturerName());
			mCallStatement.setString("SSN", objT.getsSSN());
			mCallStatement.setString("Address", objT.getsAddress());
			mCallStatement.setInt("LecturerManager", objT.getiLecturerManager());
			mCallStatement.setInt("DeptID", objT.getiDeptID());
			
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
	public boolean DeleteElement(LecturerEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		int numOfRowsAffected = 0;
		
		String sqlDelete = "{call spDeleteByID(?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlDelete);
			mCallStatement.setString("TableName", "GiaoVien");
			mCallStatement.setString("IDColumnName","GiaoVienID");
			mCallStatement.setInt("IDNumber", Integer.parseInt(sID));			
			
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
	public List<LecturerEntity> SearchElementByName(String sName) throws SQLException {
		List<LecturerEntity> listDepts = new ArrayList<LecturerEntity>();
		String sqlSearch = "{call spGetLecturerByName(?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlSearch);			
			mCallStatement.setString("LecturerName",sName);
			mResultset = mCallStatement.executeQuery();	
		
			while(mResultset.next()){
				int iLecturerID = mResultset.getInt("Lecturer ID");
				String sLecturerName = mResultset.getString("Lecturer Name");
				String sSSN = mResultset.getString("SSN");
				String sAddress = mResultset.getString("Address");
				int iLecturerManager = mResultset.getInt("Supervised By ID");
				int iDeptID = mResultset.getInt("Dept ID");
				
				LecturerEntity found = new LecturerEntity(iLecturerID, sLecturerName, sSSN, sAddress, iLecturerManager, iDeptID);
				listDepts.add(found);
			}			
		} catch (SQLException e) {
			System.out.println("Model search: " + e.getMessage());
			e.printStackTrace();
		}		
		return listDepts;
	}
	

	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		String sqlSearch = "{call spGetLecturerByName(?)}";
	
		mCallStatement = this.mConnection.prepareCall(sqlSearch);			
		mCallStatement.setString("LecturerName",sName);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	
	}


	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		
		try {
			mCallStatement = this.mConnection.prepareCall(sqlStr);
			mCallStatement.setString("TableName", "GiaoVien");
			mCallStatement.setString("IDColumnName","GiaoVienID");
			
			mResultset = mCallStatement.executeQuery();
			while(mResultset.next()){
				IDMax = mResultset.getInt("MaxID");
			}
		} catch (SQLException e1) {
			System.out.println("Model Lecturer says: " + e1.getMessage());
			//e1.printStackTrace();
		}		
		return IDMax;
	}	

}
