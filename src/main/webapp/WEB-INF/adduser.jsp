<html>
<body>
<h2>Add User</h2>
<p>${error}</p>
<form action="${pageContext.request.contextPath}/add" method="post">
    Username:<br/>
    <label>
        <input type="text" name="username"/>
    </label>
    <br/>
    Password:<br/>
    <label>
        <input type="password" name="password">
    </label>
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
