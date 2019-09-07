package mycodegen.utils;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class Delim {
    private final String delim;
    private boolean isFirst=true;
    public Delim(String delim) {
        this.delim = delim;
    }
    @Override
    public String toString(){
        if(isFirst){isFirst = false; return "";}return delim;
    }

    public void print(Object text) {
        System.out.print(this+text.toString());
    }
}