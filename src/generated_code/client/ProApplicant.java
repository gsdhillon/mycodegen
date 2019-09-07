package generated_code.client;

import java.util.List;
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
public class ProApplicant {
    //Data fields
    public int formId = 1;		/* INT[10] [NOT NULL] */
    public int empNo = 24196;		/* INT[10] [NOT NULL] */
    public String name = "Gurmeet Singh";		/* TEXT[45] [NOT NULL] */
    public String divisisionNo = "304";		/* TEXT[5] [NOT NULL] */
    public String divisionFullName = "Computer Division";		/* TEXT[45] [NOT NULL] */
    public String desigShortName = "SO/F";		/* TEXT[45] [NOT NULL] */
    public void addHtmlFormTable(Html html){
        // ProApplicant table
        Table proApplicantTable = new Table("ProApplicant", true);
        proApplicantTable
            .addField("FormId", String.valueOf(formId))
            .addField("EmpNo", String.valueOf(empNo))
            .addField("Name", name)
            .addField("DivisisionNo", divisisionNo)
            .addField("DivisionFullName", divisionFullName)
            .addField("DesigShortName", desigShortName)
        ;
        html.add(proApplicantTable);
    }
    public XmlObject getXmlFormObject(){
        // ProApplicant object
        XmlObject xmlObject = new XmlObject()
            .add(new XmlTagBasic("FormId", String.valueOf(formId)))
            .add(new XmlTagBasic("EmpNo", String.valueOf(empNo)))
            .add(new XmlTagBasic("Name", name))
            .add(new XmlTagBasic("DivisisionNo", divisisionNo))
            .add(new XmlTagBasic("DivisionFullName", divisionFullName))
            .add(new XmlTagBasic("DesigShortName", desigShortName))
        ;
        return(xmlObject);
    }
}