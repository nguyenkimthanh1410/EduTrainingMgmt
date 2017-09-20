package Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Entities.SchoolEntity;
import Models.SchoolModel;

public class SchoolController  extends BaseController<SchoolEntity> {
	private SchoolModel mSchoolModel;
	
	public SchoolController(Connection objConn){
		super(objConn);
		mSchoolModel = new SchoolModel(objConn);		
	}

	@Override
	public List<SchoolEntity> GetDataAll() throws SQLException {
		return mSchoolModel.GetElements();
	}

	@Override
	public List<SchoolEntity> GetDataById(String sID) throws SQLException {
		return null;
	}

	@Override
	public boolean InsertAndUpdateData(SchoolEntity objT, byte bOpt) throws SQLException {
		return false;
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
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

	
}
