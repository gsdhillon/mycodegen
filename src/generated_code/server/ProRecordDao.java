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
public class ProRecordDao {
    //Data fields
    public static void create(Connection conn, ProRecord entity, String logHeader) throws Exception{
        String query = "";
        try {
            //insert query
            query =
            "insert into PKI_PRO_RECORD("
                //field list
                + "FORM_ID "
                + ",APPLY_DATE "
                + ",DATE_OF_VISIT "
                + ",EXPECTED_TIME "
                + ",PURPOSE "
            + ") values ("
                //param list
                + "? " // FORM_ID
                + ",STR_TO_DATE(?, '%m/%d/%Y') " // APPLY_DATE
                + ",STR_TO_DATE(?, '%m/%d/%Y') " // DATE_OF_VISIT
                + ",? " // EXPECTED_TIME
                + ",? " // PURPOSE
            + ")";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setLong((++paramCount), entity.formId);
                ps.setString((++paramCount), entity.applyDate);
                ps.setString((++paramCount), entity.dateOfVisit);
                ps.setString((++paramCount), entity.expectedTime);
                ps.setString((++paramCount), entity.purpose);
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
    public static void update(Connection conn, ProRecord entity, String logHeader) throws Exception{
        String query = "";
        try {
            //update query
            query =
            "update PKI_PRO_RECORD set "
                //update field list
                + "APPLY_DATE = STR_TO_DATE(?, '%m/%d/%Y') "
                + ",DATE_OF_VISIT = STR_TO_DATE(?, '%m/%d/%Y') "
                + ",EXPECTED_TIME = ? "
                + ",PURPOSE = ? "
            + "where "
                //primary key field list
                + "FORM_ID = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setString((++paramCount), entity.applyDate);
                ps.setString((++paramCount), entity.dateOfVisit);
                ps.setString((++paramCount), entity.expectedTime);
                ps.setString((++paramCount), entity.purpose);
                ps.setLong((++paramCount), entity.formId);
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
    public static void get(Connection conn, ProRecord entity, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "DATE_FORMAT(APPLY_DATE, '%m/%d/%Y') APPLY_DATE "
                + ",DATE_FORMAT(DATE_OF_VISIT, '%m/%d/%Y') DATE_OF_VISIT "
                + ",EXPECTED_TIME "
                + ",PURPOSE "
            + "from "
                + "PKI_PRO_RECORD "
            + "where "
                //primary key field list
                + "FORM_ID = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setLong((++paramCount), entity.formId);
                try( ResultSet rs = ps.executeQuery() ) {
                    if ( !rs.next()) {
                        throw new Exception("Select "+logHeader+" failed no record found!");
                    }
                    //get field list
                    entity.applyDate = rs.getString("APPLY_DATE");
                    entity.dateOfVisit = rs.getString("DATE_OF_VISIT");
                    entity.expectedTime = rs.getString("EXPECTED_TIME");
                    entity.purpose = rs.getString("PURPOSE");
                }
            }
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
    @SuppressWarnings("UseSpecificCatch")
    public static List<ProRecord> getAll(Connection conn, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "FORM_ID "
                + ",DATE_FORMAT(APPLY_DATE, '%m/%d/%Y') APPLY_DATE "
                + ",DATE_FORMAT(DATE_OF_VISIT, '%m/%d/%Y') DATE_OF_VISIT "
                + ",EXPECTED_TIME "
                + ",PURPOSE "
            + "from "
                + "PKI_PRO_RECORD "
            + ";";
            //prepareStatement
            List<ProRecord> proRecordList = new ArrayList<>();
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                try( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next()) {
                        ProRecord proRecord = new ProRecord();
                        //get field list
                        proRecord.formId = rs.getLong("FORM_ID");
                        proRecord.applyDate = rs.getString("APPLY_DATE");
                        proRecord.dateOfVisit = rs.getString("DATE_OF_VISIT");
                        proRecord.expectedTime = rs.getString("EXPECTED_TIME");
                        proRecord.purpose = rs.getString("PURPOSE");
                        proRecordList.add(proRecord);
                    }
                }
            }
            return proRecordList;
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
}