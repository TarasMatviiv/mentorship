<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>

        <c:if test = "${not empty messageForAdmin}">
            <p><c:out value = "${messageForAdmin}"/><p>
        </c:if>

        <h1>Subjects</h1>
        ${subjects}

        <form action="subjects" method="get">
                   Title: <input type="text" name="title">
               <br>
                   <input type="submit" value="Get subject">
        </form>

        Coefficient:  ${subject.coefficient}
        <br>
        Students:     ${subject.students}
    </body>
</html>