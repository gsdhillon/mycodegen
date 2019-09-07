package lib.json;

import lib.TabManager;
import static lib.json.Json.colonSep;
import static lib.json.Json.dq;

/**
 *
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class JsonBasicAttribute implements JsonAttributeLabeled{
    public String label;
    public String value;
   
    public JsonBasicAttribute(String label, String value) {
        this.label = label;
        this.value = value;
    }
    
    
    @Override
    public String getText(){
        return tm.tabs()+getLabel()+dq+value+dq;
    }

    //TabManager
    private TabManager tm = new TabManager(0);
    @Override
    public JsonAttribute setTM(TabManager tm) {
        this.tm = tm;
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