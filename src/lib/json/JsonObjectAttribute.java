package lib.json;

import lib.TabManager;
import static lib.json.Json.colonSep;
import static lib.json.Json.dq;

/**
 *
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class JsonObjectAttribute implements JsonAttributeLabeled{
    public String label;
    public JsonObject value;

    public JsonObjectAttribute(String label, JsonObject value) {
        this.label = label;
        this.value = value;
    }
    
    @Override
    public String getText(){
        return tm.tabs()+getLabel()+value.getText();
    }

    
    //TabManager
    private TabManager tm = new TabManager(0);
    @Override
    public JsonAttribute setTM(TabManager tm) {
        this.tm = tm;
        value.setTM(tm);
        return this;
    }
    
    //Label
    private boolean labeled = true;
    @Override
    public JsonAttribute setLabeled(boolean labeled) {
        this.labeled = labeled;
        return this;
    }
    private String getLabel() {
        return labeled ? (dq+label+dq+colonSep) : "";
    }
    
}