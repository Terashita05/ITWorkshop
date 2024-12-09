package model;

import java.util.List;

import dao.VisitRecordDAO;

public class GetVisitRecordListLogic {
	public List<VisitRecord> execute(){
		VisitRecordDAO dao = new VisitRecordDAO();
		List<VisitRecord>visitRecordList = dao.findAll();
		return visitRecordList;
	}
	public List<VisitRecord> execute(String search){
		VisitRecordDAO dao = new VisitRecordDAO();
		List<VisitRecord>visitRecordList = dao.find(search);
		return visitRecordList;
	}
}
