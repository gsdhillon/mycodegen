package lib.json;

import lib.TabManager;
import java.util.ArrayList;
import java.util.List;
import static lib.json.Json.comma;
import static lib.json.Json.newline;

/**
 *
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class JsonArray implements JsonAttribute{
    public List<JsonAttributeLabeled> attributes;
    public JsonArray(){
        attributes = new ArrayList<>();
    }
    public JsonArray add(JsonAttributeLabeled jo){
        jo.setTM(tmNested);
        attributes.add(jo);
        return this;
    }
    @Override
    public String getText(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String delim = "";
        for(JsonAttributeLabeled ja : attributes){
//            ja.setLabeled(false);
            sb.append(delim).append(newline).append(ja.getText());
            delim = comma;
//            ja.setLabeled(true);
        }
        sb.append(newline).append(tm.tabs()).append("]");
        return sb.toString();
    }
    
    //TabManager
    private TabManager tm = new TabManager(0);
    private TabManager tmNested = tm.nested();
    @Override
    public JsonAttribute setTM(TabManager tm) {
        this.tm = tm;
        tmNested = tm.nested();
        for(JsonAttribute ja : attributes){
            ja.setTM(tmNested);
        }
        return this;
    }
}