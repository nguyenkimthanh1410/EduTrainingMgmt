package Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReportModel extends BaseModel<Object> {

	public ReportModel(Connection objConn) {
		super(objConn);
	}

	@Override
	public List<Object> GetElements() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> GetElementById(String sId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertElement(Object objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean UpdateElement(Object objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean DeleteElement(Object objT) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> SearchElementByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public ResultSet getResultSet(byte bOpt) {
		mResultset = null;
		String sqlStr ="";
		switch(bOpt){
		case 1://spReportStudentMarkExamSubjectLecturer
			sqlStr = "{call spReportStudentMarkExamSubjectLecturer}";					
			break;
			
		case 2://spReportLecturersSubjectsAssigment
			sqlStr = "{call spReportLecturersSubjectsAssigment}";
			break;
		case 3: //[spMajorSubjectRegulation]
			sqlStr = "{call spReportMajorSubjectRegulation}";
			break;
		}		
		try {
			mCallStatement = mConnection.prepareCall(sqlStr);
			mResultset = mCallStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("ReportModel says: " + e.getMessage());			
		}
		return mResultset;		
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

	@Override
	public int getMaxID() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
