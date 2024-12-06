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

import model.Mutter;



public class MuttersDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:~/Desktop/SQL/dokoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public List<Mutter> findAll(){
		List<Mutter> mutterList = new ArrayList<>();
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
			String sql ="SELECT ID, VISIT_DATE, CLIENT_NAME, CONTACT_PERSON, VISIT_NOTES, ADDRESS, CREATED_AT "
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
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");

                // 訪問日時と作成日時をフォーマットして表示（秒なし）
                String formattedVisitDate = dateFormat.format(visitDate);
                String formattedCreatedAt = dateFormat.format(createdAt);
				
				
//				int id = rs.getInt("ID");
//				String name = rs.getString("NAME");
//				String text = rs.getString("TEXT");
//				String notes = rs.getString("NOTES");
//				String createdAt = rs.getString("formatted_date");
				Mutter mutter = new Mutter(id, formattedVisitDate, clientName, contactPerson, visitNotes, address, formattedCreatedAt);
				mutterList.add(mutter);

			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	public List<Mutter> find(String search){
		List<Mutter> mutterList = new ArrayList<>();
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
			String sql = "SELECT ID, VISIT_DATE, CLIENT_NAME, CONTACT_PERSON, VISIT_NOTES, ADDRESS, CREATED_AT " +
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
                Timestamp createdAt = rs.getTimestamp("CREATED_AT");

                // 訪問日時と作成日時をフォーマットして表示（秒なし）
                String formattedVisitDate = dateFormat.format(visitDate);
                String formattedCreatedAt = dateFormat.format(createdAt);
                Mutter mutter = new Mutter(id, formattedVisitDate, clientName, contactPerson, visitNotes, address, formattedCreatedAt);
				mutterList.add(mutter);

			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}
	
	public boolean create(Mutter mutter) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//INSERT文の準備( idは自動連番なのでしていしなくてよい )
			String sql = "INSERT INTO SalesVisitRecord(VISIT_DATE, CLIENT_NAME, CONTACT_PERSON, VISIT_NOTES, ADDRESS) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//INSERT文中の「？」に使用する値を設定してSQL文を完成
			pStmt.setString(1, mutter.getVisitDate());
			pStmt.setString(2, mutter.getClientName());
			pStmt.setString(3, mutter.getContactPerson());
			pStmt.setString(4, mutter.getVisitNotes());
			pStmt.setString(5, mutter.getAddress());
			
			
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
	
	public Mutter findById(int id) {
        // データベース接続と検索処理
		
		Mutter mutter = null;
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
                    Timestamp createdAt = rs.getTimestamp("CREATED_AT");
                 // 日時フォーマット（訪問日時と作成日時）
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    // 訪問日時と作成日時をフォーマットして表示（秒なし）
                    String formattedVisitDate = dateFormat.format(visitDate);
                    String formattedCreatedAt = dateFormat.format(createdAt);
                    mutter = new Mutter(id, formattedVisitDate, clientName, contactPerson, visitNotes, address, formattedCreatedAt);
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mutter;
    }
	
	// 訪問記録を更新
    public void updateMutter(Mutter mutter) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "UPDATE SalesVisitRecord SET CLIENT_NAME = ?, VISIT_DATE = ?, ADDRESS = ?, VISIT_NOTES = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, mutter.getClientName());
                pstmt.setString(2, mutter.getVisitDate());
                pstmt.setString(3, mutter.getAddress());
                pstmt.setString(4, mutter.getVisitNotes());
                pstmt.setInt(5, mutter.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
