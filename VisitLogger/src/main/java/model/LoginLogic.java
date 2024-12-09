package model;

import dao.AccountsDAO;

public class LoginLogic {
	public User execute(User login) {
		AccountsDAO dao = new AccountsDAO();
		User user = dao.findByLogin(login);
		return user;
	}
}
