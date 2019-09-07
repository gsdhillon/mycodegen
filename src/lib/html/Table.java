package lib.html;

/**
 *
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class Table {
    private String title = null;
    private final String start = "\n<table width='750'>\n<tr>\n";
    private final StringBuilder body = new StringBuilder();
    private final String end = "</tr>\n</table>\n\n";
    private String thClass;
    private String tdClass;
    /**
     * 
     * @param border 
     */
    public Table(boolean border) {
        decorate(border);
    }
    
    public Table(String title, boolean border){
        this.title = title;
        decorate(border);
    }

    private void decorate(boolean border) {
        if(border){
            thClass = " class=\"b2\" align=\"left\" ";
            tdClass = " class=\"b1\" ";
        }else{
            thClass = " class=\"b0\" align=\"left\" ";
            tdClass = " class=\"b0\" ";
        }
    }

    public Table row(){
        body.append("</tr>\n<tr>\n");
        return this;
    }
    public Table head(String[] names){
        for(String name: names){
            th(name);
        }
        return this;
    }
    public Table data(String[] texts){
        row();
        for(String text: texts){
            td(text);
        }
        return this;
    }
    
    public int fieldPerRow = 2;
    private int countLabeledFields = 0;
    
    public Table addField(String label, String value){
        return addField(label, value, 0);
    }
    public Table addField(String label, String value, int colSpan){
        if(countLabeledFields>0 && countLabeledFields%2==0){
            row();
        }
        th(label+": ");
        td(value, colSpan);
        countLabeledFields += 1 + (colSpan>1? colSpan/2 : 0);
        return this;
    }
    
    public Table th(String name){
        return th(name, 0, "");
    }
    public Table th(String name, int widthPercent){
        return th(name, widthPercent, "");
    }
    public Table th(String name, int widthPercent, String params){
        String width = "";
        if(widthPercent > 0){
            width = " width=\""+widthPercent+"%\" ";
        }
        body.append("\t")
            .append("<th")
            .append(thClass)
            .append(width)    
            .append(params)
            .append("> ")
            .append(name)
            .append(" </th>")
            .append("\n");
        return this;
    }
    /**
     * 
     * @param texts
     * @return 
     */
    public Table td(String[] texts){
        for(String text: texts){
            td(text, 0, "");
        }
        return this;
    }
    /**
     * 
     * @param text
     * @return 
     */
    public Table td(String text){
        return td(text, 0, "");
    }
    /**
     * 
     * @param text
     * @param colspan
     * @return 
     */
    public Table td(String text, int colspan){
        return td(text, colspan, "");
    }
    /**
     * 
     * @param text
     * @param params
     * @return 
     */
    public Table td(String text, String params){
        return td(text, 0, params);
    }
    /**
     * 
     * @param text
     * @param colspan
     * @param params
     * @return 
     */
    public Table td(String text, int colspan, String params){
        String cssClass = tdClass;
        //
        String colSpanStr = "";
        if(colspan > 1){
            colSpanStr = "colspan="+colspan+" ";
        }
        body.append("\t").append("<td").append(cssClass).append(colSpanStr).append(params).append("> ");
        body.append(text);
        body.append(" </td>").append("\n");
        return this;
    }
    /**
     * 
     * @return 
     */
    public String content() {
        String heading = "";
        if(title != null){
            heading += "<h3>"+title+"</h3>";
        }
        return 
                heading +
                start + body.toString() + end;
    }
}