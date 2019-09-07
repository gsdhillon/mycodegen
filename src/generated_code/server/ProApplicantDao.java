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
public class ProApplicantDao {
    //Data fields
    public static void create(Connection conn, ProApplicant entity, String logHeader) throws Exception{
        String query = "";
        try {
            //insert query
            query =
            "insert into PKI_PRO_APPLICANT("
                //field list
                + "FORM_ID "
                + ",EMP_NO "
                + ",NAME "
                + ",DIVISISION_NO "
                + ",DIVISION_FULL_NAME "
                + ",DESIG_SHORT_NAME "
            + ") values ("
                //param list
                + "? " // FORM_ID
                + ",? " // EMP_NO
                + ",? " // NAME
                + ",? " // DIVISISION_NO
                + ",? " // DIVISION_FULL_NAME
                + ",? " // DESIG_SHORT_NAME
            + ")";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setInt((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.empNo);
                ps.setString((++paramCount), entity.name);
                ps.setString((++paramCount), entity.divisisionNo);
                ps.setString((++paramCount), entity.divisionFullName);
                ps.setString((++paramCount), entity.desigShortName);
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
    public static void update(Connection conn, ProApplicant entity, String logHeader) throws Exception{
        String query = "";
        try {
            //update query
            query =
            "update PKI_PRO_APPLICANT set "
                //update field list
                + "NAME = ? "
                + ",DIVISISION_NO = ? "
                + ",DIVISION_FULL_NAME = ? "
                + ",DESIG_SHORT_NAME = ? "
            + "where "
                //primary key field list
                + "FORM_ID = ? "
                + "AND EMP_NO = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setString((++paramCount), entity.name);
                ps.setString((++paramCount), entity.divisisionNo);
                ps.setString((++paramCount), entity.divisionFullName);
                ps.setString((++paramCount), entity.desigShortName);
                ps.setInt((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.empNo);
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
    public static void get(Connection conn, ProApplicant entity, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "NAME "
                + ",DIVISISION_NO "
                + ",DIVISION_FULL_NAME "
                + ",DESIG_SHORT_NAME "
            + "from "
                + "PKI_PRO_APPLICANT "
            + "where "
                //primary key field list
                + "FORM_ID = ? "
                + "AND EMP_NO = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setInt((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.empNo);
                try( ResultSet rs = ps.executeQuery() ) {
                    if ( !rs.next()) {
                        throw new Exception("Select "+logHeader+" failed no record found!");
                    }
                    //get field list
                    entity.name = rs.getString("NAME");
                    entity.divisisionNo = rs.getString("DIVISISION_NO");
                    entity.divisionFullName = rs.getString("DIVISION_FULL_NAME");
                    entity.desigShortName = rs.getString("DESIG_SHORT_NAME");
                }
            }
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
    @SuppressWarnings("UseSpecificCatch")
    public static List<ProApplicant> getAll(Connection conn, ProRecord entity, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "FORM_ID "
                + ",EMP_NO "
                + ",NAME "
                + ",DIVISISION_NO "
                + ",DIVISION_FULL_NAME "
                + ",DESIG_SHORT_NAME "
            + "from "
                + "PKI_PRO_APPLICANT "
            + "where "
                // parent primary key field list
                + "FORM_ID = ? "
            + ";";
            //prepareStatement
            List<ProApplicant> proApplicantList = new ArrayList<>();
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                //set params
                int paramCount = 0;
                ps.setLong((++paramCount), entity.formId);
                try( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next()) {
                        ProApplicant proApplicant = new ProApplicant();
                        //get field list
                        proApplicant.formId = rs.getInt("FORM_ID");
                        proApplicant.empNo = rs.getInt("EMP_NO");
                        proApplicant.name = rs.getString("NAME");
                        proApplicant.divisisionNo = rs.getString("DIVISISION_NO");
                        proApplicant.divisionFullName = rs.getString("DIVISION_FULL_NAME");
                        proApplicant.desigShortName = rs.getString("DESIG_SHORT_NAME");
                        proApplicantList.add(proApplicant);
                    }
                }
            }
            return proApplicantList;
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
}