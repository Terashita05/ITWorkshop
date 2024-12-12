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



public class VisitRecordDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:~/Desktop/SQL/dokoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<VisitRecord> findAll(){
		List<VisitRecord> visitRecordList = new ArrayList<>();
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		
			//SELECT文を準備
			//String sql = "SELECT ID,NAME,TEXT FROM MUTTERS ORDER BY ID DESC";
			//String sql = "SELECT ID,NAME,TEXT,NOTES,FORMATDATETIME(CREATED_AT, 'yyyy-MM-dd HH:mm') AS formatted_date FROM SALES_VISITS ORDER BY ID DESC";
			String sql ="SELECT ID, VISIT_DATE, CLIENT_NAME, CONTACT_PERSON, VISIT_NOTES, ADDRESS, FOLLOW_UP_DATE "
					+ "FROM SalesVisitRecord ORDER BY ID DESC";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			// 日時フォーマット（訪問日時と作成日時）
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			//結果表に格納されたレコードの内容を表示
			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
                Timestamp visitDate = rs.getTimestamp("VISIT_DATE");
                String clientName = rs.getString("CLIENT_NAME");
                String contactPerson = rs.getString("CONTACT_PERSON");
                String visitNotes = rs.getString("VISIT_NOTES");
                String address = rs.getString("ADDRESS");
                Timestamp followUpDate = rs.getTimestamp("FOLLOW_UP_DATE");

                String formattedVisitDate = null;
                String formattedFollowUpDate = null;
                if(visitDate != null ) {
	                // 訪問日時と作成日時をフォーマットして表示（秒なし）
	                formattedVisitDate = dateFormat.format(visitDate);
                }
                if(followUpDate != null) {
                	formattedFollowUpDate = dateFormat.format(followUpDate);
                }
				
//				int id = rs.getInt("ID");
//				String name = rs.getString("NAME");
//				String text = rs.getString("TEXT");
//				String notes = rs.getString("NOTES");
//				String createdAt = rs.getString("formatted_date");
				VisitRecord visitRecord = new VisitRecord(id, formattedVisitDate, clientName, contactPerson, visitNotes, address, formattedFollowUpDate);
				visitRecordList.add(visitRecord);

			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return visitRecordList;
	}
	public List<VisitRecord> find(String search){
		List<VisitRecord> visitRecordList = new ArrayList<>();
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
		
			//SELECT文を準備
			//String sql = "SELECT ID,NAME,TEXT FROM MUTTERS ORDER BY ID DESC";
			//String sql = "SELECT ID,NAME,TEXT,NOTES,FORMATDATETIME(CREATED_AT, 'yyyy-MM-dd HH:mm') AS formatted_date FROM SALES_VISITS  WHERE NAME LIKE ? ORDER BY ID DESC";
			String sql = "SELECT ID, VISIT_DATE, CLIENT_NAME, CONTACT_PERSON, VISIT_NOTES, ADDRESS, FOLLOW_UP_DATE " +
                    "FROM SalesVisitRecord WHERE VISIT_DATE LIKE ? OR CLIENT_NAME LIKE ? OR CONTACT_PERSON LIKE ? OR VISIT_NOTES LIKE ? OR ADDRESS LIKE ? ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			String searchWord = "%" + search + "%";
			pStmt.setString(1, searchWord);
			pStmt.setString(2, searchWord);
			pStmt.setString(3, searchWord);
			pStmt.setString(4, searchWord);
			pStmt.setString(5, searchWord);
			//SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			// 日時フォーマット（訪問日時と作成日時）
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			//結果表に格納されたレコードの内容を表示
			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
                Timestamp visitDate = rs.getTimestamp("VISIT_DATE");
                String clientName = rs.getString("CLIENT_NAME");
                String contactPerson = rs.getString("CONTACT_PERSON");
                String visitNotes = rs.getString("VISIT_NOTES");
                String address = rs.getString("ADDRESS");
                Timestamp followUpDate = rs.getTimestamp("FOLLOW_UP_DATE");

//                // 訪問日時と作成日時をフォーマットして表示（秒なし）
//                String formattedVisitDate = dateFormat.format(visitDate);
//                String formattedFollowUpDate = dateFormat.format(followUpDate);
                
                String formattedVisitDate = null;
                String formattedFollowUpDate = null;
                if(visitDate != null ) {
	                // 訪問日時と作成日時をフォーマットして表示（秒なし）
	                formattedVisitDate = dateFormat.format(visitDate);
                }
                if(followUpDate != null) {
                	formattedFollowUpDate = dateFormat.format(followUpDate);
                }
				
                VisitRecord visitRecord = new VisitRecord(id, formattedVisitDate, clientName, contactPerson, visitNotes, address, formattedFollowUpDate);
				visitRecordList.add(visitRecord);

			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return visitRecordList;
	}
	
	public boolean create(VisitRecord visitRecord) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//INSERT文の準備( idは自動連番なのでしていしなくてよい )
			String sql = "INSERT INTO SalesVisitRecord(VISIT_DATE, CLIENT_NAME, CONTACT_PERSON, VISIT_NOTES, ADDRESS, FOLLOW_UP_DATE) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//INSERT文中の「？」に使用する値を設定してSQL文を完成
			pStmt.setString(1, visitRecord.getVisitDate());
			pStmt.setString(2, visitRecord.getClientName());
			pStmt.setString(3, visitRecord.getContactPerson());
			pStmt.setString(4, visitRecord.getVisitNotes());
			pStmt.setString(5, visitRecord.getAddress());
			pStmt.setString(6, visitRecord.getFollowUpDate());			
			
			//INSERT文を実行（resultには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public VisitRecord findById(int id) {
        // データベース接続と検索処理
		
		VisitRecord visitRecord = null;
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM SalesVisitRecord WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
             
                    Timestamp visitDate = rs.getTimestamp("VISIT_DATE");
                    String clientName = rs.getString("CLIENT_NAME");
                    String contactPerson = rs.getString("CONTACT_PERSON");
                    String visitNotes = rs.getString("VISIT_NOTES");
                    String address = rs.getString("ADDRESS");
                    Timestamp followUpDate = rs.getTimestamp("FOLLOW_UP_DATE");
                 // 日時フォーマット（訪問日時と作成日時）
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    // 訪問日時と作成日時をフォーマットして表示（秒なし）
                    String formattedVisitDate = null;
                    String formattedFollowUpDate = null;
                    if(visitDate != null ) {
    	                // 訪問日時と作成日時をフォーマットして表示（秒なし）
    	                formattedVisitDate = dateFormat.format(visitDate);
                    }
                    if(followUpDate != null) {
                    	formattedFollowUpDate = dateFormat.format(followUpDate);
                    }
    				
