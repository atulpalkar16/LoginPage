<%@ page import="com.other.*" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    Boolean valid = (Boolean) session.getAttribute("loggedIn");

    if (valid == null || !valid) {
        response.sendRedirect("index.html");
    }
    String email=(String) session.getAttribute("email");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
    <h2 align="center">Welcome <%= email %></h2>
     <table border="1">
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<details> userDetailsList = (List<details>) session.getAttribute("userDetailsList");
                if (userDetailsList != null) {
                    for (details user : userDetailsList) {
            %>
                <tr>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getPassword() %></td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
