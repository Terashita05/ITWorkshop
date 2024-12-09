package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class AccountsDAO {
	private final String JDBC_URL = "jdbc:h2:~/Desktop/SQL/dokoTsubu";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public User findByLogin(User login) {
		User user = null;
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベースへの接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			//SELECT文を準備
			String sql = "SELECT USERID, PASS, NAME FROM ACCOUNT WHERE USERID = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,  login.getUserId());
			pStmt.setString(2,  login.getPass());
			
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				//ユーザーが存在したらデータを取得
				//そのユーザーを表すAccountインスタンスを生成
				String userId = rs.getString("USERID");
				String pass = rs.getString("PASS");
				String name = rs.getString("NAME");
				user = new User(userId, pass, name);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
}
