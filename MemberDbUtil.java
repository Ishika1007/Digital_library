package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MemberDbUtil {

	private DataSource dataSource;

	public MemberDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Member> getMembers() throws Exception {
		
		List<Member> Members = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from member order by last_name";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int userId= myRs.getInt("user_id");
				int memberId  = myRs.getInt("member_id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				int contactNum  = myRs.getInt("contact_no");
				String emailId = myRs.getString("email_id");
				String gender = myRs.getString("gender");
				String occupation = myRs.getString("occupation");
			//	int booksIssued  = myRs.getInt("no_of_books_issued");
				// create new Member object
				Member tempMember =  new Member(userId,memberId,firstName,lastName,contactNum,emailId,gender,occupation);
				
				// add it to the list of Members
				Members.add(tempMember);				
			}
			
			return Members;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addMember(Member theMember) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into member "
					   + "(user_id, member_id,first_name, last_name, contact_no,email_id,gender,occupation) "
					   + "values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the Member
			myStmt.setInt(1, theMember.getUserId());
			myStmt.setInt(2, theMember.getMemberId());
			myStmt.setString(3, theMember.getFirstName());
			myStmt.setString(4, theMember.getLastName());
			myStmt.setInt(5, theMember.getContactNum());
			myStmt.setString(6, theMember.getEmailId());
			myStmt.setString(7, theMember.getGender());
			myStmt.setString(8, theMember.getOccupation());
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Member getMember(String theMemberId) throws Exception {

		Member theMember = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int MemberId;
		
		try {
			// convert Member id to int
			MemberId = Integer.parseInt(theMemberId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected Member
			String sql = "select * from member where member_id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, MemberId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				
				int userId= myRs.getInt("user_id");
				int memberId  = myRs.getInt("member_id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				int contactNum  = myRs.getInt("contact_no");
				String emailId = myRs.getString("email_id");
				String gender = myRs.getString("gender");
				String occupation = myRs.getString("occupation");
			//	int booksIssued  = myRs.getInt("no_of_books_issued");
				// create new Member object
				theMember = new Member(userId,memberId,firstName,lastName,contactNum,emailId,gender,occupation);
							}
			else {
				throw new Exception("Could not find Member id: " + MemberId);
			}				
			
			return theMember;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public  void updateMember(Member theMember) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update member "
						+ "set first_name=?, last_name=?, contact_no=?,email_id=?,gender=?,occupation=? "
						+ "where member_id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theMember.getFirstName());
			myStmt.setString(2, theMember.getLastName());
			myStmt.setInt(3, theMember.getContactNum());
			myStmt.setString(4, theMember.getEmailId());
			myStmt.setString(5, theMember.getGender());
			myStmt.setString(6, theMember.getOccupation());
			myStmt.setInt(7, theMember.getMemberId());
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteMember(String theMemberId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert Member id to int
			int MemberId = Integer.parseInt(theMemberId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete Member
			String sql = "delete from member where member_id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, MemberId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















