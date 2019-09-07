package lib.xml;

import lib.TabManager;

/**
 * #################################################################
 * #################   -- IMPORTANT NOTE --      ###################
 * #####      DO not make any change in this interface        ######
 * ###   it is important for to make digital signature verify    ###
 * #################################################################
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public interface XmlTag{
    public String getText();
    public XmlTag setTM(TabManager tm);
}
