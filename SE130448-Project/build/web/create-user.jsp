<%-- 
    Document   : create-user
    Created on : Dec 17, 2019, 3:32:51 PM
    Author     : sonho
--%>

<%@page import="sample.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%
            UserDTO dtoUserMH = (UserDTO) session.getAttribute("USERDTO");
            if (dtoUserMH == null) {
                response.sendRedirect("index.jsp");
                return;
            }
            if (!dtoUserMH.getRoleID().equalsIgnoreCase("AD")) {
                response.sendRedirect("index.jsp");
                return;
            }
            String message = (String) request.getAttribute("ERRORUSERIDMESSAGE");
        %>
        <div style="width: 500px; margin: auto;">
            <h3>Create User</h3>
            <form action="MainController" method="POST">
                <%
                    if (message != null) {
                %>
                <h4><%=message%></h4>
                <%}%>
                User ID: <input type="text" name="userID" required="true"/><br>
                User Name: <input type="text" name="userName" required="true"/><br>
                Email: <input type="email" name="email" required="true"/><br>
                Password: <input type="password" name="password" required="true"/><br>
                Role: 
                <select name="roleID">
                    <option value="AD">
                        Administrator
                    </option>
                    <option value="user">
                        Customer
                    </option>
                </select><br>

                
                <input type="submit" class="site-btn btn-line" name="action" value="Create User"/>
                <input type="reset" value="Reset"/>
            </form>
        </div>
        <%@include file="footer.html" %>
        <!--Script-->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
