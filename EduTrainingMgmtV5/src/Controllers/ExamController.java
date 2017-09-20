package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.ExamEntity;
import Entities.SubjectEntity;
import Models.ExamModel;
import Models.SubjectModel;

public class ExamController extends BaseController<ExamEntity>{

	private ExamModel mExamModel;
	
	public ExamController(Connection objConnection){
		super(objConnection);
		mExamModel = new ExamModel(objConnection);
	}
	
	@Override
	public List<ExamEntity> GetDataAll() throws SQLException {
		return mExamModel.GetElements();
	}
	
	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		//List<ExamEntity> listSubjects = mSubjectModel.GetElements();
		ResultSet objRS = mExamModel.GetElementsRS();
		Controls<ExamEntity> controls = new Controls<ExamEntity>();
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
	public List<ExamEntity> GetDataById(String sID) throws SQLException {
		return mExamModel.GetElementById(sID);
	}
	
	@Override
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertAndUpdateData(ExamEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){
			return mExamModel.InsertElement(objT);
		}else{
			return mExamModel.UpdateElement(objT);
		}
	}
	
	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mExamModel.DeleteDataByID(sID);
	}

	@Override
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
		//List<SubjectEntity> listStudents = mSubjectModel.SearchElementByName(sName);
		ResultSet objRS = mExamModel.SearchElementByNameRS(sName);
		Controls<ExamEntity> controls = new Controls<ExamEntity>();
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
		return mExamModel.getMaxID();
	}

	
}
