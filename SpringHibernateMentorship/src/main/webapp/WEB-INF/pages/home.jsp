<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>

        <c:if test = "${not empty messageForAdmin}">
             <p><c:out value = "${messageForAdmin}"/><p>
        </c:if>
        <c:if test = "${not empty a}">
             <p><c:out value = "${a}"/><p>
        </c:if>

        <h1>
            Main Page
        </h1>

        <h2>
            <a href="/home/students">Students</a>
            <a href="/home/subjects">Subjects</a>
        </h2>

        <form action="logout" method="post">
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>
