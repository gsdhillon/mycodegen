package mycodegen.jdbc.oracle;

import mycodegen.jdbc.DBField;


/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DBFieldOracle extends DBField{
    private final String dateFormat = "mm/dd/yyyy";
    private final String dateTimeFormat = "mm/dd/yyyy hh24:mi:ss";

    @Override
    protected String getStrToDateParam() throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    protected String getStrToDateTimeParam() throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getDateToStrParam(String fieldName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getDateTimeToStrParam(String fieldName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}