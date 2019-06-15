<html>
<body>
<h2>Edit User</h2>
<p>${error}</p>
<form action="${pageContext.request.contextPath}/edit" method="post">
    Old Username:<br/>
    <label>
        <input type="text" name="oldusername"/>
    </label>
    <br/>
    Old Password:<br/>
    <label>
        <input type="password" name="oldpassword">
    </label>
    </br>
    New Username:<br/>
    <label>
        <input type="text" name="newusername"/>
    </label>
    <br/>
    New Password:<br/>
    <label>
        <input type="password" name="newpassword">
    </label>
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
