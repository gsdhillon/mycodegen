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
public class ProRecord extends ApplicationForm {
    //Data fields
    public long formId = 1;		/* LONG[12] [NOT NULL] */
    public String applyDate = "24/08/2019";		/* DATE[dd/mm/yyyy] [NOT NULL] */
    public String dateOfVisit = "25/08/2019";		/* DATE[dd/mm/yyyy] [NOT NULL] */
    public String expectedTime = "09:00:00";		/* TEXT[8] [NOT NULL] */
    public String purpose = "Purpose of visit";		/* TEXT[200] [NOT NULL] */
    //ProApplicant
    public ProApplicant proApplicant = new ProApplicant();
    //ProVisitor List
    public List<ProVisitor> proVisitorList = new ArrayList<>();
    public void addHtmlFormTable(Html html){
        // ProRecord table
        Table proRecordTable = new Table("ProRecord", true);
        proRecordTable
            .addField("FormId", String.valueOf(formId))
            .addField("ApplyDate", applyDate)
            .addField("DateOfVisit", dateOfVisit)
            .addField("ExpectedTime", expectedTime)
            .addField("Purpose", purpose)
        ;
        html.add(proRecordTable);
    }
    @Override
    public void addAppHtml(Html html){
        // ProRecord
        addHtmlFormTable(html);
        // ProApplicant
        proApplicant.addHtmlFormTable(html);
        // ProVisitorList
        ProVisitor.addHtmlDataTable(html, proVisitorList);
    }
    public XmlObject getXmlFormObject(){
        // ProRecord object
        XmlObject xmlObject = new XmlObject()
            .add(new XmlTagBasic("FormId", String.valueOf(formId)))
            .add(new XmlTagBasic("ApplyDate", applyDate))
            .add(new XmlTagBasic("DateOfVisit", dateOfVisit))
            .add(new XmlTagBasic("ExpectedTime", expectedTime))
            .add(new XmlTagBasic("Purpose", purpose))
        ;
        return(xmlObject);
    }
    @Override
    public void addAppXml(Xml xml){
        // ProRecord
        xml.addTag(new XmlTagObject("ProRecord", getXmlFormObject()));
        // ProApplicant
        xml.addTag(new XmlTagObject("ProApplicant", proApplicant.getXmlFormObject()));
        // ProVisitorList
        ProVisitor.addXmlDataListTag(xml, proVisitorList);
    }
}