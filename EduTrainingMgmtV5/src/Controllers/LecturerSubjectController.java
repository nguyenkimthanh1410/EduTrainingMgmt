package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import BaseUltils.IOFIles.Controls;
import Entities.LecturerSubjectEntity;
import Models.LecturerSubjectModel;

public class LecturerSubjectController extends BaseController<LecturerSubjectEntity>{
	LecturerSubjectModel mLecturerSubjectModel;
	
	public LecturerSubjectController(Connection objConnection) {
		super(objConnection);
		mLecturerSubjectModel = new LecturerSubjectModel(objConnection);
	}

	@Override
	public List<LecturerSubjectEntity> GetDataAll() throws SQLException {
		return mLecturerSubjectModel.GetElements();
	}

	@Override
	public List<LecturerSubjectEntity> GetDataById(String sID) throws SQLException {
		return mLecturerSubjectModel.GetElementById(sID);
	}

	@Override
	public boolean InsertAndUpdateData(LecturerSubjectEntity objT, byte bOpt) throws SQLException {
		if(bOpt == 0){//Insertion
			return mLecturerSubjectModel.InsertElement(objT);
		}else{//Update
			return mLecturerSubjectModel.UpdateElement(objT);
		}	
	}

	@Override
	public DefaultTableModel GetDTMAll() throws SQLException {
		DefaultTableModel dtm = null;
		ResultSet objRS = mLecturerSubjectModel.GetElementsRS();
		
		Controls<LecturerSubjectEntity> controls = new Controls<LecturerSubjectEntity>();
		try {
			dtm = controls.getModels(objRS);
		} catch (Exception e) {
			System.out.println("Controller LecSub: " + e.getMessage());
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
		return mLecturerSubjectModel.DeleteDataByID(sID);
	}

	@Override
	public int getMaxID() throws SQLException {
		return mLecturerSubjectModel.getMaxID();
	}

	public List<Integer> getAllIDs() throws SQLException {
		return mLecturerSubjectModel.getAllIDs();
	}

}
