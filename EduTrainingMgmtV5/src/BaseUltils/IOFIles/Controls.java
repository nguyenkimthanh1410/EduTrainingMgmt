package BaseUltils.IOFIles;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;


public class Controls<T> {
	/**
	 * Nghiá»‡p vá»¥ thá»±c thi vÃ  tráº£ vá»� 1 Ä‘á»‘i tÆ°á»£ng DefaultTableModel --> bindding
	 * lÃªn JTable (ResultSet --> JTable)
	 * 
	 * @param objRS
	 * @return
	 * @throws SQLException
	 */
	public DefaultTableModel getModels(ResultSet objRS) throws Exception {
		DefaultTableModel objDTM = new DefaultTableModel();
		try {
			int iCount = 0;
			// 1. Binding dá»¯ liá»‡u tá»« ResultSet vÃ o Ä‘á»‘i tÆ°á»£ng ResultSetMetaData
			ResultSetMetaData objRSData = objRS.getMetaData();
			int col = objRSData.getColumnCount();

			// 2. Khá»Ÿi táº¡o Ä‘á»‘i tÆ°á»£ng DefaultTableModel chá»©a thÃ´ng tin HÃ ng vÃ 
			// Cá»™t
			objDTM = new DefaultTableModel();
			Vector<String> col_name = new Vector<String>();
			Vector<String> row = new Vector<String>();

			// 3. Duyá»‡t vÃ  khá»Ÿi táº¡o thÃ´ng tin column
			for (int i = 1; i <= col; i++) {
				col_name.addElement(objRSData.getColumnName(i));
			}
			objDTM.setColumnIdentifiers(col_name);

			// 4. Duyá»‡t vÃ  khá»Ÿi táº¡o thÃ´ng tin Row
			while (objRS.next()) {
				row = new Vector<String>();
				for (int j = 1; j <= col; j++) {
					row.addElement(objRS.getString(j));
				}
				objDTM.addRow(row);
				iCount++;
			}
		} catch (Exception ex) {
			throw ex;
		}
		return objDTM;
	}

	private List<String> getFieldOf(List<T> lisField) {
		Field[] objF = lisField.get(0).getClass().getDeclaredFields();
		List<String> lisResult = new ArrayList<String>();
		for (int i = 0; i < objF.length; i++) {
			lisResult.add(objF[i].getName());
		}
		return lisResult;
	}

	public DefaultTableModel getDataModels(List<T> lisT) 
						throws Exception {
		// 1. Khá»Ÿi táº¡o Ä‘á»‘i tÆ°á»£ng DefaultTableModel chá»©a thÃ´ng tin HÃ ng vÃ  Cá»™t
		DefaultTableModel objDTM = new DefaultTableModel();
		Vector<String> col_name = new Vector<String>();
		Vector<String> row = null;

		// 2. Khá»Ÿi táº¡o thÃ´ng tin column
		Field[] objF = lisT.get(0).getClass().getDeclaredFields();
		for (int i = 0; i < objF.length; i++) {
			col_name.addElement(objF[i].getName());
		}
		objDTM.setColumnIdentifiers(col_name);
		Gson gson = new Gson();

		// 3. Duyá»‡t vÃ  khá»Ÿi táº¡o thÃ´ng tin Row
		Object[] objO = new Object[lisT.size()];

		for (int i = 0; i < lisT.size(); i++) {
			// objO[i] = lisT.get(i);
			String strTemp = gson.toJson(lisT.get(i));
			int iIndex = 0;
			row = new Vector<String>();
			for (int j = 0; j < col_name.size(); j++) {
				iIndex = strTemp.indexOf(objF[j].getName());
				if ('"' == strTemp.charAt(iIndex + objF[j].getName().length() + 2)) {
					if ((objF.length - j) > 1)
						row.add(strTemp.substring(iIndex + objF[j].getName().length() + 3,
								strTemp.indexOf(objF[j + 1].getName()) - 3));
					else
						row.add(strTemp.substring(iIndex + objF[j].getName().length() + 3, strTemp.length() - 2));
				} else {
					if ((objF.length - j) > 1)
						row.add(strTemp.substring(iIndex + objF[j].getName().length() + 2,
								strTemp.indexOf(objF[j + 1].getName()) - 2));
					else
						row.add(strTemp.substring(iIndex + objF[j].getName().length() + 2, strTemp.length() - 1));
				}
				// DÃ nh cho cÃ¡c chuyÃªn gia
				// String sValue = objO[i]
				// row.add(objO[i][j])
			}
			objDTM.addRow(row);
		}
		return objDTM;
	}

}
