package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.MarkEntity;
import Models.MarkModel;

public class MarkController extends BaseController<MarkEntity>{
	MarkModel mMarkModel;
	
	public MarkController(Connection objConnection) {
		super(objConnection);
		mMarkModel = new MarkModel(objConnection);
	}

	@Override
	public List<MarkEntity> GetDataAll() throws SQLException {
		return mMarkModel.GetElements();
	}

	@Override
	public List<MarkEntity> GetDataById(String sID) throws SQLException {
		return mMarkModel.GetElementById(sID);
	}

	@Override
	public boolean InsertAndUpdateData(MarkEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){//Insertion
			return mMarkModel.InsertElement(objT);
		}else{//Update
			return mMarkModel.UpdateElement(objT);
		}	
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		DefaultTableModel dtm = null;
		ResultSet objRS = mMarkModel.GetElementsRS();
		
		Controls<MarkEntity> controls = new Controls<MarkEntity>();
		try {
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			System.out.println("Controller Mark: " + e.getMessage());
			e.printStackTrace();			
		}
		return dtm;
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
		return mMarkModel.DeleteDataByID(sID);
	}

	@Override
	public int getMaxID() throws SQLException {
		return mMarkModel.getMaxID();
	}
}
