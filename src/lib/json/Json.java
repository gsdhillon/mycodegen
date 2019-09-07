package lib.json;

import java.util.ArrayList;
import java.util.List;
import lib.TabManager;

/**
 *
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class Json {
    public static final String space = String.valueOf(' ');
    public static final String dq = "\"";
    public static final String colonSep = space+":"+space;
    public static final String newline = "\n";
    public static final String comma = ",";
    //
    private final List<JsonAttribute> attributeList;
    public Json(){ 
        this.attributeList = new ArrayList<>();
    }

    private final TabManager tm = new TabManager(1);
    public void tabIn(){
        tm.tabIn();
    }
    public void tabOut(){
        tm.tabOut();
    }
    
    public Json add(JsonAttribute ja){
        ja.setTM(tm);
        attributeList.add(ja);
        return this;
    }
    
    public String getText(){
        StringBuilder sb = new StringBuilder("{");
        String delim = "";
        for(JsonAttribute ja : attributeList){
            sb.append(delim).append(newline).append(ja.getText());
            delim = comma;
        }
        sb.append(newline).append("}");
        return sb.toString();
    }
    
    
}