<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<script type="text/javascript">
	function validate() {
		if (document.getElementById("userName").value == "") {
			document.getElementById("error").innerHTML = "<center>User Name required</center>";
			return false;
		} else if (document.getElementById("userPasswd").value == "") {
			document.getElementById("error").innerHTML = "<center>Password required</center>";
			return false;
		} else if (document.getElementById("confirmUserPasswd").value == "") {
			document.getElementById("error").innerHTML = "<center>Confirm password required</center>";
			return false;
		} else if (document.getElementById("userPasswd").value != document
				.getElementById("confirmUserPasswd").value) {
			document.getElementById("error").innerHTML = "<center>Password and Confirm password does not match</center>";
			return false;
		} else {
			return true;
		}
	}
</script>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="./">Pet Shop</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="login"><span class="glyphicon glyphicon-log-in"></span>
					Login</a></li>
		</ul>

	</div>
	</nav>
	<div class="container">
		<h4>Register</h4>
		<p id="success" style="color:red;">${registered}</p>
		<p id="error" style="color: red;"><%= request.getParameter("errorMsg")==null?"":request.getParameter("errorMsg")%></p>
		<form:form action="save" modelAttribute="user">
			<label for="Name">Name:</label>
			<form:input id="userName" path="userName" class="form-control" />
			<br>
			<label for="userPasswd">Password:</label>
			<form:password id="userPasswd" path="userPasswd" class="form-control" />
			<br>
			<label for="confirmUserPasswd">Conform Password:</label>
			<form:password id="confirmUserPasswd" path="confirmUserPasswd"
				class="form-control" />
			<br>
			<input type="submit" value="Register" onclick="return validate()"
				class="btn btn-primary" />
		</form:form>
	</div>


</body>
</html>