package lib.xml;

import lib.TabManager;
import java.util.ArrayList;
import java.util.List;
//import static lib.xml.Xml.comma;
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
public class XmlObject implements XmlTag{
    public List<XmlTag> tagList;
    public XmlObject(){
        tagList = new ArrayList<>();
    }
    public XmlObject add(XmlTag tag){
        tag.setTM(tmNested);
        tagList.add(tag);
        return this;
    }
    @Override
    public String getText(){
        StringBuilder sb = new StringBuilder();
        for(XmlTag tag : tagList){
            sb.append(newline).append(tag.getText());
        }
        return sb.toString();
    }
    
    //TabManager
    private TabManager tm = new TabManager(0);
    private TabManager tmNested = tm.nested();
    @Override
    public XmlTag setTM(TabManager tm) {
        this.tm = tm;
        tmNested = tm.nested();
        for(XmlTag tag : tagList){
            tag.setTM(tmNested);
        }
        return this;
    }
}