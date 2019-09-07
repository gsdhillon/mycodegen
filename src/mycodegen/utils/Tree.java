package mycodegen.utils;

import java.util.ArrayList;
import java.util.List;
import mycodegen.generator.java.DBTable;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 * @param <T>
 */
public class Tree<T> {
    public T masterTable = null;
    public final List<Tree<T>> detailTables = new ArrayList<>();
    public Tree<T> parent = null;

    /**
     *
     * @param masterTable
     */
    public Tree(T masterTable) {
        this.masterTable = masterTable;
    }

    /**
     *
     * @param child
     * @return
     */
    public Tree<T> addChild(Tree<T> child) {
        child.setParent(this);
        this.detailTables.add(child);
        return child;
    }

    /**
     *
     * @param children
     */
    public void addChildren(List<Tree<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.detailTables.addAll(children);
    }

    /**
     *
     * @return
     */
    public List<Tree<T>> getChildren() {
        return detailTables;
    }

    /**
     *
     * @return
     */
    public T getData() {
        return masterTable;
    }

    /**
     *
     * @param data
     */
    public void setData(T data) {
        this.masterTable = data;
    }

    /**
     *
     * @param parent
     */
    private void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    /**
     *
     * @return
     */
    public Tree<T> getParent() {
        return parent;
    }

//    @SuppressWarnings("CallToPrintStackTrace")
//    public static void main(String[] args) {
//        try{
//            //pki_pro_record
//            DBTable proRecord = new DBTable("pki_pro_record", true);
//            Tree<DBTable> proRecordTree = new Tree<>(proRecord);
//            
//            //pki_applicant
//            DBTable proApplicant = new DBTable("pki_applicant", true);
//            Tree<DBTable> proApplicantTree = new Tree<>(proApplicant);
//            proRecordTree.addChild(proApplicantTree);
//            
//            //pki_pro_visitor
//            DBTable proVisitor = new DBTable("pki_pro_visitor", false);
//            Tree<DBTable> proVisitorTree = new Tree<>(proVisitor);
//            proRecordTree.addChild(proVisitorTree);
//            
//            //pki_pro_visitor_address
//            DBTable proVisitorAddress = new DBTable("pki_pro_visitor_address", false);
//            Tree<DBTable> proVisitorAdressTree = new Tree<>(proVisitorAddress);
//            proVisitorTree.addChild(proVisitorAdressTree);
//   
//            //
//            Tree.breadthFirstTraversal(proRecordTree);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    
    
    public static void breadthFirstTraversal(Tree<DBTable> tableTree){
        Tree<DBTable> parentNode = tableTree.getParent();
        String parentTable = ((parentNode==null)?"null":parentNode.getData().getTableName());
        System.out.println("table: "+tableTree.getData().getTableName()+", parent:"+parentTable);
        
        tableTree.getChildren();
        for(Tree<DBTable> child: tableTree.getChildren()){
            breadthFirstTraversal(child);
        }
    }
}