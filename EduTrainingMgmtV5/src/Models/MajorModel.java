package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.MajorEntity;

public class MajorModel 
				extends BaseModel<MajorEntity> {

	public MajorModel(Connection objConnection){
		super(objConnection);
	}
	
	@Override
	public List<MajorEntity> GetElements() throws SQLException {
		List<MajorEntity> listMajor = new ArrayList<>();
		
		String sqlListMajor = "{call spListAllMajors}";
		mCallStatement = mConnection.prepareCall(sqlListMajor);
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			int iMajorId = mResultset.getInt("Major ID");
			String sMajorName = mResultset.getString("Major Name");
			int iDepartment = mResultset.getInt("Dept ID");
			
			MajorEntity objM = new MajorEntity(iMajorId, sMajorName, iDepartment);
			listMajor.add(objM);
		}
		
		System.out.println("Model: Num of records =" +listMajor.size());
		return listMajor;		
	}
	
	@Override
	public ResultSet GetElementsRS() throws SQLException {
		List<MajorEntity> listMajor = new ArrayList<>();
		
		String sqlListMajor = "{call spListAllMajors}";
		mCallStatement = mConnection.prepareCall(sqlListMajor);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}


	@Override
	public List<MajorEntity> GetElementById(String sId) {
		MajorEntity found = null;
		String sqlStr = "{call spGetMajorByID(?)}";
	
		try {
			mCallStatement = mConnection.prepareCall(sqlStr);
			mCallStatement.setInt("MajorID", Integer.parseInt(sId));
			
			mResultset = mCallStatement.executeQuery();
			
			while(mResultset.next()){
				int iMajorId = mResultset.getInt("Major ID");
				String sMajorName = mResultset.getString("Major Name");
				int iDepartment = mResultset.getInt("Dept ID");
				found = new MajorEntity(iMajorId, sMajorName,iDepartment);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		//2. Binding value of ResultSet into Major obj
		List<MajorEntity> listResult = new ArrayList<MajorEntity>();
		listResult.add(found);
		return listResult;
	}

	
	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		String sqlStr = "{call spGetMajorByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setInt("MajorID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;	
	}
	

	@Override
	public boolean InsertElement(MajorEntity objT) {
		int numOfRowsAffected = 0;
		
		String sqlInsertAMajor = "{call spInsertAMajor(?,?,?) }";
		CallableStatement objStm;
		try {
			objStm = this.mConnection.prepareCall(sqlInsertAMajor);
			objStm.setInt("NghanhID", objT.getiMajorID());
			objStm.setString("TenNghanh", objT.getsMajorName());
			objStm.setInt("KhoaID", objT.getiDepartmentID());
			
			numOfRowsAffected = objStm.executeUpdate();
			
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
	public boolean UpdateElement(MajorEntity objT) {
		int numOfRowsAffected = 0;
		String sqlInsertAMajor = "{call spUpdateAMajor(?,?,?)}";
		CallableStatement objStm;
		try {
			objStm = this.mConnection.prepareCall(sqlInsertAMajor);
			objStm.setInt("MajorID", objT.getiMajorID());
			objStm.setString("MajorName", objT.getsMajorName());
			objStm.setInt("DeptID", objT.getiDepartmentID());
			
			numOfRowsAffected = objStm.executeUpdate();
			//System.out.println("\nNumber of Row affected in UpdateStudent: " + numOfRowsAffected);
			
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
	public boolean DeleteElement(MajorEntity objT) {
		return false;
	}
	

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		int numberRowsAffected = 0;
		
		String sqlString = "{call spDeleteByID(?,?,?)}";
		try {
			mCallStatement = mConnection.prepareCall(sqlString);
			mCallStatement.setString("TableName", "ChuyenNghanh");
			mCallStatement.setString("IDColumnName","NghanhID");
			mCallStatement.setInt("IDNumber", Integer.parseInt(sID));			
			
			numberRowsAffected = mCallStatement.executeUpdate();
		} catch (SQLException e) {			
			//e.printStackTrace();
			System.out.println("Model Delete: Fail to delete: " + e.getMessage());
			
		}	
		if(numberRowsAffected>=1){
			return true;
		}else{
			return false;
		}		
	}

	
	@Override
	public List<MajorEntity> SearchElementByName(String sName) throws SQLException {
		List<MajorEntity> listResult = new ArrayList<MajorEntity>();
		//1. Get ResultSet from DB
		String sqlStr = "{call spGetMajorByName(?)}";
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("MajorName", sName);				
		mResultset = mCallStatement.executeQuery();	
		
		while(mResultset.next()){
			int iMajorId = mResultset.getInt("Major ID");
			String sMajorName = mResultset.getString("Major Name");
			int iDepartment = mResultset.getInt("Dept ID");
			MajorEntity objM = new MajorEntity( iMajorId, sMajorName, iDepartment);
			listResult.add(objM);
		}
		System.out.println("Model search: "+ listResult.size());
		return listResult;
	}

	

	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		//1. Get ResultSet from DB
		String sqlStr = "{call spGetMajorByName(?)}";
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("MajorName", sName);				
		mResultset = mCallStatement.executeQuery();	
		return mResultset;
	}

	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		
		mCallStatement = this.mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "ChuyenNghanh");
		mCallStatement.setString("IDColumnName","NghanhID");
		
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}
		return IDMax;
	}	
	
}
