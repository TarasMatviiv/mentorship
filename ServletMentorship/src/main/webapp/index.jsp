<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
        <h1>
            Login yourself
        </h1>

        <c:if test = "${not empty errorMessage}">
             <p><c:out value = "${errorMessage}"/><p>
        </c:if>

        <form action="/login" method="post">
            Username: <input type="text" name="username">
            <br>
            Password: <input type="password" name="password">
            <br><br>
            <input type="submit" value="Login">
        </form>
    </body>
</html>