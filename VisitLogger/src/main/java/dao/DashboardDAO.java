package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.VisitRecord;

public class DashboardDAO {
	  private static final String DB_URL = "jdbc:h2:~/Desktop/SQL/dokoTsubu";
	    private static final String DB_USER = "sa";
	    private static final String DB_PASS = "";

	    public List<VisitRecord> getNextVisitRecords(String contactPerson) {
	        List<VisitRecord> list = new ArrayList<>();
	        String sql = "SELECT ID, VISIT_DATE, CLIENT_NAME, CONTACT_PERSON, VISIT_NOTES, ADDRESS, FOLLOW_UP_DATE FROM SalesVisitRecord WHERE CONTACT_PERSON = ? AND FOLLOW_UP_DATE IS NOT NULL ORDER BY FOLLOW_UP_DATE ASC";
	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
	             PreparedStatement pstmt = conn.prepareStatement(sql);	
	             pstmt.setString(1, contactPerson);
	             ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                String clientName = rs.getString("CLIENT_NAME");
	               
	                
	                Timestamp followUpDate = rs.getTimestamp("FOLLOW_UP_DATE");
	                 // 日時フォーマット（訪問日時と作成日時）
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	                String formattedFollowUpDate = null;
	                
	                if(followUpDate != null) {
	                	formattedFollowUpDate = dateFormat.format(followUpDate);
	                }
					
	               
	                list.add(new VisitRecord(clientName, formattedFollowUpDate));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
}
