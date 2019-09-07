package lib.xml;

import lib.TabManager;
import static lib.xml.Xml.GT;
import static lib.xml.Xml.LT;
import static lib.xml.Xml.LTFS;

/**
 * #################################################################
 * #################   -- IMPORTANT NOTE --      ###################
 * #####         DO not make any change in this class         ######
 * ###   it is important for to make digital signature verify    ###
 * #################################################################
 * 
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class XmlTagBasic implements XmlTag{
    public String label;
    public String value;
    /**
     * 
     * @param label
     * @param value 
     */
    public XmlTagBasic(String label, String value) {
        this.label = label;
        this.value = value;
    }
    @Override
    public String getText(){
        return tm.tabs()+LT+label+GT+value+LTFS+label+GT;
    }
    
    //TabManager
    private TabManager tm = new TabManager(0);
    @Override
    public XmlTag setTM(TabManager tm) {
        this.tm = tm;
        return this;
    }
}