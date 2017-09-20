package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.DepartmentEntity;

public class DepartmentModel extends BaseModel<DepartmentEntity>{

	public DepartmentModel(Connection objConnection) {
		super(objConnection);
	}

	@Override
	public List<DepartmentEntity> GetElements() throws SQLException {
		List<DepartmentEntity> listResult = new ArrayList<DepartmentEntity>();
		
		String sqlStr = "{call spListAllDepartments}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mResultset = mCallStatement.executeQuery();
		
		//Iterate over the ResultSet and receive list of Departments
		while(mResultset.next()){
			int iDeptID = mResultset.getInt("Dept ID");
			String sDeptName = mResultset.getString("Dept Name");
			int iSchoolID = mResultset.getInt("School ID");
			int iDeptSupervisorID = mResultset.getInt("Dept Supervisor ID");
			DepartmentEntity objDeptEntity = new DepartmentEntity(iDeptID, sDeptName, iSchoolID, iDeptSupervisorID);
			listResult.add(objDeptEntity);			
		}
		System.out.println("Model says: " + listResult.size());
		return listResult;
	}

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		String sqlStr = "{call spListAllDepartments}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}
	
	@Override
	public List<DepartmentEntity> GetElementById(String sId) throws SQLException {
		List<DepartmentEntity> listResult = new ArrayList<DepartmentEntity>();
		
		//1. Get ResultSet from DB
		//2. Return list DeptEntity meet the requirement of ID
		String sqlStr = "{call spGetDepartmentByID(?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlStr);
			mCallStatement.setInt("DeptID", Integer.parseInt(sId));
			
			mResultset = mCallStatement.executeQuery();
			
			while(mResultset.next()){
				int iDeptID = mResultset.getInt("Dept ID");
				String sDeptName = mResultset.getString("Dept Name");
				int iSchoolID = mResultset.getInt("School ID");
				int iDeptSupervisorID = mResultset.getInt("Dept Supervisor ID");				
				DepartmentEntity foundDept = new DepartmentEntity(iDeptID, sDeptName, iSchoolID, iDeptSupervisorID);
				listResult.add(foundDept);
			}
		} catch (SQLException e) {
			System.out.println("Model GetElementById: "+ e.getMessage());
			e.printStackTrace();
		}		
		return listResult;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		String sqlStr = "{call spGetDepartmentByID(?)}";
	
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setInt("DeptID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}


	@Override
	public boolean InsertElement(DepartmentEntity objT) throws SQLException {
		int numOfRowsAffected = 0;
		
		String sqlInsertADepartment = "{call spInsertADepartment(?,?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlInsertADepartment);			
			mCallStatement.setInt("DeptID", objT.getiDeptID());
			mCallStatement.setString("DeptName", objT.getsDeptName());
			mCallStatement.setInt("SchoolID", objT.getiSchoolID());			
			mCallStatement.setInt("SupervisorID", objT.getiDeptSupervisorID());
			
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
	public boolean UpdateElement(DepartmentEntity objT) throws SQLException {
		int numOfRowsAffected = 0;
		
		String sqlUpdateADepartment = "{call spUpdateADepartment(?,?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlUpdateADepartment);			
			mCallStatement.setInt("DeptID", objT.getiDeptID());
			mCallStatement.setString("DeptName", objT.getsDeptName());
			mCallStatement.setInt("SchoolID", objT.getiSchoolID());			
			mCallStatement.setInt("SupervisorID", objT.getiDeptSupervisorID());
			
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
	public boolean DeleteElement(DepartmentEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		int numOfRowsAffected = 0;
		
		String sqlDeleteADepartment = "{call spDeleteByID(?,?,?)}";
		try {
			mCallStatement = this.mConnection.prepareCall(sqlDeleteADepartment);			
			mCallStatement.setString("TableName", "Khoa");
			mCallStatement.setString("IDColumnName","KhoaID");
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
	public List<DepartmentEntity> SearchElementByName(String sName) throws SQLException {		
		List<DepartmentEntity> listDepts = new ArrayList<DepartmentEntity>();
		String sqSearchDepartment = "{call spGetDepartmentByName(?)}";
		CallableStatement objStm;
		try {
			objStm = this.mConnection.prepareCall(sqSearchDepartment);			
			objStm.setString("DeptName",sName);
			mResultset = objStm.executeQuery();	
		
			while(mResultset.next()){
				int iDeptID = mResultset.getInt("Dept ID");
				String sDeptName = mResultset.getString("Dept Name");
				int iSchoolID = mResultset.getInt("School ID");
				int iDeptSupervisorID = mResultset.getInt("Dept Supervisor ID");				
				DepartmentEntity de = new DepartmentEntity(iDeptID, sDeptName, iSchoolID, iDeptSupervisorID);
				listDepts.add(de);
			}			
		} catch (SQLException e) {
			System.out.println("Model search: " + e.getMessage());
			e.printStackTrace();
		}		
		return listDepts;
	}
	
	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		String sqSearchDepartment = "{call spGetDepartmentByName(?)}";
		CallableStatement objStm;
		
		objStm = this.mConnection.prepareCall(sqSearchDepartment);			
		objStm.setString("DeptName",sName);
		mResultset = objStm.executeQuery();
		return mResultset;	
	}

	
	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "Khoa");
		mCallStatement.setString("IDColumnName","KhoaID");
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;
	}
}
