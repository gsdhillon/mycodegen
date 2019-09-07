package mycodegen.generator.java.client;

import mycodegen.jdbc.DBField;
import mycodegen.jdbc.DB;
import java.util.List;
import application.Author;
import application.DBNode;
import application.Package;
import mycodegen.generator.java.ClassGeneratorFacade;
import mycodegen.generator.java.CodeWriter;
import mycodegen.jdbc.DBFactory;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 * @createdOn 
 */
public class ClassGeneratorClient extends ClassGeneratorFacade{
    /**
     * 
     * @param dbNode
     * @param package1
     * @param author
     * @throws Exception 
     */
    public ClassGeneratorClient(
            DBNode dbNode, 
            Package package1, 
            Author author 
    ) throws Exception {
        super(
                dbNode, 
                package1, 
                author 
        );
    }

    /**
     * 
     * @param cw
     * @throws Exception 
     */
    @Override
    protected void getApplicationFieldsDeclaration(CodeWriter cw) throws Exception{
        // comment
        cw.cn("//Application header and version detail");
        // orgName
        cw.csn("public final String orgName = \""+dbNode.app.orgName+"\"");
        // orgUnit
        cw.csn("public final String orgUnit = \""+dbNode.app.orgUnit+"\"");
        // appShortName
        cw.csn("public final String appShortName = \""+dbNode.app.appShortName+"\"");
        // appFullName
        cw.csn("public final String appFullName = \""+dbNode.app.appFullName+"\"");
        // xmlCurrentVersion
        cw.csn("public final int xmlCurrentVersion = "+dbNode.app.xmlCurrentVersion);
        //
        cw.cn("//Application work flow detail");
        // currentStatus
        cw.csn("public String currentStatus = \"\"");
        // pendingWithAuthEmpNo
        cw.csn("public String pendingWithAuthEmpNo = \"\"");
        
        //
        cw.n();
        cw.cn("protected Table getHeaderTable() {").tabIn();
        cw.cn("return");
        cw.cn("new Table(false).td(\"\\n\\t<h1>\"+orgName+\"</h1>\\n\"").tabIn();
        cw.cn("+ \"\\t<h2>\"+orgUnit+\"</h2>\\n\"");        
        cw.cn("+ \"\\t<h3>\"+appFullName+\"</h3>\\n\", \"div align=center\"");        
        cw.tabOut().csn(")"); 
        cw.tabOut().cn("}");
    }
    
    @Override
    protected String getPackageName(){
        return package1.clientPackageName;
    }
    
    @Override
    protected String getClassName(){
        return dbNode.getEntityName();
    }
    
    @Override
    protected void makeClassDeclaration(StringBuilder classDeclarationSb) throws Exception{
        //Master Class
        if(dbNode.app != null){
            classDeclarationSb.append("public class ").append(getClassName()).append(" extends ApplicationForm {\n");
        }else {
            classDeclarationSb.append("public class ").append(getClassName()).append(" {\n");
        }
    }
    
    @Override
    protected void makeMasterDataFields(CodeWriter cw) throws Exception{
        addImport("java.util.List");
        //
        DB db = DBFactory.createDB().connect();
        List<DBField> tableFields = db.getTableFields(this.dbNode.getTableName());
        boolean blobPresent = false;
        for (DBField field : tableFields){
            if(field.isBlob()){
                blobPresent = true;
            }
            field.getDeclarationStatement(cw);
        }
        if(blobPresent){
            addImport("javax.sql.rowset.serial.SerialBlob");
        }
    }
    
    @Override
    protected void makeDetailsFields(CodeWriter cw) throws Exception{
        addImport("java.util.ArrayList");
        for(DBNode child : dbNode.getDetailTables()) {
            //
            if(child.isSingle()){
                cw.cn("//"+child.getEntityName());
                cw.csn("public "+child.getEntityName()+" "+child.getVariableNameChildList()+" = new "+child.getEntityName()+"()");
            }else{
                cw.cn("//"+child.getEntityName()+" List");
                cw.csn("public List<"+child.getEntityName()+"> "+child.getVariableNameChildList()+" = new ArrayList<>()");
            }
            
           
        }
    }
    
    @Override
    protected void makeMethods(CodeWriter cw) throws Exception{
        //
        DB db = DBFactory.createDB().connect();
        List<DBField> fields = db.getTableFields(this.dbNode.getTableName());
        //html
        addImport("lib.html.Html");
        addImport("lib.html.Table");
        HtmlMaker htmlMaker = new HtmlMaker(package1, this.dbNode, fields);
        htmlMaker.makeHtmlMethods(cw);
        //xml
        addImport("lib.xml.Xml");
        addImport("lib.xml.XmlObject");
        addImport("lib.xml.XmlTagBasic");
        addImport("lib.xml.XmlTagObject");
        XmlMaker xmlMaker = new XmlMaker(package1, this.dbNode, fields);
        xmlMaker.makeXmlMethods(cw);
//        //json
//        addImport("lib.json.Json");
//        addImport("lib.json.JsonArray");
//        addImport("lib.json.JsonObject");
//        addImport("lib.json.JsonBasicAttribute");
//        addImport("lib.json.JsonObjectAttribute");
//        addImport("lib.json.JsonArrayAttribute");
//        JsonMaker jsonMaker = new JsonMaker(package1, this.dbNode, fields);
//        jsonMaker.makeJsonMethods(cw);
        //
        db.close();
    }
}