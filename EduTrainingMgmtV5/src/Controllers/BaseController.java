package Controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public abstract class BaseController<T> {
	protected Connection mConnection;	
	protected BaseController(Connection objConnection){
		 mConnection = objConnection;
	}
	public abstract List<T> GetDataAll() throws SQLException;
	public abstract List<T> GetDataById(String sID) throws SQLException;
	/**
	 * 
	 * @param objT
	 * @param bOpt; 0: insert, 1: update
	 * @return
	 * @throws SQLException
	 */
	public abstract boolean InsertAndUpdateData(T objT, byte bOpt) throws SQLException;
	
	public abstract DefaultTableModel GetDTMAll() throws SQLException;
	public abstract DefaultTableModel GetDTMByID(String sID) throws SQLException;
	
	public abstract DefaultTableModel GetDTMByName(String sName) throws SQLException;
	
	public abstract boolean DeleteDataByID(String sID) throws SQLException;
	public abstract int getMaxID() throws SQLException;
}
