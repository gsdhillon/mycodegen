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
public class JsonMaker {
    private final Package package1;
    private final DBNode dbNode;
    private final List<DBField> fields;

    public JsonMaker(Package package1, DBNode dbNode, List<DBField> fields) throws Exception{
        this.package1 = package1;
        this.dbNode = dbNode;
        this.fields = fields;
    }
    
    public void makeJsonMethods(CodeWriter cw) throws Exception{
        //
        if(dbNode.app != null){
            make_getAppJson_Method(cw);
            make_getJsonFormObject_Method(cw, 1);
        } else {
            if(!dbNode.isSingle()){
                make_getJsonDataListObject_Method(cw);
                make_getJsonFormObject_Method(cw, 2);
            }else{
                make_getJsonFormObject_Method(cw, 1);
            }
        }
    }
    
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_getAppJson_Method(CodeWriter cw) throws Exception{
        cw.methodDec("public", "String", "getAppJson", "", false);
        List<String> jsonObjectList = new LinkedList<>();

        //master table json object
        cw.cn("//"+dbNode.getEntityName()+" table");
        String xmlTagName = dbNode.getVariableName()+"Object";
        cw.varDec("", "String", xmlTagName, "getJsonFormObject()", "");   
        cw.n();
        jsonObjectList.add(xmlTagName);
        
        //each detail table xml tag or tag list
        for(DBNode child : dbNode.getDetailTables()){
            cw.cn("// "+child.getVariableNameChildList()+" Object");
            xmlTagName = child.getVariableNameChildList()+"Object";
            if(child.isSingle()){
                cw.varDec("", "String", xmlTagName, child.getVariableName()+".getJsonFormObject()", ""); //getXmlFormTag(true)
            }else{
                cw.varDec("", "String", xmlTagName, child.getEntityName()+".getJsonDataListObject("+child.getVariableNameChildList()+")", "");
            }
            cw.n();
            jsonObjectList.add(xmlTagName);
        }
        
        //
        cw.cn("// return all json content");
        cw.cn("return").tabIn();
        Delim delim = new Delim("+ \"\\n\" + ");
        for(String jsnoObject: jsonObjectList){
            cw.cn(delim+jsnoObject);
        }
        cw.tabOut().cn(";");
        cw.closeBlock();
    }

    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_getJsonFormObject_Method(CodeWriter cw, int tabCount) throws Exception{
        //
        cw.methodDec("public", "String", "getJsonFormObject", "", false);  
        //
        cw.cn("//"+dbNode.getEntityName()+" object");
        String tagName = dbNode.getVariableName()+"Object";
        cw.cn("JsonObject "+tagName+" = new JsonObject(\""+dbNode.getEntityName()+"\", "+tabCount+")").tabIn(); 
        for (DBField field : fields){
            cw.cn(".attribute(\""+Java.type(field.nameDb)+"\", "+field.getVariableCastedString()+")");
        }
        cw.tabOut().csn("");
        cw.csn("return ("+tagName+".get())");
        //
        cw.closeBlock();
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_getJsonDataListObject_Method(CodeWriter cw) throws Exception{
        //
        cw.methodDec("public static", "String", "getJsonDataListObject", "List<"+dbNode.getEntityName()+"> "+dbNode.getVariableNameChildList(), false);
        //
        cw.cn("//"+dbNode.getVariableNameChildList()+" Object");
        String tagName = dbNode.getVariableNameChildList()+"Object";
        cw.classVarDec("JsonObject", tagName, "\""+dbNode.getVariableNameChildList()+"\", 1"); 
        cw.cn("// add data row object");
        cw.cn("for("+dbNode.getEntityName()+" a"+dbNode.getEntityName()+" : "+dbNode.getVariableNameChildList()+"){").tabIn();
        cw.csn(tagName+".object(a"+dbNode.getEntityName()+".getJsonFormObject())"); 
        cw.closeBlock(); //for loop
        cw.csn("return ("+tagName+".get())");
        //
        cw.closeBlock();
    }
}