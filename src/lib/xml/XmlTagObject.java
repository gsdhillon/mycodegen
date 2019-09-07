package lib.xml;

import lib.TabManager;
import static lib.xml.Xml.GT;
import static lib.xml.Xml.LT;
import static lib.xml.Xml.LTFS;
import static lib.xml.Xml.newline;

/**
 * #################################################################
 * #################   -- IMPORTANT NOTE --      ###################
 * #####         DO not make any change in this class         ######
 * ###   it is important for to make digital signature verify    ###
 * #################################################################
 *
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class XmlTagObject implements XmlTag{
    public String label;
    public XmlObject value;
    /**
     * 
     * @param label
     * @param value 
     */
    public XmlTagObject(String label, XmlObject value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String getText(){
        return 
        tm.tabs()+LT+label+GT+
            value.getText()+
        newline+tm.tabs()+LTFS+label+GT;
    }

    //TabManager
    private TabManager tm = new TabManager(0);

    /**
     *
     * @param tm
     * @return
     */
    @Override
    public XmlTag setTM(TabManager tm) {
        this.tm = tm;
        value.setTM(tm);
        return this;
    }
}