package lib.xml;

import java.util.ArrayList;
import java.util.List;
import lib.TabManager;

/**
 * #################################################################
 * #################   -- IMPORTANT NOTE --      ###################
 * #####         DO not make any change in this class         ######
 * ###   it is important for to make digital signature verify    ###
 * #################################################################
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class Xml {
    public static final String LT = "<";
    public static final String LTFS = "</";
    public static final String GT = ">";
    public static final String newline = "\n";
    //
    private final List<XmlTag> tagList;
    private final TabManager tm = new TabManager(1);
    /**
     * 
     */
    public Xml(){ 
        this.tagList = new ArrayList<>();
    }

    public void tabIn(){
        tm.tabIn();
    }
    
    public void tabOut(){
        tm.tabOut();
    }
    
    public Xml addTag(XmlTag tag){
        tag.setTM(tm);
        tagList.add(tag);
        return this;
    }
    
    public String getText(){
        StringBuilder sb = new StringBuilder("<XML>");
        for(XmlTag tag : tagList){
            sb.append(newline).append(tag.getText());
        }
        sb.append(newline).append("</XML>");
        return sb.toString();
    }
}