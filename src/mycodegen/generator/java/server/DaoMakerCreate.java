package mycodegen.generator.java.server;

import mycodegen.generator.java.CodeWriter;
import mycodegen.generator.java.Java;
import mycodegen.jdbc.DBField;
import mycodegen.utils.Delim;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DaoMakerCreate {
    private final DaoMaker dm;
    /**
     * 
     * @param daoMaker 
     */
    public DaoMakerCreate(DaoMaker daoMaker) {
        this.dm = daoMaker;
    }
   
   
    /**
     * 
     * @param cw
     * @throws Exception 
     */   
    public void makeCreateMethod(CodeWriter cw) throws Exception{
        //make create method header
        cw.methodDec("public static", "void", "create", "Connection conn, "+dm.entityClassName+" entity, String logHeader", true);
        cw.csn("String query = \"\"");
        //start try block
        cw.tryBlock();
        //insert query
        makeInsertQuery(cw);
        //make prepared statement and set params code
        makePSInsertCode(cw);
        //catch block
        cw.catchBlock();
        cw.csn("System.out.println(\"Insert \"+logHeader+\" failed! query: \"+query)");
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
    private void makeInsertQuery(CodeWriter cw) throws Exception{
        //insert into
        cw.cn("//insert query");
        cw.cn("query =");
        cw.cn("\"insert into "+dm.tableName+"(\"").tabIn();
        //Field List
        makeFieldListInsert(cw);
        //values
        cw.tabOut().cn("+ \") values (\"").tabIn();
        //Param List
        makeInsertParamList(cw);
        //close query
        cw.tabOut().csn("+ \")\"");
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makePSInsertCode(CodeWriter cw) throws  Exception{
        //make prepared statement
        cw.cn("//prepareStatement");
        cw.tryWithResourcesBlock("PreparedStatement ps = conn.prepareStatement(query)");
        //Set params  
        cw.csn("int paramCount = 0");
        makeSetInsertParamList(cw);
        //execute update
        cw.csn("int updateCount = ps.executeUpdate()");
        cw.cn("if (updateCount != 1) {").tabIn();
        cw.csn("throw new Exception(\"Create \"+logHeader+\" failed updateCount = \"+updateCount+\"!\")");
        cw.closeBlock();//if
        cw.closeBlock();//try w. r.
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeFieldListInsert(CodeWriter cw) throws Exception{
        cw.cn("//field list");
        Delim delim = new Delim(",");
        for (DBField field : dm.fields){
            cw.cn("+ \""+delim+field.nameDb+" \"");
        }
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeInsertParamList(CodeWriter cw) throws Exception{
        cw.cn("//param list");
        Delim delim = new Delim(",");
        for (DBField field : dm.fields){
            cw.cn("+ \""+delim+field.getQueryParam()+" \" // "+field.nameDb+"");
        }
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeSetInsertParamList(CodeWriter cw) throws Exception{
        cw.cn("//set params");
        for (DBField field : dm.fields){
            cw.csn("ps."+Java.getPsSetMethod(field.type)+"((++paramCount), entity."+Java.var(field.nameDb)+")");
        }
    }
   
}