package lib;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class TabManager {
    public static final String space = String.valueOf(' ');
    public static final int numTabChars = 4;
    public static final String tab = "\t";
    static {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < numTabChars; i++) {
//            s.append(space);
//        }
//        tab = s.toString();
    }
    
    //
    private int tabCount;
    public TabManager(int tabCount) {
        this.tabCount = tabCount;
    }
    public TabManager nested() {
        return new TabManager(tabCount+1);
    }
    public void tabIn(){
        tabCount++;
    }
    public void tabOut(){
        tabCount--;
    }
    
    
    //
    public final String tabs() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tabCount; i++) {
            s.append(tab);
        }
        return s.toString();
    }
}