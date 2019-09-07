package generated_code.client;

import lib.html.Table;
import lib.json.Json;
import lib.html.Html;
import lib.xml.Xml;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public abstract class ApplicationForm {
    //Application header and version detail
    public final String orgName = "Bhabha Atomic Research Centre";
    public final String orgUnit = "Computer Division, E&I Group";
    public final String appShortName = "PRO_PERMIT";
    public final String appFullName = "PRO Permit Application";
    public final int xmlCurrentVersion = 1;
    //Application work flow detail
    public String currentStatus = "";
    public String pendingWithAuthEmpNo = "";

    protected Table getHeaderTable() {
        return
        new Table(false).td("\n\t<h1>"+orgName+"</h1>\n"
            + "\t<h2>"+orgUnit+"</h2>\n"
            + "\t<h3>"+appFullName+"</h3>\n", "div align=center"
        );
    }
    
    /**
     * 
     * @return 
     */
    public String getHtml(){
        Html html = new Html();
        //Application Header
        html.add(getHeaderTable());
        addAppHtml(html);
        // return all html contents
        return html.htmlContent();
    }
    public abstract void addAppHtml(Html html);

    
    public String getXml(){
        Xml xml = new Xml();
        addAppXml(xml);
        return xml.getText();
    }
    public abstract void addAppXml(Xml xml);
    
    public String getJson(){
        Json json = new Json();
        addAppJson(json);
        return json.getText();
    }
//    public abstract void addAppJson(Json json);
    public void addAppJson(Json json){}//not made available yet
}
