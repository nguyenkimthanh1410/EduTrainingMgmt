package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.SchoolEntity;

public class SchoolModel extends BaseModel<SchoolEntity> {

	public SchoolModel(Connection objConnection) {
		super(objConnection);
	}

	@Override
	public List<SchoolEntity> GetElements() throws SQLException {
		List<SchoolEntity> listSchoolsResult = new ArrayList<SchoolEntity>();
		
		//1. Get ResultSet from DB
		//2. Binding each record into School obj
		//3. Add each School obj into listSchoolsResult				
		String sqlStr = "{call spListSchools}";
		try {
			CallableStatement objCstm = this.mConnection.prepareCall(sqlStr);
			ResultSet objRS = objCstm.executeQuery();
			
			while(objRS.next()){
				SchoolEntity objSchool = new SchoolEntity();
				int schoolID = objRS.getInt("School ID");
				String schoolName = objRS.getString("School Name");
				String address = objRS.getString("Address");
				String email = objRS.getString("Email");
				String phone = objRS.getString("Phone");
				objSchool.setSchoolID(schoolID);
				objSchool.setSchoolName(schoolName);
				objSchool.setAddress(address);
				objSchool.setEmail(email);
				objSchool.setPhone(phone);
				
				listSchoolsResult.add(objSchool);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return listSchoolsResult;
	}

	@Override
	public List<SchoolEntity> GetElementById(String sId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertElement(SchoolEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdateElement(SchoolEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteElement(SchoolEntity objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SchoolEntity> SearchElementByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxID() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultSet GetElementsRS() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet GetElementByIdRS(String sId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet SearchElementByNameRS(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
