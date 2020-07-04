package mycodegen.jdbc.mysql;

import mycodegen.jdbc.DBField;


/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DBFieldMySQL extends DBField{
    private final String dateFormat = "%d/%m/%Y";
    private final String dateTimeFormat = "%d/%m/%Y %H:%i:%s";

    @Override
    protected String getStrToDateParam() throws Exception{
        return "STR_TO_DATE(?, '"+dateFormat+"')";  
    }
    @Override
    protected String getStrToDateTimeParam() throws Exception{
        return "STR_TO_DATE(?, '"+dateTimeFormat+"')";  
    }

    @Override
    protected String getDateToStrParam(String fieldName) throws Exception {
        return "DATE_FORMAT("+fieldName+", '"+dateFormat+"') "+fieldName; 
    }

    @Override
    protected String getDateTimeToStrParam(String fieldName) throws Exception {
        return "DATE_FORMAT("+fieldName+", '"+dateTimeFormat+"') "+fieldName; 
    }

    
}