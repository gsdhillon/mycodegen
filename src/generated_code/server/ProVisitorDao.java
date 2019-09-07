package generated_code.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import generated_code.client.*;

/**
 * 
 * @author - Gurmeet Singh, gsdhillon@gmail.com
 * @createdOn - 03/09/2019 21:43:33
 */
public class ProVisitorDao {
    //Data fields
    public static void create(Connection conn, ProVisitor entity, String logHeader) throws Exception{
        String query = "";
        try {
            //insert query
            query =
            "insert into PKI_PRO_VISITOR("
                //field list
                + "FORM_ID "
                + ",S_NO "
                + ",NAME "
                + ",PHONE_NO "
                + ",ORG "
                + ",DESIG "
                + ",DOB "
            + ") values ("
                //param list
                + "? " // FORM_ID
                + ",? " // S_NO
                + ",? " // NAME
                + ",? " // PHONE_NO
                + ",? " // ORG
                + ",? " // DESIG
                + ",STR_TO_DATE(?, '%m/%d/%Y') " // DOB
            + ")";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setLong((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.sNo);
                ps.setString((++paramCount), entity.name);
                ps.setString((++paramCount), entity.phoneNo);
                ps.setString((++paramCount), entity.org);
                ps.setString((++paramCount), entity.desig);
                ps.setString((++paramCount), entity.dob);
                int updateCount = ps.executeUpdate();
                if (updateCount != 1) {
                    throw new Exception("Create "+logHeader+" failed updateCount = "+updateCount+"!");
                }
            }
        } catch(Exception e) {
            System.out.println("Insert "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
    public static void update(Connection conn, ProVisitor entity, String logHeader) throws Exception{
        String query = "";
        try {
            //update query
            query =
            "update PKI_PRO_VISITOR set "
                //update field list
                + "NAME = ? "
                + ",PHONE_NO = ? "
                + ",ORG = ? "
                + ",DESIG = ? "
                + ",DOB = STR_TO_DATE(?, '%m/%d/%Y') "
            + "where "
                //primary key field list
                + "FORM_ID = ? "
                + "AND S_NO = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setString((++paramCount), entity.name);
                ps.setString((++paramCount), entity.phoneNo);
                ps.setString((++paramCount), entity.org);
                ps.setString((++paramCount), entity.desig);
                ps.setString((++paramCount), entity.dob);
                ps.setLong((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.sNo);
                int updateCount = ps.executeUpdate();
                if (updateCount != 1) {
                    throw new Exception("Update "+logHeader+" failed updateCount = "+updateCount+"!");
                }
            }
        } catch(Exception e) {
            System.out.println("Update "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
    public static void get(Connection conn, ProVisitor entity, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "NAME "
                + ",PHONE_NO "
                + ",ORG "
                + ",DESIG "
                + ",DATE_FORMAT(DOB, '%m/%d/%Y') DOB "
            + "from "
                + "PKI_PRO_VISITOR "
            + "where "
                //primary key field list
                + "FORM_ID = ? "
                + "AND S_NO = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setLong((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.sNo);
                try( ResultSet rs = ps.executeQuery() ) {
                    if ( !rs.next()) {
                        throw new Exception("Select "+logHeader+" failed no record found!");
                    }
                    //get field list
                    entity.name = rs.getString("NAME");
                    entity.phoneNo = rs.getString("PHONE_NO");
                    entity.org = rs.getString("ORG");
                    entity.desig = rs.getString("DESIG");
                    entity.dob = rs.getString("DOB");
                }
            }
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
    @SuppressWarnings("UseSpecificCatch")
    public static List<ProVisitor> getAll(Connection conn, ProRecord entity, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "FORM_ID "
                + ",S_NO "
                + ",NAME "
                + ",PHONE_NO "
                + ",ORG "
                + ",DESIG "
                + ",DATE_FORMAT(DOB, '%m/%d/%Y') DOB "
            + "from "
                + "PKI_PRO_VISITOR "
            + "where "
                // parent primary key field list
                + "FORM_ID = ? "
            + ";";
            //prepareStatement
            List<ProVisitor> proVisitorList = new ArrayList<>();
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                //set params
                int paramCount = 0;
                ps.setLong((++paramCount), entity.formId);
                try( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next()) {
                        ProVisitor proVisitor = new ProVisitor();
                        //get field list
                        proVisitor.formId = rs.getLong("FORM_ID");
                        proVisitor.sNo = rs.getInt("S_NO");
                        proVisitor.name = rs.getString("NAME");
                        proVisitor.phoneNo = rs.getString("PHONE_NO");
                        proVisitor.org = rs.getString("ORG");
                        proVisitor.desig = rs.getString("DESIG");
                        proVisitor.dob = rs.getString("DOB");
                        proVisitorList.add(proVisitor);
                    }
                }
            }
            return proVisitorList;
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
}