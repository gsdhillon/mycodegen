package generated_code.client;

import java.util.List;
import java.util.ArrayList;
import lib.html.Html;
import lib.html.Table;
import lib.xml.Xml;
import lib.xml.XmlObject;
import lib.xml.XmlTagBasic;
import lib.xml.XmlTagObject;

/**
 * 
 * @author - Gurmeet Singh, gsdhillon@gmail.com
 * @createdOn - 03/09/2019 21:43:33
 */
public class ProVisitor {
    //Data fields
    public long formId = 1;		/* LONG[12] [NOT NULL] */
    public int sNo = 1;		/* INT[5] [NOT NULL] */
    public String name = "Ishjyot Kaur";		/* TEXT[100] [NOT NULL] */
    public String phoneNo = "9869117976";		/* TEXT[20]  */
    public String org = "AECS-04";		/* TEXT[100]  */
    public String desig = "Student";		/* TEXT[100]  */
    public String dob = "14/11/2006";		/* DATE[dd/mm/yyyy]  */
    //ProVisitorAddress List
    public List<ProVisitorAddress> proVisitorAddressList = new ArrayList<>();
    public void addHtmlFormTable(Html html){
        // ProVisitor table
        Table proVisitorTable = new Table("ProVisitor", true);
        proVisitorTable
            .addField("FormId", String.valueOf(formId))
            .addField("SNo", String.valueOf(sNo))
            .addField("Name", name)
            .addField("PhoneNo", phoneNo)
            .addField("Org", org)
            .addField("Desig", desig)
            .addField("Dob", dob)
        ;
        html.add(proVisitorTable);
    }
    public static void addHtmlDataTable(Html html, List<ProVisitor> proVisitorList){
        // ProVisitor List Table
        Table proVisitorListTable = new Table("proVisitorList", true);
        // add header
        addHeader(proVisitorListTable);
        // add data
        for(ProVisitor aProVisitor : proVisitorList){
            aProVisitor.addData(proVisitorListTable);
        }
        // add table to html
        html.add(proVisitorListTable);
    }
    private static void addHeader(Table table){
        table.head(new String[]{
            "FormId",
            "SNo",
            "Name",
            "PhoneNo",
            "Org",
            "Desig",
            "Dob",
        });
    }
    private void addData(Table table){
        table.data(new String[]{
            String.valueOf(formId),
            String.valueOf(sNo),
            name,
            phoneNo,
            org,
            desig,
            dob,
        });
    }
    public XmlObject getXmlFormObject(){
        // ProVisitor object
        XmlObject xmlObject = new XmlObject()
            .add(new XmlTagBasic("FormId", String.valueOf(formId)))
            .add(new XmlTagBasic("SNo", String.valueOf(sNo)))
            .add(new XmlTagBasic("Name", name))
            .add(new XmlTagBasic("PhoneNo", phoneNo))
            .add(new XmlTagBasic("Org", org))
            .add(new XmlTagBasic("Desig", desig))
            .add(new XmlTagBasic("Dob", dob))
        ;
        return(xmlObject);
    }
    public static void addXmlDataListTag(Xml xml, List<ProVisitor> proVisitorList){
        // ProVisitor object list
        XmlObject xmlObject = new XmlObject();
        for(ProVisitor proVisitor: proVisitorList){
            xmlObject.add(new XmlTagObject("ProVisitor", proVisitor.getXmlFormObject()));
        }
        xml.addTag(new XmlTagObject("ProVisitorList", xmlObject));
    }
}