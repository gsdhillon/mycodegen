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
public class ProVisitorAddress {
    //Data fields
    public int formId = 1;		/* INT[10] [NOT NULL] */
    public int visSno = 1;		/* INT[5] [NOT NULL] */
    public int sNo = 1;		/* INT[5] [NOT NULL] */
    public String addressType = "Permanent";		/* TEXT[45] [NOT NULL] */
    public String house = "s/o Gurmeet Singh";		/* TEXT[45]  */
    public String street = "Vill-Burjwali,P.O.-20GG";		/* TEXT[45]  */
    public String city = "Sriganganagar";		/* TEXT[45]  */
    public String state = "Rajasthan";		/* TEXT[45]  */
    public String pin = "335001";		/* TEXT[45]  */
    public String country = "India";		/* TEXT[45]  */
    public void addHtmlFormTable(Html html){
        // ProVisitorAddress table
        Table proVisitorAddressTable = new Table("ProVisitorAddress", true);
        proVisitorAddressTable
            .addField("FormId", String.valueOf(formId))
            .addField("VisSno", String.valueOf(visSno))
            .addField("SNo", String.valueOf(sNo))
            .addField("AddressType", addressType)
            .addField("House", house)
            .addField("Street", street)
            .addField("City", city)
            .addField("State", state)
            .addField("Pin", pin)
            .addField("Country", country)
        ;
        html.add(proVisitorAddressTable);
    }
    public static void addHtmlDataTable(Html html, List<ProVisitorAddress> proVisitorAddressList){
        // ProVisitorAddress List Table
        Table proVisitorAddressListTable = new Table("proVisitorAddressList", true);
        // add header
        addHeader(proVisitorAddressListTable);
        // add data
        for(ProVisitorAddress aProVisitorAddress : proVisitorAddressList){
            aProVisitorAddress.addData(proVisitorAddressListTable);
        }
        // add table to html
        html.add(proVisitorAddressListTable);
    }
    private static void addHeader(Table table){
        table.head(new String[]{
            "FormId",
            "VisSno",
            "SNo",
            "AddressType",
            "House",
            "Street",
            "City",
            "State",
            "Pin",
            "Country",
        });
    }
    private void addData(Table table){
        table.data(new String[]{
            String.valueOf(formId),
            String.valueOf(visSno),
            String.valueOf(sNo),
            addressType,
            house,
            street,
            city,
            state,
            pin,
            country,
        });
    }
    public XmlObject getXmlFormObject(){
        // ProVisitorAddress object
        XmlObject xmlObject = new XmlObject()
            .add(new XmlTagBasic("FormId", String.valueOf(formId)))
            .add(new XmlTagBasic("VisSno", String.valueOf(visSno)))
            .add(new XmlTagBasic("SNo", String.valueOf(sNo)))
            .add(new XmlTagBasic("AddressType", addressType))
            .add(new XmlTagBasic("House", house))
            .add(new XmlTagBasic("Street", street))
            .add(new XmlTagBasic("City", city))
            .add(new XmlTagBasic("State", state))
            .add(new XmlTagBasic("Pin", pin))
            .add(new XmlTagBasic("Country", country))
        ;
        return(xmlObject);
    }
    public static void addXmlDataListTag(Xml xml, List<ProVisitorAddress> proVisitorAddressList){
        // ProVisitorAddress object list
        XmlObject xmlObject = new XmlObject();
        for(ProVisitorAddress proVisitorAddress: proVisitorAddressList){
            xmlObject.add(new XmlTagObject("ProVisitorAddress", proVisitorAddress.getXmlFormObject()));
        }
        xml.addTag(new XmlTagObject("ProVisitorAddressList", xmlObject));
    }
}