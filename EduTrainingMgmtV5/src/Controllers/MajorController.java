package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.MajorEntity;
import Models.MajorModel;

public class MajorController 
				extends BaseController<MajorEntity> {
	private MajorModel mMajorModel;
	
	public MajorController(Connection objConnection){
		super(objConnection);
		mMajorModel = new MajorModel(objConnection);
	}
	
	@Override
	public List<MajorEntity> GetDataAll() throws SQLException {
		return mMajorModel.GetElements();
	}

	@Override
	public List<MajorEntity> GetDataById(String sID) {
		return mMajorModel.GetElementById(sID);
	}

	/**
	 * bOpt = 0: Insert
	 * bOpt = 1: Update
	 */
	@Override
	public boolean InsertAndUpdateData(MajorEntity objT, byte bOpt) {
		if(bOpt == 0){
			return mMajorModel.InsertElement(objT);
		}else{
			return mMajorModel.UpdateElement(objT);
		}
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		//List<MajorEntity> listMajors = mMajorModel.GetElements();
		ResultSet objRS = mMajorModel.GetElementsRS();
		Controls<MajorEntity> controls = new Controls<MajorEntity>();
		DefaultTableModel dtm = null;
		try {
			//dtm = controls.getDataModels(listMajors);
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtm;
	}

	@Override
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		//List<MajorEntity> listMajors = mMajorModel.GetElementById(sID);
		ResultSet objRS = mMajorModel.GetElementByIdRS(sID);
		Controls<MajorEntity> controls = new Controls<MajorEntity>();
		DefaultTableModel dtm = null;
		try {
			//dtm = controls.getDataModels(listMajors);
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtm;
	}

	@Override
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
		//List<MajorEntity> listMajors = mMajorModel.SearchElementByName(sName);
		ResultSet objRS = mMajorModel.SearchElementByNameRS(sName);
		Controls<MajorEntity> controls = new Controls<MajorEntity>();
		DefaultTableModel dtm = null;
		try {
			//dtm = controls.getDataModels(listMajors);
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtm;		
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mMajorModel.DeleteDataByID(sID);
	}

	@Override
	public int getMaxID() throws SQLException {
		int resultMajorIDMax = 0;
		resultMajorIDMax= mMajorModel.getMaxID();	
		return resultMajorIDMax;
	}
}
