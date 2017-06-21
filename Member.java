package jdbc;

public class Member {
	private int userId;
	private int memberId;
	private String firstName;
	private String lastName;
	private int contactNum;
	private String emailId;
	private String gender;
	private int booksIssued;
	private String occupation;
	
	public Member(int userId, int memberId, String firstName,String lastName, int contactNum, String emailId, String gender,String occupation) {
		super();
		this.userId = userId;
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNum = contactNum;
		this.emailId = emailId;
		this.gender = gender;
		this.occupation = occupation;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getContactNum() {
		return contactNum;
	}

	public void setContactNum(int contactNum) {
		this.contactNum = contactNum;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}

