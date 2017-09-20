package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.LecturerEntity;
import Models.LecturerModel;

public class LecturerController extends BaseController<LecturerEntity> {
	LecturerModel mLecturerModel;
	public LecturerController(Connection objConnection) {
		super(objConnection);
		mLecturerModel = new LecturerModel(objConnection);
		
	}

	@Override
	public List<LecturerEntity> GetDataAll() throws SQLException {
		return mLecturerModel.GetElements();
	}

	@Override
	public List<LecturerEntity> GetDataById(String sID) throws SQLException {
		return mLecturerModel.GetElementById(sID);
	}

	@Override
	public boolean InsertAndUpdateData(LecturerEntity objT, byte bOpt) throws SQLException {
		if(bOpt ==0){
			return mLecturerModel.InsertElement(objT);
		}else{
			return mLecturerModel.UpdateElement(objT);
		}
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		//List<LecturerEntity> list = mLecturerModel.GetElements();
		ResultSet objRS = mLecturerModel.GetElementsRS();
		Controls<LecturerEntity> controls = new Controls<LecturerEntity>();
		DefaultTableModel dtm = null;
		try {
			//dtm = controls.getDataModels(list);
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
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
		DefaultTableModel dtm = null;		
		//List<LecturerEntity> list = mLecturerModel.SearchElementByName(sName);
		ResultSet objRS = mLecturerModel.SearchElementByNameRS(sName);
		Controls<LecturerEntity> controls = new Controls<LecturerEntity>();
		try {
			//dtm = controls.getDataModels(list);
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Controller, search: " + e.getMessage());
		}		
		return dtm;
	}


	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mLecturerModel.DeleteDataByID(sID);
	}

	@Override
	public int getMaxID() throws SQLException {
		return mLecturerModel.getMaxID();
	}

}
