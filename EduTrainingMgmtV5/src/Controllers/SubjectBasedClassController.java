package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.SubjectBasedClassEntity;
import Models.SubjectBasedClassModel;

public class SubjectBasedClassController extends BaseController<SubjectBasedClassEntity>{
	SubjectBasedClassModel mLecturerSubjectModel;
	
	public SubjectBasedClassController(Connection objConnection) {
		super(objConnection);
		mLecturerSubjectModel = new SubjectBasedClassModel(objConnection);
	}

	@Override
	public List<SubjectBasedClassEntity> GetDataAll() throws SQLException {
		return mLecturerSubjectModel.GetElements();
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		DefaultTableModel dtm = null;
		ResultSet objRS = mLecturerSubjectModel.GetElementsRS();
		
		Controls<SubjectBasedClassEntity> controls = new Controls<SubjectBasedClassEntity>();
		try {
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			System.out.println("Controller Subject-Based Class: " + e.getMessage());
			e.printStackTrace();			
		}
		return dtm;
	}

	
	@Override
	public List<SubjectBasedClassEntity> GetDataById(String sID) throws SQLException {
		return mLecturerSubjectModel.GetElementById(sID);
	}
	
	@Override
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean InsertAndUpdateData(SubjectBasedClassEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){//Insertion
			return mLecturerSubjectModel.InsertElement(objT);
		}else{//Update
			return mLecturerSubjectModel.UpdateElement(objT);
		}	
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mLecturerSubjectModel.DeleteDataByID(sID);
	}

	
	@Override
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxID() throws SQLException {
		return mLecturerSubjectModel.getMaxID();
	}
}
