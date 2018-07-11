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
                <a class="nav-item nav-link" href="#musicTable">Music</a>
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
        <div class="col-md-4">
            <input class="btn btn-primary" value="add new user" onclick="SendToAddUser()">
        </div>
        <div class="col-md-8">
            <div class="input-group">
                <div class="input-group-prepend">
                    <select id="findSelected" class="custom-select">
                        <option value="all">All users</option>
                        <option value="name">By name</option>
                        <option value="login">By login</option>
                        <option value="role">By role</option>
                        <option value="address">By address</option>
                        <option value="music">By favorite music</option>
                    </select>
                </div>
                <input id="valueToFind" class="form-control" placeholder="Enter search value" aria-describedby="basic-addon2"/>
                <div class="input-group-append">
                    <input class="btn btn-primary" value="find" onclick="FillTable($('#findSelected').val(), $('#valueToFind').val())">
                </div>
            </div>
        </div>
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
            <select id="addRole" class='roleSelect form-control'></select>
            <label for="addAddress">Address:</label>
            <input class="addressSelect form-control" id="addAddress" placeholder="enter user address"/>
            <input id="addUserBtn" class="btn btn-primary" value="add new user">
        </form>
    </div>
</div>

<div class="container content" id="infoUser">
    <h1 class="text-center">User info</h1>
    <div class="row justify-content-center align-items-center">
        <div class="col-md-4">
            <form>
                <input id="infoId" class="form-control" hidden/>
                <label for="infoName">Name:</label>
                <input class="form-control" id="infoName" readonly/>
                <label for="infoLogin">Login:</label>
                <input class="form-control" id="infoLogin" readonly/>
                <label for="infoPassword">Password:</label>
                <input class="form-control" id="infoPassword" readonly/>
                <label for="infoRole">Role:</label>
                <input id="infoRole" class='form-control' readonly/>
                <label for="infoAddress">Address:</label>
                <input class="form-control" id="infoAddress" readonly/>
                <input id="updateUserBtn" class="btn btn-primary" value="update user">
            </form>
        </div>
        <div class="col-md-4">
            <h2 class="text-center">Favorite music</h2>
            <ul id="userMusic"></ul>
            <div class="input-group">
                <select id="addUserMusic" class="musicTypesSelect custom-select"></select>
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button" onclick="AddMusicToUser()">Add</button>
                </div>
                <select id="removeMusic" class="removeUserMusic custom-select"></select>
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button" onclick="DeleteMusicFromUser()">Remove
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container content" id="updateUser">
    <h1 class="text-center">Update User Form</h1>
    <div class="row justify-content-center align-items-center">
        <form>
            <label for="updateName">Name:</label>
            <input class="form-control" id="updateName" placeholder="enter user name"/>
            <label for="updateLogin">Login:</label>
            <input class="form-control" id="updateLogin" placeholder="enter user login" required/>
            <label for="updatePassword">Password:</label>
            <input class="form-control" id="updatePassword" placeholder="enter user password"/>
            <label for="updateRole">Role:</label>
            <select id="updateRole" class='roleSelect form-control' name='role'></select>
            <label for="updateAddress">Address:</label>
            <input class="form-control" id="updateAddress" placeholder="enter user address"/>
            <input id="confirmUpdateBtn" class="btn btn-primary" value="confirm update" onclick="ConfirmUpdateAction()">
        </form>
    </div>
</div>

<div class="container">
    <div class="content row justify-content-center align-items-center" id="musicTable">
        <div class="col-md-8">
            <h1 class="text-center">Here is music types</h1>
            <table id="tableMusic" class="table">
                <tr>
                    <th>Music Type</th>
                    <th>Delete music</th>
                </tr>
            </table>
            <div class="input-group">
                <input id="newMusicType" class="form-control" placeholder="new music type"/>
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button" onclick="AddMusicType()">Add</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
