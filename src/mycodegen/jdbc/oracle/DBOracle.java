package mycodegen.jdbc.oracle;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import mycodegen.jdbc.DB;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DBOracle extends DB{
    @SuppressWarnings("UseSpecificCatch")
    @Override
    protected Connection createConnection() throws Exception{
        try {
            String options = "zeroDateTimeBehavior=convertToNull";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sampleweb?" + options,
                    "root", "fw0r"
            );
            //
            DatabaseMetaData meta = conn.getMetaData();
            String productName = meta.getDatabaseProductName();
            String productVersion = meta.getDatabaseProductVersion();
//            System.out.println("Connected to MySQL "
//                    + "productName:" + productName + ", "
//                    + "productVersion:" + productVersion);
            return conn;
        } catch (Exception e) {
            System.out.println("Connect to MySQL failed!");
            throw e;
        }
    }
    /**
     * MYSQL Types
     *
     * @param columnTypeName
     * @param size
     * @return
     * @throws Exception
     */
    @Override
    protected String makeType(String columnTypeName, int size) throws Exception {
        columnTypeName = columnTypeName.toUpperCase();
        if (columnTypeName.startsWith("VARCHAR")) {
            return TYPE_TEXT;
        } else if (columnTypeName.contains("INT")) {
            if (size > 10) {
                return TYPE_LONG;//BIGINT 
            } else {
                return TYPE_INT; //INTEGER, INT, TINYINT, SMALLINT
            }
        } else if (columnTypeName.equals("FLOAT") || columnTypeName.equals("DOUBLE")) {
            return TYPE_DOUBLE;
        } else if (columnTypeName.equals("DATE")) {
            return TYPE_DATE;
        } else if (columnTypeName.equals("DATETIME")) {
            return TYPE_DATETIME;
        } else if (columnTypeName.endsWith("BLOB")) {
            return TYPE_BLOB;
        } else {
            throw new Exception("Unknown datatype name - " + columnTypeName);
        }
    }
}