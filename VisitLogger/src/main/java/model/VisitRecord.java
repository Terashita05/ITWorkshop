package model;

import java.io.Serializable;

public class VisitRecord implements Serializable{
//	private int id;
//	private String userName;//ユーザー名
//	private String text;	//つぶやき内容
//	private String createdAt;//記録日時
//	private String notes;//備考欄
	int id;
	String visitDate; 
    String clientName;
    String contactPerson;
    String visitNotes;
    String address;
    String followUpDate;
    
	public VisitRecord() {}
	
	public VisitRecord(String clientName, String followUpDate) {
		this.clientName = clientName;
		this.followUpDate = followUpDate;
	}

	public VisitRecord(int id, String visitDate, String clientName, String contactPerson, String visitNotes,
			String address) {
		this.id = id;
		this.visitDate = visitDate;
		this.clientName = clientName;
		this.contactPerson = contactPerson;
		this.visitNotes = visitNotes;
		this.address = address;
	}



	public VisitRecord(int id, String visitDate, String clientName, String contactPerson, String visitNotes,
			String address, String  followUpDate) {
		this.id = id;
		this.visitDate = visitDate;
		this.clientName = clientName;
		this.contactPerson = contactPerson;
		this.visitNotes = visitNotes;
		this.address = address;
		this.followUpDate = followUpDate;
	}


	public VisitRecord(int id, String clientName, String visitDate, String address, String visitNotes) {
		this.id = id;
		this.visitDate = visitDate;
		this.clientName = clientName;
		this.visitNotes = visitNotes;
		this.address = address;
	}
	public VisitRecord(String visitDate, String clientName, String contactPerson, String visitNotes, String address) {
		super();
		this.visitDate = visitDate;
		this.clientName = clientName;
		this.contactPerson = contactPerson;
		this.visitNotes = visitNotes;
		this.address = address;
	}



	public int getId() {
		return id;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public String getClientName() {
		return clientName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public String getVisitNotes() {
		return visitNotes;
	}

	public String getAddress() {
		return address;
	}

	public String getFollowUpDate() {
		return followUpDate;
	}
	
	
//	public Mutter( String userName, String text) {
//		this.userName = userName;
//		this.text = text;
//	}
//	public Mutter(int id, String userName, String text) {
//		this.id = id;
//		this.userName = userName;
//		this.text = text;
//	}
//	public Mutter(int id, String userName, String text, String createdAt) {
//		this.id = id;
//		this.userName = userName;
//		this.text = text;
//		this.createdAt = createdAt;
//	}
//	public Mutter(int id, String userName, String text, String notes, String createdAt) {
//		this.userName = userName;
//		this.text = text;
//		this.notes = notes;
//		this.createdAt = createdAt;
//	}
//	public int getId() {return id;}
//	public String getUserName() {
//		return userName;
//	}
//	public String getText() {
//		return text;
//	}
//	public String getCreatedAt() {
//		return createdAt;
//	}
//	public String getNotes() {
//		return notes;
//	}
	
}
