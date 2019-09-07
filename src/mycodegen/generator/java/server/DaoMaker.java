package mycodegen.generator.java.server;

import java.util.List;
import application.DBNode;
import application.Package;
import mycodegen.jdbc.DB;
import mycodegen.jdbc.DBFactory;
import mycodegen.jdbc.DBField;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DaoMaker {
    public final Package packageInfo;
    // table
    public final DBNode dbNode;
    public final String daoClassName;
    public final String entityClassName;
    public final String tableName;
    public final List<DBField> fields;
    // parent table
    public final DBNode parentNode;
    public String parentDaoClassName = null;
    public String parentEntityClassName = null;
    public String parentTableName = null;
    public List<DBField> parentFields = null;
    //
    private final DB db;

    /**
     * 
     * @param packageInfo
     * @param dbNode
     * @throws Exception 
     */
    public DaoMaker(Package packageInfo, DBNode dbNode) throws Exception{
        this.packageInfo = packageInfo;
        this.dbNode = dbNode;
        //
        db = DBFactory.createDB().connect();
        
        // Node
        tableName = dbNode.getTableName();
        daoClassName = dbNode.getDaoName();
        entityClassName = dbNode.getEntityName();
        fields = db.getTableFields(dbNode.getTableName());
        
        // Parent Node
        parentNode = dbNode.getParent();
        if(parentNode != null){
            parentFields = db.getTableFields(parentNode.getTableName());
            parentTableName = parentNode.getTableName();
            parentDaoClassName = parentNode.getDaoName();
            parentEntityClassName = parentNode.getEntityName();
        }
    }
  
    /**
     * 
     */
    public void close(){
        db.close();
    }
}