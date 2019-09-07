package mycodegen.generator.java.client;

import java.util.List;
import application.DBNode;
import application.Package;
import mycodegen.generator.java.CodeWriter;
import mycodegen.generator.java.Java;
import mycodegen.jdbc.DBField;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class XmlMaker {
    private final Package package1;
    private final DBNode dbNode;
    private final List<DBField> fields;

    public XmlMaker(Package package1, DBNode dbNode, List<DBField> fields) throws Exception{
        this.package1 = package1;
        this.dbNode = dbNode;
        this.fields = fields;
    }
    
    public void makeXmlMethods(CodeWriter cw) throws Exception{
        //
        make_getXmlFormTag_Method(cw);
        if(dbNode.app != null){
            make_addAppXml_Method(cw);
        } else {
            if(!dbNode.isSingle()){
                make_addXmlDataListTag_Method(cw);
            }
        }
    }
    
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_addAppXml_Method(CodeWriter cw) throws Exception{
        cw.cn("@Override");
        cw.methodDec("public", "void", "addAppXml", "Xml xml", false);

        // master xml tag
        cw.cn("// "+dbNode.getEntityName());
        cw.csn("xml.addTag(new XmlTagObject(\""+dbNode.getEntityName()+"\", getXmlFormObject()))");
     
        //each detail table xml tag or tag list
        for(DBNode child : dbNode.getDetailTables()){
            if(child.isSingle()){
                cw.cn("// "+child.getEntityName());
                cw.csn("xml.addTag(new XmlTagObject(\""+child.getEntityName()+"\", "+child.getVariableName()+".getXmlFormObject()))");
            }else{
                cw.cn("// "+child.getEntityName()+ "List");
                cw.csn(child.getEntityName()+".addXmlDataListTag(xml, "+child.getVariableNameChildList()+")");
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
    private void make_getXmlFormTag_Method(CodeWriter cw) throws Exception{
        cw.methodDec("public", "XmlObject", "getXmlFormObject", "", false);
        cw.cn("// "+dbNode.getEntityName()+" object");
        cw.cn("XmlObject xmlObject = new XmlObject()").tabIn();
        for (DBField field : fields){
            cw.cn(".add(new XmlTagBasic(\""+Java.type(field.nameDb)+"\", "+field.getVariableCastedString()+"))");
        }
        cw.tabOut().csn("");
        cw.csn("return(xmlObject)");
        cw.closeBlock();
    }
    /**
     * 
     * @param cw
     * @param fields
     * @throws Exception 
     */
    private void make_addXmlDataListTag_Method(CodeWriter cw) throws Exception{
        cw.methodDec("public static", "void", "addXmlDataListTag", "Xml xml, List<"+dbNode.getEntityName()+"> "+dbNode.getVariableNameChildList(), false);
        cw.cn("// "+dbNode.getEntityName()+" object list");
        cw.csn("XmlObject xmlObject = new XmlObject()");
        cw.cn("for("+dbNode.getEntityName()+" "+dbNode.getVariableName()+": "+dbNode.getVariableNameChildList()+"){").tabIn();
        cw.csn("xmlObject.add(new XmlTagObject(\""+dbNode.getEntityName()+"\", "+dbNode.getVariableName()+".getXmlFormObject()))");
        cw.closeBlock();
        cw.csn("xml.addTag(new XmlTagObject(\""+dbNode.getEntityName()+"List\", xmlObject))");
        cw.closeBlock();
    }
}