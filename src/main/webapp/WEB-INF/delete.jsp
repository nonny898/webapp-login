<html>
<body>
<h2>Delete User</h2>
<p>${error}</p>
<form action="${pageContext.request.contextPath}/delete" method="post">
    Username:<br/>
    <input type="text" name="username"/>
    <br/>
    Password:<br/>
    <input type="password" name="password">
    <br><br>
    <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete?')" />
</form>
</body>
</html>
