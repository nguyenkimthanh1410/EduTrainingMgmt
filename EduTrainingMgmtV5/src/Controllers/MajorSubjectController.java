package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.MajorSubjectEntity;
import Models.MajorSubjectModel;


public class MajorSubjectController extends BaseController<MajorSubjectEntity>{
	MajorSubjectModel mMajorSubjectModel;
	
	public MajorSubjectController(Connection objConnection) {
		super(objConnection);
		mMajorSubjectModel = new MajorSubjectModel(objConnection);
	}

	@Override
	public List<MajorSubjectEntity> GetDataAll() throws SQLException {
		return mMajorSubjectModel.GetElements();
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		DefaultTableModel dtm = null;
		ResultSet objRS = mMajorSubjectModel.GetElementsRS();
		
		Controls<MajorSubjectEntity> controls = new Controls<MajorSubjectEntity>();
		try {
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			System.out.println("Controller Major-Subject: " + e.getMessage());
			e.printStackTrace();			
		}
		return dtm;
	}

	
	@Override
	public List<MajorSubjectEntity> GetDataById(String sID) throws SQLException {
		return mMajorSubjectModel.GetElementById(sID);
	}
	
	@Override
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean InsertAndUpdateData(MajorSubjectEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){//Insertion
			return mMajorSubjectModel.InsertElement(objT);
		}else{//Update
			return mMajorSubjectModel.UpdateElement(objT);
		}	
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mMajorSubjectModel.DeleteDataByID(sID);
	}

	
	@Override
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		return mMajorSubjectModel.getMaxID();
	}
}
