<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<title>Home Page</title>
<script type="text/javascript">
		function Myfunction(){
			document.querySelector('#Buy').innerHTML = 'Sold'
			document.getElementById("Buy").disabled = true;
			return true;
		}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="./">PetShop</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="home">Home</a></li>
            <li ><a href="myPets">My Pet</a></li>
            <li><a href="addPet">Add Pet</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="logout"><span class="glyphicon glyphicon-log-in"></span>
                    Logout</a></li>
        </ul>
    </div>
    </nav>
	<p id="home" >${moduleName}</p>
    <div align="center" class="table-responsive">
<table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">PetName</th>
                    <th scope="col">Age</th>
                    <th scope="col">Place</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pet" items="${pets}">
                    <tr>
                        <td>${pet.petId}</td>
                        <td>${pet.petName}</td>
                        <td>${pet.petAge}</td>
                        <td>${pet.petPlace}</td>
                        <td><c:if test="${pet.user == null}">
                                <form action="buyPet">
                                <input type="hidden" name="petId" value="${pet.petId}"/>
                                    <input type="submit" class="btn btn-primary" id="Buy"
                                        value="Buy" onclick="Myfunction()">
                                </form>
                            </c:if> <c:if test="${pet.user != null}">
                                <button type="button" class="btn btn-primary" value="Sold"
                                    disabled>Sold</button>
                            </c:if></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
            </div>

</body>
</html>