package model;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;
	private String name;//ユーザー名
	private String pass;//パスワード
	
	public User() {}
	public User(String userId,String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	public User(String userId, String pass, String name) {
		this.userId = userId;
		this.name = name;
		this.pass = pass;
	}
	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	
}
