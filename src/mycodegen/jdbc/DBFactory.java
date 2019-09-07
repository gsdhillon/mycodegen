package mycodegen.jdbc;

import mycodegen.jdbc.mysql.DBFieldMySQL;
import mycodegen.jdbc.mysql.DBMySQL;
import mycodegen.jdbc.oracle.DBOracle;
import mycodegen.jdbc.oracle.DBFieldOracle;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DBFactory {
    public static final int DB_TYPE_MYSQL = 1;
    public static final int DB_TYPE_ORACLE = 2;
    //TODO GURMEET set databse type here and update the create methods
    private static final int dbTypeSelected = DB_TYPE_MYSQL;
    //
    public static DB createDB(){
        switch(dbTypeSelected){
            case DB_TYPE_ORACLE: return new DBOracle();
            default: return new DBMySQL(); //DB_TYPE_MYSQL
        }
    }
    public static DBField createField(){
        switch(dbTypeSelected){
            case DB_TYPE_ORACLE: return new DBFieldOracle() ;
            default: return new DBFieldMySQL() ; //DB_TYPE_MYSQL
        }
    }
    
    
    
//    public static void main(String[] args) {
//        try {
//            DB db = DBFactory.createDB();
//            List<Field> tableFields = db.getTableFields("pki_pro_visitors");
//            Delim delim = new Delim(",\n");
//            System.out.println("[");
//            tableFields.stream().forEach(field -> {
//                delim.print(field);
//            });
//            System.out.println("\n]");
//        } catch (Exception e) {
//        }
//    }
}