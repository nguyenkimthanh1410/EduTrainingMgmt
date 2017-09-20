package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Models.ReportModel;

public class ReportController extends BaseController<Object>{
	private ReportModel mReportModel;
	
	public ReportController(Connection objConn) {
		super(objConn);
		mReportModel = new ReportModel(objConn);
	}

	@Override
	public List<Object> GetDataAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> GetDataById(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertAndUpdateData(Object objT, byte bOpt) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
	
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

	//bOpt = 1: spReportStudentMarkExamSubjectLecturer
	public DefaultTableModel GetDTMAll(byte bOpt) {
		DefaultTableModel dtmResult = null;
		ResultSet objRS = mReportModel.getResultSet(bOpt);
		Controls objControls  = new Controls ();
		try {
			dtmResult = objControls.getModels(objRS);
		} catch (Exception e) {
			System.out.println("ReportController says: " + e.getMessage());
		}		
		return dtmResult;
	}

	@Override
	public int getMaxID() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
