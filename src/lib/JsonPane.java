package lib;

import generated_code.client.ProRecord;
import generated_code.client.ProVisitor;
import java.io.File; 
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import mycodegen.utils.Util;
//import javax.swing.JTextPane;

public class JsonPane{
    @SuppressWarnings({"UseSpecificCatch", "CallToPrintStackTrace"}) 
    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame();
            frame.setSize(850, 700);
            frame.setLocationRelativeTo(null);
//        JTextPane pane = new JTextPane();
            JEditorPane pane = new JEditorPane();
            pane.setContentType("text/json");
         //   pane.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(pane);
            frame.getContentPane().add(scrollPane);
            //
            String doc = getJson();
            File f = new File("D:/MyCodeGen/test.json");
            Util.writeToFile(f, doc);
          //  System.out.println(doc);
            pane.setText(doc);
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getJson() {
        ProRecord proRecord = new ProRecord();
        proRecord.proVisitorList.add(new ProVisitor());
        proRecord.proVisitorList.add(new ProVisitor());
        return proRecord.getJson();
    }
}