package lib.html;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GurmeetSingh, gsdhillon@gmail.com
 */
public class Html {
    private final String start =
    "<!DOCTYPE html>"+
    "<html>\n"+
        "<head>\n"+
            "<style type='text/css'>\n"+
                "\th1, h2, h3, th, td { font-family:sans-serif }\n"+
                "\th1, h2, h3, th { font-weight:bold }\n"+
                "\ttd, th { padding-left: 5px }\n"+
                "\th1 { font-size: 20 }\n"+
                "\th2 { font-size: 18 }\n"+
                "\th3 { font-size: 16 }\n"+
                "\tth { font-size: 15 }\n"+
                "\ttd { font-size: 15 }\n"+
                "\t.b2 { border: 1px solid black }\n"+ 
                "\t.b1 { border: 1px solid black }\n"+
                "\t.b0 { border: none align:'left'}\n"+
            "</style>\n"+
        "</head>\n"
      + "<body>\n";
    private final List<Table> tableList;
    private final String end = 
       "\n</body>"+
    "\n</html>";

    public Html() {
        tableList = new ArrayList<>();
    }
    
    public Html add(Table table){
        tableList.add(table);
        return this;
    }

    public String htmlContent() {
        StringBuilder sb = new StringBuilder();
        for(Table table : tableList){
            sb.append(table.content()).append("\n");
        }
        return 
        start + 
        sb.toString() + 
        end;
    }
}
