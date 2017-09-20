package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.DepartmentEntity;
import Models.DepartmentModel;

public class DepartmentController extends BaseController<DepartmentEntity> {
	private DepartmentModel mDepartmentModel;
	public DepartmentController(Connection objConnection) {
		super(objConnection);
		mDepartmentModel = new DepartmentModel(objConnection);	
	}

	@Override
	public List<DepartmentEntity> GetDataAll() throws SQLException {
		return mDepartmentModel.GetElements();
	}

	@Override
	public List<DepartmentEntity> GetDataById(String sID) throws SQLException {		
		return mDepartmentModel.GetElementById(sID);
	}

	@Override
	public boolean InsertAndUpdateData(DepartmentEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){//insert
			return mDepartmentModel.InsertElement(objT);
		}else{//update
			return mDepartmentModel.UpdateElement(objT);
		}		
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		//List<DepartmentEntity> listDepts = mDepartmentModel.GetElements();
		ResultSet objRS = mDepartmentModel.GetElementsRS();
		Controls<DepartmentEntity> controls = new Controls<DepartmentEntity>();
		DefaultTableModel dtm = null;
		try {
			//dtm = controls.getDataModels(listDepts);
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
		
		//List<DepartmentEntity> listDepts = mDepartmentModel.SearchElementByName(sName);
		ResultSet objRS = mDepartmentModel.SearchElementByNameRS(sName);
		Controls<DepartmentEntity> controls = new Controls<DepartmentEntity>();
		try {
			//dtm = controls.getDataModels(listDepts);
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Dept Control, search: " + e.getMessage());
		}		
		return dtm;
	}

	@Override
	public boolean DeleteDataByID(String sID) throws SQLException {
		return mDepartmentModel.DeleteDataByID(sID);
	}

	@Override
	public int getMaxID() throws SQLException {
		return mDepartmentModel.getMaxID();		
	}

	

}
