<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>

        <h1>
            Main Page
        </h1>

        <h2>
            <a href="/home/students">Students</a>
            <a href="/home/subjects">Subjects</a>
        </h2>

        <form action="/logout" method="post">
            <input type="submit" value="Logout" >
        </form>
    </body>
</html>
