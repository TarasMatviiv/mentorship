<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <body>
        <%
        String message = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("testCookie")) message = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
        }
        %>
        <h4><%=message%></h4>

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