package jdbc;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jdbc.Member;
import jdbc.MemberDbUtil;
/*
 * Servlet implementation class MemberControlServlet
 */
@WebServlet("/MemberControlServlet")
public class MemberControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDbUtil memberDbUtil;
	
	@Resource(name="jdbc/db")
	private DataSource dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#getServletConfig()
	 */


	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our Member db util ... and pass in the conn pool / datasource
		try {
			memberDbUtil = new MemberDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing Members
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listMembers(request, response);
				break;
				
			case "ADD":
				addMember(request, response);
				break;
				
			case "LOAD":
				loadMember(request, response);
				break;
				
			case "UPDATE":
				updateMember(request, response);
				break;
			
			case "DELETE":
				deleteMember(request, response);
				break;
				
			default:
				listMembers(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Member id from form data
		String theMemberId = request.getParameter("MemberId");
		
		// delete Member from database
		memberDbUtil.deleteMember(theMemberId);
		
		// send them back to "list Members" page
		listMembers(request, response);
	}

	private void updateMember(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Member info from form data
		int userId = Integer.parseInt(request.getParameter("userId"));
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int contactNum = Integer.parseInt(request.getParameter("contactNum"));
		String emailId = request.getParameter("emailId");
        String gender = request.getParameter("gender");
        String occupation = request.getParameter("occupation");
        
		// create a new Member object
		Member theMember = new Member(userId,memberId,firstName,lastName,contactNum,emailId,gender,occupation);
		
		// perform update on database
		memberDbUtil.updateMember(theMember);
		
		// send them back to the "list Members" page
		listMembers(request, response);
		
	}

	private void loadMember(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read Member id from form data
		String theMemberId = request.getParameter("memberId");
		
		// get Member from database (db util)
		Member theMember = memberDbUtil.getMember(theMemberId);
		
		// place Member in the request attribute
		request.setAttribute("THE_MEMBER", theMember);
		
		// send to jsp page: update-Member-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/holla.jsp");
		dispatcher.forward(request, response);		
	}

	private void addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Member info from form data
		int userId = Integer.parseInt(request.getParameter("userId"));
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int contactNum = Integer.parseInt(request.getParameter("contactNum"));
		String emailId = request.getParameter("emailId");
        String gender = request.getParameter("gender");
        String occupation = request.getParameter("occupation");		
		
		// create a new Member object
		Member theMember = new Member(userId,memberId,firstName,lastName,contactNum,emailId,gender,occupation);
		
		// add the Member to the database
		memberDbUtil.addMember(theMember);
				
		// send back to main page (the Member list)
		listMembers(request, response);
	}

	private void listMembers(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get Members from db util
		List<Member> Members = memberDbUtil.getMembers();
		
		// add Members to the request
		request.setAttribute("MEMBER_LIST", Members);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/holla.jsp");
		dispatcher.forward(request, response);
	}


}
