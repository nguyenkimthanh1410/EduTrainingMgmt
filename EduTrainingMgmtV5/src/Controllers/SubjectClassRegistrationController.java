package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.SubjectClassRegistrationEntity;
import Models.SubjectClassRegistrationModel;


public class SubjectClassRegistrationController extends BaseController<SubjectClassRegistrationEntity>{
	SubjectClassRegistrationModel mSubjectClassRegistrationModel;
	
	public SubjectClassRegistrationController(Connection objConnection) {
		super(objConnection);
		mSubjectClassRegistrationModel = new SubjectClassRegistrationModel(objConnection);
	}

	@Override
	public List<SubjectClassRegistrationEntity> GetDataAll() throws SQLException {
		return mSubjectClassRegistrationModel.GetElements();
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		DefaultTableModel dtm = null;
		ResultSet objRS = mSubjectClassRegistrationModel.GetElementsRS();
		
		Controls<SubjectClassRegistrationEntity> controls = new Controls<SubjectClassRegistrationEntity>();
		try {
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			System.out.println("Controller Major-Subject: " + e.getMessage());
			e.printStackTrace();			
		}
		return dtm;
	}

	
	@Override
	public List<SubjectClassRegistrationEntity> GetDataById(String sID) throws SQLException {
		return mSubjectClassRegistrationModel.GetElementById(sID);
	}
	
	@Override
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean InsertAndUpdateData(SubjectClassRegistrationEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){//Insertion
			return mSubjectClassRegistrationModel.InsertElement(objT);
		}else{//Update
			return mSubjectClassRegistrationModel.UpdateElement(objT);
		}	
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mSubjectClassRegistrationModel.DeleteDataByID(sID);
	}

	
	@Override
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		return mSubjectClassRegistrationModel.getMaxID();
	}
}