//                    String formattedVisitDate = dateFormat.format(visitDate);
//                    String formattedFollowUpDate = dateFormat.format(followUpDate);
                    visitRecord = new VisitRecord(id, formattedVisitDate, clientName, contactPerson, visitNotes, address, formattedFollowUpDate);
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitRecord;
    }
	
	// 訪問記録を更新
    public void updateMutter(VisitRecord visitRecord) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "UPDATE SalesVisitRecord SET CLIENT_NAME = ?, VISIT_DATE = ?, ADDRESS = ?, VISIT_NOTES = ? , FOLLOW_UP_DATE = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, visitRecord.getClientName());
                pstmt.setString(2, visitRecord.getVisitDate());
                pstmt.setString(3, visitRecord.getAddress());
                pstmt.setString(4, visitRecord.getVisitNotes());
             // FOLLOW_UP_DATE が空文字なら NULL をセット
                if (visitRecord.getFollowUpDate() == null || visitRecord.getFollowUpDate().isEmpty()) {
                    pstmt.setNull(5, java.sql.Types.TIMESTAMP);
                } else {
                    pstmt.setString(5, visitRecord.getFollowUpDate());
                }
                
              //  pstmt.setString(5, visitRecord.getFollowUpDate());
                pstmt.setInt(6, visitRecord.getId());
                
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // 訪問記録の削除
    public boolean deleteMutter(int id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "DELETE FROM SalesVisitRecord WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int result = pstmt.executeUpdate(); // 削除実行
            return result > 0; // 削除成功時にtrueを返す
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
