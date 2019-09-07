package mycodegen.generator.java.client;

import java.util.LinkedList;
import java.util.List;
import application.DBNode;
import application.Package;
import mycodegen.generator.java.CodeWriter;
import mycodegen.generator.java.Java;
import mycodegen.jdbc.DBField;
import mycodegen.utils.Delim;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class HtmlMaker {
    private final Package packageInfo;
    private final DBNode dbNode;
    private final List<DBField> fields;

    public HtmlMaker(Package packageInfo, DBNode dbNode, List<DBField> fields) throws Exception{
        this.packageInfo = packageInfo;
        this.dbNode = dbNode;
        this.fields = fields;
    }
    
    public void makeHtmlMethods(CodeWriter cw) throws Exception{
        //
        make_addHtmlFormTable_Method(cw);
        if(dbNode.app != null){
            make_addAppHtml_Method(cw);
        } else {
            if(!dbNode.isSingle()){
                make_getHtmlDataTable_Method(cw);
            }
        }
    }
    
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_addAppHtml_Method(CodeWriter cw) throws Exception{
        cw.cn("@Override");
        cw.methodDec("public", "void", "addAppHtml", "Html html", false);
       
        // master html form table
        cw.cn("// "+dbNode.getEntityName());
        cw.csn("addHtmlFormTable(html)");
        
        // each detail html form table /  data table
        for(DBNode child : dbNode.getDetailTables()){
            if(child.isSingle()){
                cw.cn("// "+child.getEntityName());
                cw.csn(child.getVariableName()+".addHtmlFormTable(html)");
            }else{
                cw.cn("// "+child.getEntityName()+ "List");
                cw.csn(child.getEntityName()+".addHtmlDataTable(html, "+child.getVariableNameChildList()+")");
            }
        }
        cw.closeBlock();
    }

    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_addHtmlFormTable_Method(CodeWriter cw) throws Exception{
        //
        cw.methodDec("public", "void", "addHtmlFormTable", "Html html", false);
        //
        cw.cn("// "+dbNode.getEntityName()+" table");
        String tableName = dbNode.getVariableName()+"Table";
        cw.classVarDec("Table", tableName, "\""+dbNode.getEntityName()+"\", true");
        cw.cn(tableName).tabIn();
        for (DBField field : fields){
        //    System.out.println("type "+field.type);
            cw.cn(".addField(\""+Java.type(field.nameDb)+"\", "+field.getVariableCastedString()+")");
        }
        cw.tabOut().csn("");
        cw.csn("html.add("+tableName+")");
        //
        cw.closeBlock();
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_getHtmlDataTable_Method(CodeWriter cw) throws Exception{
        //
        cw.methodDec("public static", "void", "addHtmlDataTable", "Html html, List<"+dbNode.getEntityName()+"> "+dbNode.getVariableNameChildList(), false);
        //
        cw.cn("// "+dbNode.getEntityName()+" List Table");
        String tableName = dbNode.getVariableNameChildList()+"Table";
        cw.classVarDec("Table", tableName, "\""+dbNode.getVariableNameChildList()+"\", true");
        cw.cn("// add header");
        cw.csn("addHeader("+tableName+")");
        cw.cn("// add data");
        cw.cn("for("+dbNode.getEntityName()+" a"+dbNode.getEntityName()+" : "+dbNode.getVariableNameChildList()+"){").tabIn();
        cw.csn("a"+dbNode.getEntityName()+".addData("+tableName+")");
        cw.closeBlock(); //for loop
        cw.cn("// add table to html");
        cw.csn("html.add("+tableName+")");
        //
        cw.closeBlock();
        //
        make_getHtmlTableHead_Method(cw);
        make_getHtmlTableDataRow_Method(cw);
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_getHtmlTableHead_Method(CodeWriter cw) throws Exception{
        cw.methodDec("private static", "void", "addHeader", "Table table", false);
        cw.cn("table.head(new String[]{").tabIn();
         for (DBField field : fields){
            cw.cn("\""+Java.type(field.nameDb)+"\",");
        }
        cw.tabOut().cn("});");
        cw.closeBlock();
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_getHtmlTableDataRow_Method(CodeWriter cw) throws Exception{
        cw.methodDec("private", "void", "addData", "Table table", false);
        cw.cn("table.data(new String[]{").tabIn();
        for (DBField field : fields){
            cw.cn(field.getVariableCastedString()+",");
        }
        cw.tabOut().cn("});");
        cw.closeBlock();
    }
}