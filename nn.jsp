<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Library Management App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Welcome to Digital Library!!!</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
			
			<input type="button" value="Add Member" 
				   onclick="window.location.href='Login.jsp'; return false;"
				   class="add-student-button"
			/>
			
			<input type="button" value="Delete Member" 
				   onclick="window.location.href='Login.jsp'; return false;"
				   class="add-student-button"
			/>
			
			<input type="button" value="Update Member Info" 
				   onclick="window.location.href='Login.jsp'; return false;"
				   class="add-student-button"
			/>
			<h3> Current Members</h3>

			<table>
			
				<tr>
					<th> Member Id </th>
					<th> First Name</th>
					<th> Last Name</th>
					<th> Contact Num</th>
					<th> Email</th>
					<th> Gender</th>
					<th> Occupation</th>
				</tr>
				
				<c:forEach var="tempMember" items="${MEMBER_LIST}">
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="MemberControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="memberId" value="${tempMember.memberId}" />
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="MemberControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="memberId" value="${tempMember.memberId}" />
					</c:url>
																		
					<tr>
						<td> ${tempMember.memberId} </td>
						<td> ${tempMember.firstName} </td>
						<td> ${tempMember.lastName} </td>
						<td> ${tempMember.contactNum} </td>
						<td> ${tempMember.emailId} </td>
						<td> ${tempMember.gender} </td>
						<td> ${tempMember.occupation} </td>
						<td> 
							<a href="${tempLink}">Update</a> 
							 | 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this member?'))) return false">
							Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








