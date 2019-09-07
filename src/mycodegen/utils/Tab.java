package mycodegen.utils;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class Tab {
    private int tabCount;
    private final String tab = "    ";
    public Tab(int numTabs){
         tabCount = numTabs;
    }
    public Tab inOut(int numTabs){
        tabCount += numTabs;
        return this;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<tabCount;i++){
            sb.append(tab);
        }
        return sb.toString();
    }
}