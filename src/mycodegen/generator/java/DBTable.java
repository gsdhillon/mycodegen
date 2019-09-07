package mycodegen.generator.java;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DBTable {
    public final String dbTableName;
    public final String javaTypeName;
    public final String javaVarName;
    private boolean single = false;
    public boolean isSingle() {
        return single;
    }

    public DBTable(String name, boolean isSingleChild) throws Exception{
        this.dbTableName = name.toUpperCase();
        this.single = isSingleChild;
       
        //
        String trimmedTableName = Java.removePrefix(name);
        javaTypeName = Java.type(trimmedTableName);
        javaVarName = Java.var(trimmedTableName);
        
        
    }

    public String getTableName() {
        return dbTableName;
    }

}
