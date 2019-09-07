package mycodegen.jdbc;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mycodegen.utils.Util;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
@SuppressWarnings("CallToPrintStackTrace")
public abstract class DB {
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_INT = "INT";
    public static final String TYPE_LONG = "LONG";
    public static final String TYPE_DOUBLE = "DOUBLE";
    public static final String TYPE_DATE = "DATE";
    public static final String TYPE_DATETIME = "DATETIME";
    public static final String TYPE_BLOB = "BLOB";


    public Connection connection = null;
    /**
     * 
     */
    public DB(){
    }
    /**
     * 
     * @return
     * @throws Exception 
     */
    public DB connect() throws Exception{
        connection = createConnection();
        return this;
    }
    /**
     * 
     * @return
     * @throws Exception 
     */
    protected abstract Connection createConnection() throws Exception;
    /**
     * 
     * @param table
     * @return
     * @throws Exception 
     */
    public List<DBField> getTableFields(String table) throws Exception {
        //Required atleast ONE row in table
        System.out.println("Table: "+table);
        List<String> pkFieldList = new ArrayList<>();
        try(ResultSet pkRs = connection.getMetaData().getPrimaryKeys(null,null,table);) {
            while(pkRs.next()) {
                String pkColumnName = pkRs.getString("COLUMN_NAME");
                Integer keySeq = pkRs.getInt("KEY_SEQ");
                System.out.println("COLUMN_NAME:"+pkColumnName+", KEY_SEQ "+keySeq);
                pkFieldList.add(pkColumnName);
            }
        }
        
        String sql = "SELECT * FROM " + table;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        List<DBField> fields = new ArrayList<>();
        rs.next();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            DBField f = DBFactory.createField();
            f.nameDb = rsmd.getColumnName(i);
            f.isPrimary = checkIn(f.nameDb, pkFieldList); 
            f.size = rsmd.getColumnDisplaySize(i);
            f.type = makeType(rsmd.getColumnTypeName(i), f.size);
            f.nullable = makeNullable(rsmd.isNullable(i));
            switch (f.type) {
                case TYPE_BLOB:
                    f.value = getBlobInfo(rs, i);
                    break;
                case TYPE_DATE:
                    f.value = getDateValue(rs, i);
                    break;
                case TYPE_DATETIME:
                    f.value = getDateTimeValue(rs, i);
                    break;
                default:
                    f.value = rs.getString(i);
            }
            fields.add(f);
        }
        return fields;
    }
    /**
     * 
     * @param rs
     * @param i
     * @return 
     */
    @SuppressWarnings("UseSpecificCatch")
    private String getBlobInfo(ResultSet rs, int i) {
        try {
            Blob blob = rs.getBlob(i);
            if (blob != null) {
                return "BLOB data len=" + blob.length();
            } else {
                return "BLOB data null";
            }

        } catch (Exception e) {
            return "BLOB get failed - " + e.getMessage();
        }
    }
    /**
     * 
     * @param nullable
     * @return 
     */
    private boolean makeNullable(int nullable) {
        return nullable == 1;
    }
    /**
     * 
     * @param rs
     * @param i
     * @return
     * @throws Exception 
     */
    protected String getDateValue(ResultSet rs, int i) throws Exception {
        return Util.formatDate(rs.getDate(i));
    }
    /**
     * 
     * @param rs
     * @param i
     * @return
     * @throws Exception 
     */
    protected String getDateTimeValue(ResultSet rs, int i) throws Exception {
        return Util.formatDateTime(rs.getDate(i));
    }
    /**
     * 
     * @param columnTypeName
     * @param size
     * @return
     * @throws Exception 
     */
    protected abstract String makeType(String columnTypeName, int size) throws Exception;
    /**
     * 
     * @param nameDb
     * @param pkFieldList
     * @return 
     */
    private boolean checkIn(String nameDb, List<String> pkFieldList) {
        for(String pkField: pkFieldList){
            if(nameDb.equalsIgnoreCase(pkField)) return true;
        }
        return false;
    }
    /**
     * 
     */
    
    public void close() {
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setAutoCommit(boolean b) throws Exception{
        connection.setAutoCommit(b);
    }
    public void commit() throws Exception{
        connection.commit();
    }
    public void rollback(){
        try{
            if(connection != null){
                connection.rollback();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}