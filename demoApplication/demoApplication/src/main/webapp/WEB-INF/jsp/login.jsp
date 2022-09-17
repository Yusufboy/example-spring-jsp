<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <form action="login" method="post">
    		<input type="text" name="username" placeholder="username"/>
    		<input type="password" name="password" placeholder="password"/>
			<input type="submit" value="Login" />
		</form>
    </body>
</html>