package application;


import mycodegen.generator.java.client.ClassGeneratorClient;
import mycodegen.generator.java.server.ClassGeneratorServer;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class Main {
    //Package
    static Package packageInfo = new Package();
    //Author
    static Author author = new Author();
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        try {
            DBNode rootNode = new DBNode();
            
            breadthFirstTraversal(rootNode);

//            //generate code 
//            for(DBTable table : dbSchema.detailTables){
//                DBSchema dbSchema2 = new DBSchema(table);
//                new ClassGeneratorClient(dbSchema2, packageInfo, author).generatedCode();//, null)
//                new ClassGeneratorServer(dbSchema2, packageInfo, author).generatedCode();//, null)
//            }
//            new ClassGeneratorClient(dbSchema, packageInfo, author).generatedCode();//, applicationEntry
//            new ClassGeneratorServer(dbSchema, packageInfo, author).generatedCode();//, applicationEntry

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void breadthFirstTraversal(DBNode dbNode) throws Exception{
        DBNode parentNode = dbNode.getParent();
        String parentTable = ((parentNode==null)?"null":parentNode.getMasterTable().getTableName());
        System.out.println("ClassGeneratorClient table: "+dbNode.getMasterTable().getTableName()+", parent:"+parentTable);
        new ClassGeneratorClient(dbNode, packageInfo, author).generatedCode();
        new ClassGeneratorServer(dbNode, packageInfo, author).generatedCode();
        
        for(DBNode child: dbNode.getDetailTables()){
            breadthFirstTraversal(child);
        }
    }
    
}
