<!DOCTYPE html>
<html>

<head>
	<title>Add Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Digital Library</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add new member</h3>
		
		<form action="holla.jsp" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>User Id:</label></td>
						<td><input type="text" name="memberId" /></td>
					</tr>
					
					<tr>
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" /></td>
					</tr>
					
					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" /></td>
					</tr>
					
					<tr>
						<td><label>Contact Number:</label></td>
						<td><input type="text" name="contactNum" /></td>
					</tr>
					
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="emailId" /></td>
					</tr>
					
					<tr>
						<td><label>Gender:</label></td>
						<td><input type="text" name="gender" /></td>
					</tr>
					
					<tr>
						<td><label>Occupation:</label></td>
						<td><input type="text" name="occupation" /></td>
					</tr>
						
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="MemberControllerServlet">Back</a>
		</p>
	</div>
</body>

</html>










