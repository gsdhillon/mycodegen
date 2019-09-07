package application;

import mycodegen.generator.java.DBTable;
import java.util.ArrayList;
import java.util.List;

/**
 * DB tables one master-details branch at a time from bottom to up
 * TODO - Full Tree implementation pending
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DBNode{
    public Application app = null;
    private final DBTable masterTable;
    private final List<DBNode> detailTables; 
    private DBNode parent = null;
    
    //Will used by CodeGenerator
    public DBNode(DBTable masterTable) {
        this.masterTable = masterTable;
        this.detailTables = new ArrayList<>();
    }
    /**
     * @@@@@@@@@@@   Fill Data Here  @@@@@@@@@@@@@@@@
     * @throws java.lang.Exception
     */
    public DBNode() throws Exception{
        //pki_pro_record
        app = new Application();
        masterTable = new DBTable("pki_pro_record", true);
        this.detailTables = new ArrayList<>();
         
        //pki_applicant
        DBTable proApplicant = new DBTable("pki_pro_applicant", true);
        addChild(new DBNode(proApplicant));

        //pki_pro_visitor
        DBTable proVisitor = new DBTable("pki_pro_visitor", false);
        DBNode proVisitorSchema = new DBNode(proVisitor);
        addChild(proVisitorSchema);

        //pki_pro_visitor_address
        DBTable proVisitorAddress = new DBTable("pki_pro_visitor_address", false);
        proVisitorSchema.addChild(new DBNode(proVisitorAddress));
    }

    
    public DBTable getMasterTable() {
        return masterTable;
    }
    public boolean isSingle() {
        return masterTable.isSingle();
    }
    public List<DBNode> getDetailTables() {
        return detailTables;
    }
    public boolean haveChildren(){
        return detailTables.size() > 0;
    }
    public DBNode getParent() {
        return parent;
    }
    
    
    public final String getTableName() {
        return masterTable.getTableName();
    }
    public final String getEntityName() {
        return masterTable.javaTypeName;
    }
    public final String getDaoName() {
        return masterTable.javaTypeName+"Dao";
    }
    public String getVariableName() {
        return masterTable.javaVarName;
    }
    public String getVariableNameChildList() {
        return isSingle()? getVariableName(): getVariableName()+"List";
    }
    

    public void showMaster(){
        System.out.println("Table Name="+masterTable.getTableName()+", Entity="+ getEntityName() +", Dao="+getDaoName());
    }
    
    
    /**
     *
     * @param child
     * @return
     */
    public final DBNode addChild(DBNode child) {
        child.setParent(this);
        detailTables.add(child);
        return child;
    }
    /**
     *
     * @param parent
     */
    private void setParent(DBNode parent) {
        this.parent = parent;
    }
    
    
    
}