package mycodegen.generator.java.server;

import mycodegen.generator.java.CodeWriter;
import mycodegen.generator.java.Java;
import mycodegen.jdbc.DBField;
import mycodegen.utils.Delim;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DaoMakerGet {
    private final DaoMaker dm;
    /**
     * 
     * @param daoMaker 
     */
    public DaoMakerGet(DaoMaker daoMaker) {
        this.dm = daoMaker;
    }
    /**
     * 
     * @param cw
     * @throws Exception 
     */
    public void makeGetMethod(CodeWriter cw) throws Exception{
        //make get method header
        cw.methodDec("public static", "void", "get", "Connection conn, "+dm.entityClassName+" entity, String logHeader", true);
        
        cw.csn("String query = \"\"");
        //start try block
        cw.tryBlock();
        //select query
        makeSelectQuery(cw);
        //make prepared statement and set params code
        makePSSelectCode(cw);
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
        cw.tryWithResourcesBlock("PreparedStatement ps = conn.prepareStatement(query)");
        //Set params
        cw.csn("int paramCount = 0");
        makeSetSelectParamList(cw);
        //execute update
        cw.tryWithResourcesBlock("ResultSet rs = ps.executeQuery()");
        cw.cn("if ( !rs.next()) {").tabIn();
        cw.csn("throw new Exception(\"Select \"+logHeader+\" failed no record found!\")");
        cw.closeBlock();//if
        makeFieldGetList(cw);
        cw.closeBlock();//try w. r.
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
        makeFieldListSelect(cw);
        //
        cw.tabOut().cn("+ \"from \"").tabIn();
        cw.cn("+ \""+dm.tableName+" \"");
        cw.tabOut().cn("+ \"where \"").tabIn();
        //Param List
        makeWhereCondition(cw);
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
            if(!field.isPrimary){
                cw.csn("entity."+Java.var(field.nameDb)+" = rs.get"+Java.getTypeNameResultSet(field.type)+"(\""+field.nameDb+"\")");
            }
        }
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeWhereCondition(CodeWriter cw) throws Exception{
        cw.cn("//primary key field list");
        Delim delim = new Delim("AND ");
        for (DBField field : dm.fields){
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
    private void makeFieldListSelect(CodeWriter cw) throws Exception{
        cw.cn("//field list");
        Delim delim = new Delim(",");
        for (DBField field : dm.fields){
            if(!field.isPrimary){
                cw.cn("+ \""+delim+field.getQuerySelect()+" \"");
            }
        }
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeSetSelectParamList(CodeWriter cw) throws Exception{
        cw.cn("//set params");
        for (DBField field : dm.fields){
            if(field.isPrimary){
                cw.csn("ps."+Java.getPsSetMethod(field.type)+"((++paramCount), entity."+Java.var(field.nameDb)+")");
            }
        }
    }
}