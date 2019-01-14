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
            <table>
                <tr>
                   <td>User:</td>
                   <td><input type='text' name='username' value=''></td>
                </tr>
                <tr>
                   <td>Password:</td>
                   <td><input type='password' name='password' /></td>
                </tr>
                <tr>
                   <td><input name="submit" type="submit" value="submit" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>