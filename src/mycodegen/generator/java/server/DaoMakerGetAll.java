package mycodegen.generator.java.server;

import mycodegen.generator.java.CodeWriter;
import mycodegen.generator.java.Java;
import mycodegen.jdbc.DBField;
import mycodegen.utils.Delim;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DaoMakerGetAll {
    private final DaoMaker dm;
    /**
     * 
     * @param daoMaker 
     */
    public DaoMakerGetAll(DaoMaker daoMaker) {
        this.dm = daoMaker;
    }
    /**
     * 
     * @param cw
     * @throws Exception 
     */
    public void makeGetAllMethod(CodeWriter cw) throws Exception{
        //make get method header
        cw.cn("@SuppressWarnings(\"UseSpecificCatch\")");
        String entityParam = "";
        if(dm.parentNode != null){
            entityParam = dm.parentEntityClassName+" entity, ";
        }
        cw.methodDec("public static", "List<"+dm.entityClassName+">", "getAll", "Connection conn, "+entityParam+"String logHeader", true);
        
        cw.csn("String query = \"\"");
        //start try block
        cw.tryBlock();
        //select query
        makeSelectQuery(cw);
        //make prepared statement and set params code
        makePSSelectCode(cw);
        //return list
        cw.csn("return "+ dm.dbNode.getVariableName()+"List");
        //catch block
        cw.catchBlock();
        cw.csn("System.out.println(\"Select \"+logHeader+\" failed! query: \"+query)");
        cw.csn("throw e");         
        cw.closeBlock();

        //close method body
        cw.closeBlock();
    }
    
    
     /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makePSSelectCode(CodeWriter cw) throws  Exception{
        //make prepared statement
        cw.cn("//prepareStatement");
        cw.csn("List<"+dm.entityClassName+"> "+dm.dbNode.getVariableName()+"List = new ArrayList<>()");
        cw.tryWithResourcesBlock("PreparedStatement ps = conn.prepareStatement(query)");
         //Set params  
        makeSetSelectAllParamList(cw);
        //execute update
        cw.tryWithResourcesBlock("ResultSet rs = ps.executeQuery()"); //rs
        cw.cn("while ( rs.next()) {").tabIn();
        cw.csn(dm.entityClassName+" "+dm.dbNode.getVariableName()+" = new "+dm.entityClassName+"()");
        makeFieldGetList(cw);
        cw.csn(dm.dbNode.getVariableName()+"List.add("+dm.dbNode.getVariableName()+")");
        cw.closeBlock();//while
        cw.closeBlock();//try w. r. rs
        cw.closeBlock();//try w. r.
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeSelectQuery(CodeWriter cw) throws Exception{
        //insert into
        cw.cn("//select query");
        cw.cn("query =");
        cw.cn("\"select \"").tabIn();
        //Field List for Select
        makeFieldListSelectAll(cw);
        //
        cw.tabOut().cn("+ \"from \"").tabIn();
        cw.cn("+ \""+dm.tableName+" \"");
        //Param List
        makeWhereConditionSelectAll(cw);
        //close query
        cw.tabOut().csn("+ \";\"");
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeFieldGetList(CodeWriter cw) throws Exception{
        cw.cn("//get field list");
        for (DBField field : dm.fields){
            cw.csn(dm.dbNode.getVariableName()+"."+Java.var(field.nameDb)+" = rs.get"+Java.getTypeNameResultSet(field.type)+"(\""+field.nameDb+"\")");
        }
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeWhereConditionSelectAll(CodeWriter cw) throws Exception{
        if(dm.parentNode == null){
            return;
        }
        cw.tabOut().cn("+ \"where \"").tabIn();
        cw.cn("// parent primary key field list");
        Delim delim = new Delim("AND ");
        for (DBField field : dm.parentFields){
            if(field.isPrimary){
                cw.cn("+ \""+delim+field.nameDb+" = "+field.getQueryParam()+" \"");
            }
        }
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeFieldListSelectAll(CodeWriter cw) throws Exception{
        cw.cn("//field list");
        Delim delim = new Delim(",");
        for (DBField field : dm.fields){
            cw.cn("+ \""+delim+field.getQuerySelect()+" \"");
        }
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeSetSelectAllParamList(CodeWriter cw) throws Exception{
        if(dm.parentNode == null){
            return;
        }
        cw.cn("//set params");
        cw.csn("int paramCount = 0");
        for (DBField field : dm.parentFields){
            if(field.isPrimary){
                cw.csn("ps."+Java.getPsSetMethod(field.type)+"((++paramCount), entity."+Java.var(field.nameDb)+")");
            }
        }
    }
}