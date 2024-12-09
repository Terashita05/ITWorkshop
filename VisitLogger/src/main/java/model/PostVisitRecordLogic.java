package model;

import dao.VisitRecordDAO;

public class PostVisitRecordLogic {
	public void execute(VisitRecord visitRecord) {
		VisitRecordDAO dao = new VisitRecordDAO();
		dao.create(visitRecord);
	}
}
