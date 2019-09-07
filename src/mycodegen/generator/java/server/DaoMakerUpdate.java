package mycodegen.generator.java.server;

import mycodegen.generator.java.CodeWriter;
import mycodegen.generator.java.Java;
import mycodegen.jdbc.DBField;
import mycodegen.utils.Delim;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class DaoMakerUpdate {
    private final DaoMaker dm;
    /**
     * 
     * @param daoMaker 
     */
    public DaoMakerUpdate(DaoMaker daoMaker) {
        this.dm = daoMaker;
    }
    /**
     * 
     * @param cw
     * @throws Exception 
     */
    public void makeUpdateMethod(CodeWriter cw) throws Exception{
        //create update method header
        cw.methodDec("public static", "void", "update", "Connection conn, "+dm.entityClassName+" entity, String logHeader", true);
        
        cw.csn("String query = \"\"");
        //start try block
        cw.tryBlock();
        //update query
        makeUpdateQuery(cw);
        //make prepared statement and set params code
        makePSUpdateCode(cw);
        //catch block
        cw.catchBlock();
        cw.csn("System.out.println(\"Update \"+logHeader+\" failed! query: \"+query)");
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
    private void makeUpdateQuery(CodeWriter cw) throws Exception{
        //insert into
        cw.cn("//update query");
        cw.cn("query =");
        cw.cn("\"update "+dm.tableName+" set \"").tabIn();
        //Field List for update
        makeFieldUpdateList(cw);
        //values
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
    private void makePSUpdateCode(CodeWriter cw) throws  Exception{
        //make prepared statement
        cw.cn("//prepareStatement");
        cw.tryWithResourcesBlock("PreparedStatement ps = conn.prepareStatement(query)");
        //Set params  
        cw.csn("int paramCount = 0");
        makeSetUpdateParamList(cw);
        //execute update
        cw.csn("int updateCount = ps.executeUpdate()");
        cw.cn("if (updateCount != 1) {").tabIn();
        cw.csn("throw new Exception(\"Update \"+logHeader+\" failed updateCount = \"+updateCount+\"!\")");
        cw.closeBlock();//if
        cw.closeBlock();//try w. r.
    }
     /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void makeFieldUpdateList(CodeWriter cw) throws Exception{
        cw.cn("//update field list");
        Delim delim = new Delim(",");
        for (DBField field : dm.fields){
            if(!field.isPrimary){
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
    private void makeSetUpdateParamList(CodeWriter cw) throws Exception{
        cw.cn("//set params");
        for (DBField field : dm.fields){
            if(!field.isPrimary){
                cw.csn("ps."+Java.getPsSetMethod(field.type)+"((++paramCount), entity."+Java.var(field.nameDb)+")");
            }
        }
        for (DBField field : dm.fields){
            if(field.isPrimary){
                cw.csn("ps."+Java.getPsSetMethod(field.type)+"((++paramCount), entity."+Java.var(field.nameDb)+")");
            }
        }
    }
}