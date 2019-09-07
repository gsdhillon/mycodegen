package mycodegen.generator.java.server;

import application.Author;
import application.DBNode;
import application.Package;
import mycodegen.generator.java.ClassGeneratorFacade;
import mycodegen.generator.java.CodeWriter;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 * @createdOn 
 */
@SuppressWarnings("StringConcatenationInsideStringBufferAppend")
public class ClassGeneratorServer extends ClassGeneratorFacade{
    /**
     * 
     * @param dbNode
     * @param package1
     * @param author
//     * @param applicationEntry
     * @throws Exception 
     */
    public ClassGeneratorServer(
            DBNode dbNode, 
            Package package1, 
            Author author 
//            ApplicationEntry applicationEntry
    ) throws Exception {
        super(
                dbNode, 
                package1, 
                author 
//                applicationEntry
        );
    }

    @Override
    protected void getApplicationFieldsDeclaration(CodeWriter cw) throws Exception{
    }
    
    @Override
    protected String getPackageName(){
        return package1.serverPackageName;
    }
    
    @Override
    protected String getClassName(){
        return dbNode.getDaoName();
    }
    
    @Override
    protected void makeClassDeclaration(StringBuilder classDeclarationSb) throws Exception{
        //Master Class
        classDeclarationSb.append("public class ").append(getClassName()).append(" {\n");
    }
    
    @Override
    protected void makeMasterDataFields(CodeWriter cw) throws Exception{
    }
    
    @Override
    protected void makeDetailsFields(CodeWriter cw) throws Exception{
    }

    @Override
    protected void makeMethods(CodeWriter cw) throws Exception{
        addImport("java.sql.Connection");
        addImport("java.sql.PreparedStatement");
        addImport("java.sql.ResultSet");
        addImport("java.util.List");
        addImport("java.util.ArrayList");
        addImport(package1.clientPackageName+".*");

        //
        DaoMaker dao = new DaoMaker(package1, dbNode);
        //
        new DaoMakerCreate(dao).makeCreateMethod(cw);
        new DaoMakerUpdate(dao).makeUpdateMethod(cw);
        new DaoMakerGet(dao).makeGetMethod(cw);
        new DaoMakerGetAll(dao).makeGetAllMethod(cw);
        //
        dao.close();
    }
}