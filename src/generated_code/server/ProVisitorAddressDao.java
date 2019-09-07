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
public class ProVisitorAddressDao {
    //Data fields
    public static void create(Connection conn, ProVisitorAddress entity, String logHeader) throws Exception{
        String query = "";
        try {
            //insert query
            query =
            "insert into PKI_PRO_VISITOR_ADDRESS("
                //field list
                + "form_id "
                + ",vis_sno "
                + ",s_no "
                + ",address_type "
                + ",house "
                + ",street "
                + ",city "
                + ",state "
                + ",pin "
                + ",country "
            + ") values ("
                //param list
                + "? " // form_id
                + ",? " // vis_sno
                + ",? " // s_no
                + ",? " // address_type
                + ",? " // house
                + ",? " // street
                + ",? " // city
                + ",? " // state
                + ",? " // pin
                + ",? " // country
            + ")";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setInt((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.visSno);
                ps.setInt((++paramCount), entity.sNo);
                ps.setString((++paramCount), entity.addressType);
                ps.setString((++paramCount), entity.house);
                ps.setString((++paramCount), entity.street);
                ps.setString((++paramCount), entity.city);
                ps.setString((++paramCount), entity.state);
                ps.setString((++paramCount), entity.pin);
                ps.setString((++paramCount), entity.country);
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
    public static void update(Connection conn, ProVisitorAddress entity, String logHeader) throws Exception{
        String query = "";
        try {
            //update query
            query =
            "update PKI_PRO_VISITOR_ADDRESS set "
                //update field list
                + "address_type = ? "
                + ",house = ? "
                + ",street = ? "
                + ",city = ? "
                + ",state = ? "
                + ",pin = ? "
                + ",country = ? "
            + "where "
                //primary key field list
                + "form_id = ? "
                + "AND vis_sno = ? "
                + "AND s_no = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setString((++paramCount), entity.addressType);
                ps.setString((++paramCount), entity.house);
                ps.setString((++paramCount), entity.street);
                ps.setString((++paramCount), entity.city);
                ps.setString((++paramCount), entity.state);
                ps.setString((++paramCount), entity.pin);
                ps.setString((++paramCount), entity.country);
                ps.setInt((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.visSno);
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
    public static void get(Connection conn, ProVisitorAddress entity, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "address_type "
                + ",house "
                + ",street "
                + ",city "
                + ",state "
                + ",pin "
                + ",country "
            + "from "
                + "PKI_PRO_VISITOR_ADDRESS "
            + "where "
                //primary key field list
                + "form_id = ? "
                + "AND vis_sno = ? "
                + "AND s_no = ? "
            + ";";
            //prepareStatement
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                int paramCount = 0;
                //set params
                ps.setInt((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.visSno);
                ps.setInt((++paramCount), entity.sNo);
                try( ResultSet rs = ps.executeQuery() ) {
                    if ( !rs.next()) {
                        throw new Exception("Select "+logHeader+" failed no record found!");
                    }
                    //get field list
                    entity.addressType = rs.getString("address_type");
                    entity.house = rs.getString("house");
                    entity.street = rs.getString("street");
                    entity.city = rs.getString("city");
                    entity.state = rs.getString("state");
                    entity.pin = rs.getString("pin");
                    entity.country = rs.getString("country");
                }
            }
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
    @SuppressWarnings("UseSpecificCatch")
    public static List<ProVisitorAddress> getAll(Connection conn, ProVisitor entity, String logHeader) throws Exception{
        String query = "";
        try {
            //select query
            query =
            "select "
                //field list
                + "form_id "
                + ",vis_sno "
                + ",s_no "
                + ",address_type "
                + ",house "
                + ",street "
                + ",city "
                + ",state "
                + ",pin "
                + ",country "
            + "from "
                + "PKI_PRO_VISITOR_ADDRESS "
            + "where "
                // parent primary key field list
                + "FORM_ID = ? "
                + "AND S_NO = ? "
            + ";";
            //prepareStatement
            List<ProVisitorAddress> proVisitorAddressList = new ArrayList<>();
            try( PreparedStatement ps = conn.prepareStatement(query) ) {
                //set params
                int paramCount = 0;
                ps.setLong((++paramCount), entity.formId);
                ps.setInt((++paramCount), entity.sNo);
                try( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next()) {
                        ProVisitorAddress proVisitorAddress = new ProVisitorAddress();
                        //get field list
                        proVisitorAddress.formId = rs.getInt("form_id");
                        proVisitorAddress.visSno = rs.getInt("vis_sno");
                        proVisitorAddress.sNo = rs.getInt("s_no");
                        proVisitorAddress.addressType = rs.getString("address_type");
                        proVisitorAddress.house = rs.getString("house");
                        proVisitorAddress.street = rs.getString("street");
                        proVisitorAddress.city = rs.getString("city");
                        proVisitorAddress.state = rs.getString("state");
                        proVisitorAddress.pin = rs.getString("pin");
                        proVisitorAddress.country = rs.getString("country");
                        proVisitorAddressList.add(proVisitorAddress);
                    }
                }
            }
            return proVisitorAddressList;
        } catch(Exception e) {
            System.out.println("Select "+logHeader+" failed! query: "+query);
            throw e;
        }
    }
}