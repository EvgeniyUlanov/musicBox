<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="resources/css/styles.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="resources/js/myScript.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#">Admin Page</a>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="#usersTable">User Table</a>
                <a class="nav-item nav-link" href="#">Music</a>
            </div>
        </div>
        <form class="form-inline" action="${pageContext.servletContext.contextPath}/signout">
            <input class="btn btn-primary" type="submit" value="Sign out">
        </form>
    </div>
</nav>

<div class="container">
    <div class="content row justify-content-center align-items-center" id="usersTable">
        <h1 class="text-center">Here is all users</h1>
        <table id="tableUsers" class="table">
            <tr>
                <th>Name</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
                <th>Delete user</th>
                <th>Full info</th>
            </tr>
        </table>
        <input class="btn btn-primary" id="sendToAddUser" value="add new user">
    </div>
</div>

<div class="container content" id="addUser">
    <h1 class="text-center">Add new User Form</h1>
    <div class="row justify-content-center align-items-center">
        <form>
            <label for="addName">Name:</label>
            <input class="form-control" id="addName" placeholder="enter user name"/>
            <label for="addLogin">Login:</label>
            <input class="form-control" id="addLogin" placeholder="enter user login" required/>
            <label for="addPassword">Password:</label>
            <input class="form-control" id="addPassword" placeholder="enter user password"/>
            <label for="addRole">Role:</label>
            <input class="form-control" id="addRole" placeholder="enter user role" required/>
            <label for="addAddress">Address:</label>
            <input class="form-control" id="addAddress" placeholder="enter user address"/>
            <input id="addUserBtn" class="btn btn-primary" value="add new user">
        </form>
    </div>
</div>
</body>
</html>
