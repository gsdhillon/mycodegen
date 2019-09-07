package mycodegen.jdbc;

import mycodegen.generator.java.CodeWriter;
import mycodegen.generator.java.Java;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public abstract class DBField {
    protected final boolean withComments = true;
    protected final boolean withValue = true;
    public String nameDb;
    public String type;
    public int size;
    public boolean nullable;
    public String value;
    public boolean isPrimary;
    /**
     * 
     * @param cw
     * @throws Exception 
     */
    public void getDeclarationStatement(CodeWriter cw) throws Exception{
        cw.varDec("public", Java.getTypeName(type), Java.var(nameDb), getValueJava(), getInfo());
    }
    
    public String getVariableCastedString(){
        if( 
            "TEXT".equals(type)  || 
            type.startsWith("DATE")

        ){
            return Java.var(nameDb);
        }else{
            return "String.valueOf("+Java.var(nameDb)+")";
        }
        
    }
    
    
    public String getValueJava() throws Exception{
        switch(type){
            case DB.TYPE_INT: 
            case DB.TYPE_LONG: 
            case DB.TYPE_DOUBLE: return value;
            case DB.TYPE_TEXT: 
            case DB.TYPE_DATE: 
            case DB.TYPE_DATETIME: return "\"" + value + "\"";
            case DB.TYPE_BLOB: return "null";
            default: throw new Exception("Unknown type - "+type);
        }
    }
    public String getValueSql() throws Exception{
        switch(type){
            case DB.TYPE_INT: 
            case DB.TYPE_LONG: 
            case DB.TYPE_DOUBLE: return value;
            case DB.TYPE_TEXT: 
            case DB.TYPE_DATE: 
            case DB.TYPE_DATETIME: return "'" + value + "'";
            case DB.TYPE_BLOB: return "null";
            default: throw new Exception("Unknown type - "+type);
        }
    }
    private String getInfo() throws Exception{
        return getDataTypeInfo() + " " + getNullable();
    }
    private String getDataTypeInfo() throws Exception{
        switch(type){
            case DB.TYPE_INT: 
            case DB.TYPE_LONG: 
            case DB.TYPE_DOUBLE: 
            case DB.TYPE_TEXT: return type + "[" + size + "]";
            case DB.TYPE_DATE: return type + "[dd/mm/yyyy]";
            case DB.TYPE_DATETIME: return type + "[dd/mm/yyyy hh24:mi:ss]";
            case DB.TYPE_BLOB: return type + "[" + size + "]";
            default: throw new Exception("Unknown type - "+type);
        }
    }
    private String getNullable() {
        if(nullable){
            return "";
        }else{
            return "[NOT NULL]";
        }
    }
    
    public boolean isBlob() {
        return (type == null ? false : type.equals(DB.TYPE_BLOB));
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public String getQueryParam() throws Exception{
        switch(type){
            case DB.TYPE_INT: 
            case DB.TYPE_LONG: 
            case DB.TYPE_DOUBLE: 
            case DB.TYPE_TEXT: 
            case DB.TYPE_BLOB: return "?";
            case DB.TYPE_DATE: return getStrToDateParam();
            case DB.TYPE_DATETIME: return getStrToDateTimeParam();
            default: throw new Exception("Unknown type - "+type);
        }
    }
    public String getQuerySelect() throws Exception{
        switch(type){
            case DB.TYPE_INT: 
            case DB.TYPE_LONG: 
            case DB.TYPE_DOUBLE: 
            case DB.TYPE_TEXT: 
            case DB.TYPE_BLOB: return nameDb;
            case DB.TYPE_DATE: return getDateToStrParam(nameDb);
            case DB.TYPE_DATETIME: return getDateTimeToStrParam(nameDb);
            default: throw new Exception("Unknown type - "+type);
        }
    }
    
     @Override
    public String toString() {
        return "\t{\n\t"
                + "\tname_db : " + nameDb + ", \n\t"
                + "\tname_java : " + Java.var(nameDb) + ", \n\t"
                + "\ttype : " + type + ", \n\t"
                + "\tsize : " + size + ", \n\t"
                + "\tnullable : " + nullable + ", \n\t"
                + "\tvalue : " + value + "\n"
                + "\t}";
    }
    
    //TO_DATE (Oracle) /  STR_TO_DATE (MySQL)
    protected abstract String getStrToDateParam() throws Exception;
    protected abstract String getStrToDateTimeParam() throws Exception;
    
    //TO_CHAR (Oracle)  /  DATE_FORMAT (MySQL)
    protected abstract String getDateToStrParam(String fieldName) throws Exception;
    protected abstract String getDateTimeToStrParam(String fieldName) throws Exception;

   
}