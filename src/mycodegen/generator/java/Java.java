package mycodegen.generator.java;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mycodegen.jdbc.DB;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class Java {
    public static final String prefixToRemove = "PKI"; //className as well as all details field names
    
    /**
     * 
     * @param tableName
     * @return
     * @throws Exception 
     */
    public static String removePrefix(String tableName) throws Exception{
        int len = prefixToRemove.length();
        if(tableName==null || tableName.length() < len){
            return tableName;
        }
        String prefix = tableName.substring(0, len);
        if(prefix.equalsIgnoreCase(prefixToRemove)){
            return tableName.substring(len);
        }else{
            return tableName;
        }
    }
    public static String getTypeName(String dbType) throws Exception{
        switch(dbType){
            case DB.TYPE_INT: return "int";
            case DB.TYPE_LONG: return "long";
            case DB.TYPE_DOUBLE: return "double";
            case DB.TYPE_TEXT: return "String";
            case DB.TYPE_DATE: return "String";
            case DB.TYPE_DATETIME: return "String";
            case DB.TYPE_BLOB: return "SerialBlob";
            default: throw new Exception("Unknown type - "+dbType);
        }
    }
    public static String getTypeNameResultSet(String dbType) throws Exception{
        switch(dbType){
            case DB.TYPE_INT: return "Int";
            case DB.TYPE_LONG: return "Long";
            case DB.TYPE_DOUBLE: return "Double";
            case DB.TYPE_TEXT: return "String";
            case DB.TYPE_DATE: return "String";
            case DB.TYPE_DATETIME: return "String";
            case DB.TYPE_BLOB: return "Blob";
            default: throw new Exception("Unknown type - "+dbType);
        }
    }
    
    public static String getPsSetMethod(String dbType) throws Exception{
        switch(dbType){
            case DB.TYPE_INT: return "setInt";
            case DB.TYPE_LONG: return "setLong";
            case DB.TYPE_DOUBLE: return "setDouble";
            case DB.TYPE_TEXT: 
            case DB.TYPE_DATE: 
            case DB.TYPE_DATETIME: return "setString";
            case DB.TYPE_BLOB: return "setBlob";
            default: throw new Exception("Unknown type - "+dbType);
        }
    }
    
    public static String type(String dbName){
        return name(dbName, false);
    }
    public static String var(String dbName){
        return name(dbName, true);
    }
    
    /**
     * Underscore separated to CamelCase
     * @param dbName
     * @param isField
     * @return 
     */
    public static String name(String dbName, boolean isField){
        try{
            if(dbName == null){
                return null;
            }
            //
            String lower = "_"+dbName.toLowerCase();
            Matcher m = Pattern.compile("(_)+([^_]+)").matcher(lower);
            boolean found = false;
            StringBuilder sb = new StringBuilder();
            while(m.find()){
              if(!found){
                  if(isField){
                      sb.append(m.group(2));
                  }else{
                      sb.append(Character.toUpperCase(m.group(2).charAt(0)))
                    .append(m.group(2).substring(1));
                  }
                  found = true;
              }else{
                   sb.append(Character.toUpperCase(m.group(2).charAt(0)))
                   .append(m.group(2).substring(1));
              }
            }
            return sb.toString();
        }catch(Exception e){
            return dbName.replaceAll("_", "");
        }
    }
}