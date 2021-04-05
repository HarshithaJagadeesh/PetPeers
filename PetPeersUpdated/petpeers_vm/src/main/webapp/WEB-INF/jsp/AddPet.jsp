<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Pet</title>

</head>
<body>
    <nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="./">PetShop</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="home">Home</a></li>
            <li><a href="myPets">My Pet</a></li>
            <li class="active"><a href="addPet">Add Pet</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>
                    Logout</a></li>
        </ul>
    </div>
    </nav>

 

    <center>
        <h2>Pet Information</h2>
            </center>
            <div style="padding-top: 10px" class="panel-body">
                <form:form action="savePet" class="form-horizontal" method="post"
                    role="form" modelAttribute="pet">
                    <div style="margin-bottom: 10px" class="input-group">
                        <label for="Name">Pet Name</label><br>
                        <form:input id="name" type="text" class="form-control"
                            name="petName" path="petName" placeholder="Enter pet name" size="30" />
                    </div>
                    <div style="margin-bottom: 10px" class="input-group">
                        <label for="NameDemo">Pet Age</label><br>
                        <form:input id="age" type="text" class="form-control"
                            name="petAge" path="petAge" placeholder="Enter pet age" size="30" />
                    </div>
                    <div style="margin-bottom: 10px" class="input-group">
                        <label for="Place">Pet Place</label><br>
                        <form:input id="place" type="text" class="form-control"
                            name="petPlace" path="petPlace" placeholder="Enter pet place"
                            size="30" />
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-0 col-sm-9 m-t-15">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <button type="reset" class="btn btn-primary">Cancel</button>
                        </div>
                    </div>
                </form:form>
            </div>


</body>
</html>