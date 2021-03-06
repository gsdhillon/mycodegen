package lib;

import generated_code.client.ProRecord;
import generated_code.server.ProPermitController;
import java.io.File;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import mycodegen.utils.Util;
//import javax.swing.JTextPane;

public class HtmlPane{
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"}) 
    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame();
            frame.setSize(850, 700);
            frame.setLocationRelativeTo(null);
//        JTextPane pane = new JTextPane();
            JEditorPane pane = new JEditorPane();
            pane.setContentType("text/html");
         //   pane.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(pane);
            frame.getContentPane().add(scrollPane);
            //
            String doc = getHtml();
            File f = new File("D:/MyCodeGen/test.html");
            Util.writeToFile(f, doc);
          //  System.out.println(doc);
            pane.setText(doc);
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getHtml() {
        ProRecord proRecord = ProPermitController.getForm(1);
        return proRecord.getHtml();
    }
}

