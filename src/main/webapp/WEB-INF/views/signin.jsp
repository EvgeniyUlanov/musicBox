<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sing In Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container h-100">
    <div class="row h-100 justify-content-center align-items-center">
        <form action="${pageContext.servletContext.contextPath}/sign" method="post">
            <div class="form-group">
                <label for="login_id">Login</label>
                <input id="login_id" class="form-control" name="login">
            </div>
            <div class="form-group">
                <label for="password_id">Password</label>
                <input id="password_id" class="form-control" name="password" type="password">
            </div>
            <input type="submit" class="btn btn-primary" value="sign in">
        </form>
    </div>
</div>
</body>
</html>
