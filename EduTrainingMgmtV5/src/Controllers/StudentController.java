package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.StudentEntity;
import Models.StudentModel;

public class StudentController 
				extends BaseController<StudentEntity> {
	private StudentModel mStudentModel;
	
	public StudentController(Connection objConnection){
		super(objConnection);
		mStudentModel = new StudentModel(objConnection);
	}
	
	@Override
	public List<StudentEntity> GetDataAll() throws SQLException {
		return mStudentModel.GetElements();
	}

	@Override
	public List<StudentEntity> GetDataById(String sID) {
		return mStudentModel.GetElementById(sID);
	}

	/**
	 * bOpt = 0: Insert
	 * bOpt = 1: Update
	 */
	@Override
	public boolean InsertAndUpdateData(StudentEntity objT, byte bOpt) {
		if(bOpt == 0){
			return mStudentModel.InsertElement(objT);
		}else{
			return mStudentModel.UpdateElement(objT);
		}
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		//List<StudentEntity> listStudents = mStudentModel.GetElements();
		ResultSet objRS = mStudentModel.GetElementsRS();
		Controls<StudentEntity> controls = new Controls<StudentEntity>();
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
	public DefaultTableModel GetDTMByID(String sID) throws SQLException {
		//List<StudentEntity> listStudents = mStudentModel.GetElementById(sID);
		ResultSet objRS = mStudentModel.GetElementsRS();		
		Controls<StudentEntity> controls = new Controls<StudentEntity>();
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
	public DefaultTableModel GetDTMByName(String sName) throws SQLException {
		//List<StudentEntity> listStudents = mStudentModel.SearchElementByName(sName);
		ResultSet objRS = mStudentModel.SearchElementByNameRS(sName);
		Controls<StudentEntity> controls = new Controls<StudentEntity>();
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
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mStudentModel.DeleteDataByID(sID);
	}

	@Override
	public int getMaxID() throws SQLException {
		int resultStudentIDMax = 0;
		resultStudentIDMax= mStudentModel.getMaxID();		
		return resultStudentIDMax;
	}
}
