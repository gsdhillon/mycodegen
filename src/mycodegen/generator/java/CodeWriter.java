package mycodegen.generator.java;

import mycodegen.utils.Tab;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
public class CodeWriter{
    private final StringBuilder sb;
    private final Tab tab;
    private final String newLine = "\n";

    public CodeWriter(int tabCount) {
        this.sb = new StringBuilder();
        this.tab = new Tab(tabCount);
    }

    public CodeWriter tabIn(){
        tab.inOut(1);
        return this;
    }

    public CodeWriter tabOut(){
        tab.inOut(-1);
        return this;
    }
    
    public CodeWriter methodDec(String qualifier, String returnType, String name, String params, boolean thEx) {
        return methodDec(qualifier, returnType, name, params, "", thEx);
    }

    /**
     * 
     * @param qualifier - public/private/protected
     * @param returnType - void/int/String ..
     * @param name - mathodName
     * @param params - "String a, int b"
     * @param extendsStr
     * @param thEx - if true it will add "throws Exception"
     * @return - reference to class object itself
     */
    public CodeWriter methodDec(String qualifier, String returnType, String name, String params, String extendsStr, boolean thEx) {
        // tab public method declaration [th ex] newline tabIn()
        String throwText = (thEx)?" throws Exception":"";
        return tab().textSp(qualifier).textSp(returnType).text(name+"("+params+")").text(extendsStr).text(throwText).text("{").ln().tabIn();
    } 
    /**
     * 
     * @param params
     * @return 
     */
    public CodeWriter tryWithResourcesBlock(String params) {
        // tab code;
        return tab().text("try( "+params+" ) {").ln().tabIn();
    } 
    /**
     * 
     * @return 
     */
    public CodeWriter tryBlock() {
        // tab code;
        return tab().text("try {").ln().tabIn();
    } 
    /**
     * 
     * @return 
     */
    public CodeWriter catchBlock() {
        // tab code;
        return tabOut().tab().text("} catch(Exception e) {").ln().tabIn();
    } 
    /**
     * 
     * @return 
     */
    public CodeWriter closeBlock() {
        // tab code;
        return tabOut().tab().text("}").ln();
    } 
    
    /**
     * 
     * @param qualifier
     * @param type
     * @param varName
     * @param value
     * @param comment
     * @return 
     */
    public CodeWriter varDec(String qualifier, String type, String varName, String value, String comment) {
        String valStr = (value==null || value.length()==0)?"":" = "+value;
        // tab  Type varName = value; newline
        if(qualifier != null && qualifier.trim().length() > 0){
            qualifier = qualifier+" ";
        }
        return tab().text(qualifier).textSp(type).text(varName).text(valStr+";").comment(comment).ln();
    } 
    
    /**
     * 
     * @param className
     * @param varName
     * @param params
     * @return 
     */
    public CodeWriter classVarDec(String className, String varName, String params) {
        // tab  className varName = new className(params); newline
        return tab().textSp(className).textSp(varName).textSp("= new").text(className+"("+params+");").ln();
    } 
    
    /**
     * 
     * @param qualifier - public/private/protected
     * @param type - classType
     * @param name - className
     * @return - reference to class object itself
     */
    public CodeWriter classDec(String qualifier, String type, String name) {
        // tab public method declaration 
        return tab().textSp(qualifier).textSp(type).text(name).text("{").ln().tabIn();
    } 


    /**
     * 
     * @param type - example: String
     * @param var - example: name
     * @param listVar - example: nameList
     * @return - reference to class object itself
     */
    public CodeWriter lfe(String type, String var, String listVar) {
        // this.isStreem=isStreem;
        // tab list for each
        return tab().text("for(").textSp(type).textSp(var).textSp(":").text(listVar).text("){").ln();
            
    } 
    /**
     * append tab code
     * @param code
     * @return 
     */
    public CodeWriter c(String code) {
        // tab code
        return tab().text(code);
    } 
    /**
     * append tab code;
     * @param code
     * @return 
     */
    public CodeWriter cs(String code) {
        // tab code;
        return tab().text(code).text(";");
    } 
    /**
     * 
     * @return 
     */
    public CodeWriter n() {
        // newline
        return ln();
    } 
    /**
     * append tab code;
     * @param code
     * @return 
     */
    public CodeWriter cn(String code) {
        // tab code; newline
        return tab().text(code).ln();
    } 
    
    /**
     * append tab code; newline
     * @param code
     * @return 
     */
    public CodeWriter csn(String code) {
        // tab code; newline
        return tab().text(code).text(";").ln();
    } 
    /**
     * append tab code; comment newline
     * @param code
     * @param comment
     * @return 
     */
    public CodeWriter cscn(String code,  String comment) {
        // tab code; /* comment */ newline
        return tab().text(code).text(";").comment(comment).ln();
    } 
    @Override
    public String toString() {
            return sb. toString() ;
    } 
    
    private CodeWriter comment(String comment){
        if(comment != null && comment.length()>0){
            sb.append("\t\t/* ").append(comment).append(" */");
        }
        return this;
    }
    private CodeWriter text(String text){
        sb.append(text);
        return this;
    }
    private CodeWriter textSp(String text){
        sb.append(text).append(" ");
        return this;
    }
    private CodeWriter tab(){
        sb.append(tab);
        return this;
    }
    private CodeWriter ln(){
        sb.append(newLine);
        return this;
    }
} 