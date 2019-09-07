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
public class JsonObject implements JsonAttribute{
    public List<JsonAttributeLabeled> attributes;
    public JsonObject(){
        attributes = new ArrayList<>();
    }
    public JsonObject add(JsonAttributeLabeled ja){
        ja.setTM(tmNested);
        attributes.add(ja);
        return this;
    }
    @Override
    public String getText(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        String delim = "";
        for(JsonAttribute ja : attributes){
            sb.append(delim).append(newline).append(ja.getText());
            delim = comma;
        }
        sb.append(newline).append(tm.tabs()).append("}");
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