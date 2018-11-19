<html>
    <body>
        <h1>Students</h1>
        ${students}

        <h1>Add new student</h1>
        <form action="students" method="post">
            Name: <input type="text" name="name">
        <br>
            Age: <input type="text" name="age">
        <br>
            Subject ids (through comma separator): <input type="text" name="subjectIds">
        <br>
            <input type="submit" value="Add">
        </form>
    </body>
</html>