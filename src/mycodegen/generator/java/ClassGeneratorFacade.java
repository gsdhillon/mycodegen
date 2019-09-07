package mycodegen.generator.java;

import mycodegen.utils.Util;
import java.io.File;
import application.Author;
import application.DBNode;
import application.Package;

/**
 * 
 * @author Gurmeet Singh, gsdhillon@gmail.com
 * @createdOn 
 */
public abstract class ClassGeneratorFacade {
    private static final boolean addLicAndDisclaimer = false;
    private static final boolean addSuggestions = false;
    //
    protected final DBNode dbNode;
    protected final Package package1;
    protected final Author author;
//    protected final ApplicationEntry applicationEntry;
    //
    protected final StringBuilder packageStringBuilder = new StringBuilder();
    protected final StringBuilder importStringBuilder = new StringBuilder();
    protected final StringBuilder classDeclarationStringBuilder = new StringBuilder();
    //
    protected final CodeWriter classAndAuthorInfoSb = new CodeWriter(0);
    protected final CodeWriter fieldsWriter = new CodeWriter(1);
    protected final CodeWriter methodsWriter = new CodeWriter(1);
    //tab
  //  protected final Tab tab = new Tab();
    /**
     * 
     * @param dbNode
     * @param package1
     * @param author
//     * @param applicationEntry
     * @throws Exception 
     */
    public ClassGeneratorFacade (
            DBNode dbNode, 
            Package package1, 
            Author author 
//            ApplicationEntry applicationEntry
    ) throws Exception {
        //take parameters
//        this.dbTables = dbTables.formatNames();
        this.dbNode = dbNode;
        this.package1 = package1;
        this.author = author;
//        this.applicationEntry = applicationEntry;
    }
    /**
     * 
     * @param cw
     * @throws Exception 
     */
    protected void makeClassInfoAndAuthor(CodeWriter cw) throws Exception{
        cw.cn("");
        cw.cn("/**");
        if(addLicAndDisclaimer){
            cw
            .cn(" * About MyCodeGen Tool:")
            .cn(" * This tool is created mainly for writing code for developing work flow ")
            .cn(" * oriented, digital signature based applications on BOOST. It takes database tables ")
            .cn(" * and generates the java classes required at side as well as server side. ")
            .cn(" * In case any customization is required in the code generation, please")
            .cn(" * feel free to write me back (Gurmeet Singh, gsdhill@barc.gov.in).")
            .cn(" * ")
            .cn(" * Disclaimer:")
            .cn(" * The author of MyCodeGen Tool disclaim any liability in connection with the ")
            .cn(" * generated code from this tool. This tool is for merely helping the developers ")
            .cn(" * for writing code of repeated type in nature. Developers are free to change any ")
            .cn(" * part of the code according to their future requirements. ")
            .cn(" * ");
        }
        if(addSuggestions){
            cw
            .cn(" * Suggestion to the developers: ")
            .cn(" * It is good practice to write the change logs here with date and name of the developer.")
            .cn(" * Write few words about the changes made at here and also around the code changed.")
            .cn(" * ")
            .cn(" * Change logs:")
            .cn(" * ㋡ 1. [dd/mm/yyyy] by [name ]:  write down change log here ✎    ")
            .cn(" * ")
            .cn(" * ㋡ N. [dd/mm/yyyy] by [name ]: ");
        }
        cw
        .cn(" * ")
        .cn(" * @author - "+author)
        .cn(" * @createdOn - "+Util.getDateTimeStamp())
        .cn(" */");
   }
    /**
     * 
     * @throws Exception 
     */
    public void generatedCode() throws Exception{
        //generate individual components
        makePackageDeclaration(packageStringBuilder);
        makeClassInfoAndAuthor(classAndAuthorInfoSb);
        makeClassDeclaration(classDeclarationStringBuilder);
        makeDataFields(fieldsWriter);
//        makeMethods(tab, methodsStringBuilder);
        makeMethods(methodsWriter);
        
        //make complete class code
        String code =  
            packageStringBuilder.toString()+
            importStringBuilder.toString()+
            classAndAuthorInfoSb.toString()+
            classDeclarationStringBuilder.toString()+
            fieldsWriter.toString()+
            methodsWriter.toString()+
            "}";
        
        //write generated code into the file
        File dir = new File(package1.targetDirPath+"/"+getPackageName().replaceAll("\\.", "/"));
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir, getClassName()+".java");
        Util.writeToFile(file, code);
    }
    
    protected void makePackageDeclaration(StringBuilder packageSb) {
        packageSb.append("package ").append(getPackageName()).append(";\n\n");
    }
    
    /**
     * 
     * use packageInfo.clientPackageName or packageInfo.serverPackageName 
     * @return 
     */
    protected abstract String getPackageName();
    
    protected abstract void makeClassDeclaration(StringBuilder classDeclarationSb) throws Exception;
    
    /**
     * 
     * @return 
     */
    protected abstract String getClassName();
    
    private void makeDataFields(CodeWriter cw) throws Exception{
//        //Master Class
//        if(dbNode.app != null){
//            getApplicationFieldsDeclaration(cw);
//        }
        //comment
        cw.cn("//Data fields");
        makeMasterDataFields(cw);
        if(dbNode.haveChildren()){
            makeDetailsFields(cw);
        }
    }
    
    protected void addImport(String importLib){
        importStringBuilder.append("import ").append(importLib).append(";\n");
    }
    
    protected abstract void getApplicationFieldsDeclaration(CodeWriter fieldsSb) throws Exception;
    protected abstract void makeMasterDataFields(CodeWriter fieldsSb) throws Exception;
    protected abstract void makeDetailsFields(CodeWriter fieldsSb) throws Exception;

    /**
     * 
     * @param cw
     * @throws Exception 
     */
    protected abstract void makeMethods(CodeWriter cw) throws Exception;
//    protected abstract void makeMethods(Tab tab, StringBuilder methodsSb) throws Exception;

   
}