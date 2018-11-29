<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <body>
        <h1>Students</h1>
        ${students}

        <h1>Add new student</h1>
        <form action="students" method="post">
            <table>
                <tr>
                    <td>Name:<input type="text" name="name"></td>
                </tr>
                <tr>
                     <td>Age: <input type="text" name="age"></td>
                </tr>
                <c:forEach var="subject" items="${subjects}">
                    <tr>
                        <td colspan="2"><input type="checkbox" name="subjectIdsCheckbox" value="${subject.id}">${subject.title}</input></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <input type="submit" value="Add">
                    </td>
                </tr>
             </table>
        </form>
    </body>
</html>