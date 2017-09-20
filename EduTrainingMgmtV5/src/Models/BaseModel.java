package Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseModel<T> {
	protected ResultSet mResultset;
	protected CallableStatement mCallStatement;
	protected Connection mConnection;	
	protected BaseModel(Connection objConnection){
		 mConnection = objConnection;
	}	
	public abstract List<T> GetElements() throws SQLException ;
	public abstract ResultSet GetElementsRS() throws SQLException;
	
	public abstract List<T> GetElementById(String sId) throws SQLException ;
	public abstract ResultSet GetElementByIdRS(String sId) throws SQLException ;
	
	public abstract boolean InsertElement(T objT) throws SQLException ;
	public abstract boolean UpdateElement(T objT) throws SQLException ;
	public abstract boolean DeleteElement(T objT) throws SQLException ;	
	public abstract boolean DeleteDataByID(String sID) throws SQLException;
	
	public abstract ResultSet SearchElementByNameRS(String sName) throws SQLException;	
	public abstract List<T> SearchElementByName(String sName) throws SQLException;
	
	public abstract int getMaxID() throws SQLException;
}
