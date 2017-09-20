package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.MajorSubjectEntity;

public class MajorSubjectModel extends BaseModel<MajorSubjectEntity>{
	
	public MajorSubjectModel(Connection objConnection) {
		super(objConnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<MajorSubjectEntity> GetElements() throws SQLException {
		//1. Declare List<MajorSubjectEntity> to hold the result 
		List<MajorSubjectEntity> listResult = new ArrayList<MajorSubjectEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spListAllMajorSubject}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		
		while(mResultset.next()){
			int iMajorSubjectID = mResultset.getInt("MS ID");
			int iMajorID = mResultset.getInt("Major ID");
			int iSubjectID = mResultset.getInt("Subject ID");
			//convert String in ResultSet.Compulsory into boolean value
			boolean boolCompulsory = Boolean.parseBoolean(mResultset.getString("Compulsory"));
			String sNote = mResultset.getString("Note");			
			MajorSubjectEntity obj = new MajorSubjectEntity(iMajorSubjectID, iMajorID, iSubjectID, boolCompulsory, sNote);
			listResult.add(obj);
		}		
		return listResult;
	}

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		String sqlString = "{call spListAllMajorSubject}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<MajorSubjectEntity> GetElementById(String sId) throws SQLException {
		//1. Declare List<LecturerSubjectEntity> to hold the result
		List<MajorSubjectEntity> listResult = new ArrayList<MajorSubjectEntity>();
		
		//2. Communicate with DB to retrieve data
		String sqlString = "{call spGetMajorSubjectByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("MajorSubjectID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();		
		while(mResultset.next()){
			int iMajorSubjectID = mResultset.getInt("MS ID");
			int iMajorID = mResultset.getInt("Major ID");
			int iSubjectID = mResultset.getInt("Subject ID");
			//convert String in ResultSet.Compulsory into boolean value
			boolean boolCompulsory = Boolean.parseBoolean(mResultset.getString("Compulsory"));
			String sNote = mResultset.getString("Note");			
			MajorSubjectEntity obj = new MajorSubjectEntity(iMajorSubjectID, iMajorID, iSubjectID, boolCompulsory, sNote);
			listResult.add(obj);
		}
		return listResult;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		String sqlString = "{call spGetMajorSubjectByID(?)}";
		mCallStatement = mConnection.prepareCall(sqlString);
		mCallStatement.setInt("SubjectBasedClassID", Integer.parseInt(sId));
		
		mResultset = mCallStatement.executeQuery();
		return mResultset;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean InsertElement(MajorSubjectEntity objT) throws SQLException {
		String sqlInsert = "{call spInsertAMajorSubject(?,?,?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlInsert);
		mCallStatement.setInt("MajorSubjectID",objT.getiMajorSubjectID());
		mCallStatement.setInt("MajorID", objT.getiMajorID());
		mCallStatement.setInt("SubjectID", objT.getiSubjectID());
		mCallStatement.setBoolean("Compulsory", objT.isBoolCompulsory());
		mCallStatement.setString("Note", objT.getsNote());		
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean UpdateElement(MajorSubjectEntity objT) throws SQLException {
		String sqlUpdate = "{call spUpdateAMajorSubject(?,?,?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlUpdate);
		mCallStatement.setInt("MajorSubjectID",objT.getiMajorSubjectID());
		mCallStatement.setInt("MajorID", objT.getiMajorID());
		mCallStatement.setInt("SubjectID", objT.getiSubjectID());
		mCallStatement.setBoolean("Compulsory", objT.isBoolCompulsory());
		mCallStatement.setString("Note", objT.getsNote());
		
		int rowsAffected = mCallStatement.executeUpdate();
		if(rowsAffected >=1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean DeleteElement(MajorSubjectEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		String sqlDelete = "{call spDeleteByID(?,?,?)}";
		
		mCallStatement = mConnection.prepareCall(sqlDelete);
		mCallStatement.setString("TableName", "ChuyenNghanhMonHoc");
		mCallStatement.setString("IDColumnName","ChuyenNghanhMonHocID");
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
	public List<MajorSubjectEntity> SearchElementByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		int IDMax = 0;
		String sqlStr = "{call spGetIDMax(?,?)}";
		mCallStatement = mConnection.prepareCall(sqlStr);
		mCallStatement.setString("TableName", "ChuyenNghanhMonHoc");
		mCallStatement.setString("IDColumnName","ChuyenNghanhMonHocID");	
		mResultset = mCallStatement.executeQuery();
		while(mResultset.next()){
			IDMax = mResultset.getInt("MaxID");
		}		
		return IDMax;
	}	
}
