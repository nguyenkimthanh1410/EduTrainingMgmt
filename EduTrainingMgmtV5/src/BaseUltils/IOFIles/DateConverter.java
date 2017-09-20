package BaseUltils.IOFIles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    /**
     * Nghiệp vụ convert kiểu dữ liệu từ Date --> Sang String 
     * format: "yyyy-MM-dd"
     */
    public static String convertDateToString(Date date, String format) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
        return mySimpleDateFormat.format(date);
    }

    /**
     * Nghiệp vụ chuyển đổi từ String --> Date trong Java
     */
    public static Date convertStringToDate(String dateStr, String format) {
        SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
        try {
            return mySimpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
    
    /**
     * Purpose: To convert java.util.Date into java.sql.Date. 
     * It's useful when giving data from Java app to SQL
     * Ref: https://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
     * @param javaDate
     * @return java.sql.Date   		
     */
    
    public static java.sql.Date covertUtilDateToSqlDate(java.util.Date javaDate){
    	java.util.Date utilDOB = javaDate;
		java.sql.Date sqlDate = new java.sql.Date(utilDOB.getTime());
		
		return sqlDate;
    }
    
    /**
     * Purpose: To convert a Date formated String to java.sql.Date
     * It's useful when giving data from Java App to SQL 
     * @param dateStr
     * @param format
     * @return java.sql.Date
     */
    public static java.sql.Date convertStringToSqlDate(String dateStr, String format){
    	java.util.Date utilDate = convertStringToDate(dateStr, format);
    	java.sql.Date sqlDate = covertUtilDateToSqlDate(utilDate);
    	return sqlDate;
    }
}
