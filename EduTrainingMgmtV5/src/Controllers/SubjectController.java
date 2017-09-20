package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.SubjectEntity;
import Models.SubjectModel;

public class SubjectController extends BaseController<SubjectEntity>{

	private SubjectModel mSubjectModel;
	
	public SubjectController(Connection objConnection){
		super(objConnection);
		mSubjectModel = new SubjectModel(objConnection);
	}
	
	@Override
	public List<SubjectEntity> GetDataAll() throws SQLException {
		return mSubjectModel.GetElements();
	}
	
	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		//List<SubjectEntity> listSubjects = mSubjectModel.GetElements();
		ResultSet objRS = mSubjectModel.GetElementsRS();
		Controls<SubjectEntity> controls = new Controls<SubjectEntity>();
		DefaultTableModel dTM = null;
		try {
			//dTM = controls.getDataModels(listSubjects);
			dTM = controls.getModels(objRS);
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println("controller: " + e.getMessage());
		}
		return dTM;
	}


	@Override
	public List<SubjectEntity> GetDataById(String sID) throws SQLException {
		return mSubjectModel.GetElementById(sID);
	}
	
	@Override
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertAndUpdateData(SubjectEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){
			return mSubjectModel.InsertElement(objT);
		}else{
			return mSubjectModel.UpdateElement(objT);
		}
	}
	
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mSubjectModel.DeleteDataByID(sID);
	}

	@Override
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
		//List<SubjectEntity> listStudents = mSubjectModel.SearchElementByName(sName);
		ResultSet objRS = mSubjectModel.SearchElementByNameRS(sName);
		Controls<SubjectEntity> controls = new Controls<SubjectEntity>();
		DefaultTableModel dtm = null;
		try {
			//dtm = controls.getDataModels(listStudents);
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtm;
	}

	

	@Override
	public int getMaxID() throws SQLException {
		return mSubjectModel.getMaxID();
	}

	
}
