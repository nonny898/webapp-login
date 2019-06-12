<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
    </head>
    <body>
    <table border="1" cellpadding="5">
        <tr>
            <td><b>Username</b></td>
            <td><b>Password</b></td>
        </tr>
        <%Iterator itr;%>
        <% List data= (List)request.getAttribute("data");
            for (itr=data.iterator(); itr.hasNext(); )
            {
        %>
        <tr>
            <td><%=itr.next()%></td>
            <td><%=itr.next()%></td>
        </tr>
        <%}%>
    </table>
    </br>
    </br>
    <form action="add">
        <input type="submit" value="Add User">
    </form>
    <form action="edit">
        <input type="submit" value="Edit">
    </form>
    <form action="delete">
        <input type="submit" value="Remove User">
    </form>
    <form action="logout">
        <input type="submit" value="Logout">
    </form>
    </body>
    </html>