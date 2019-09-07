package generated_code.server;

import generated_code.client.ProRecord;
import generated_code.client.ProVisitor;
import mycodegen.jdbc.DB;
import mycodegen.jdbc.DBFactory;

/**
 *
 * @author Gurmeet Singh, gsdhillon@gmail.com
 */
@SuppressWarnings("CallToPrintStackTrace")
public class ProPermitController {
    
    public static void submitForm(ProRecord proRecord){
        DB db = DBFactory.createDB();
        try{
            db.connect().setAutoCommit(false);
            ProRecordDao.create(db.connection, proRecord, "SubmitProForm");
            ProApplicantDao.create(db.connection, proRecord.proApplicant, "SubmitProForm");
            for(ProVisitor proVisitor : proRecord.proVisitorList){
                ProVisitorDao.create(db.connection, proVisitor, "SubmitProForm");
            }
            db.commit();
        }catch(Exception e){
            db.rollback();
            e.printStackTrace();
        }finally{
            db.close();
        }
    }
    public static ProRecord getForm(long formId){
        DB db = DBFactory.createDB();
        try{
            db.connect();
            ProRecord proRecord = new ProRecord();
            proRecord.formId = formId;
            ProRecordDao.get(db.connection, proRecord, "GetProForm");
            ProApplicantDao.get(db.connection, proRecord.proApplicant, "GetProForm");
            proRecord.proVisitorList = ProVisitorDao.getAll(db.connection, proRecord, "GetProForm");
            return proRecord;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            db.close();
        }
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        try{
            ProRecord proRecord = ProPermitController.getForm(1);
            proRecord.formId = 2;
            proRecord.proApplicant.formId = 2;
            for(ProVisitor proVisitor : proRecord.proVisitorList){
                proVisitor.formId = proRecord.formId;
            }
            ProPermitController.submitForm(proRecord);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
