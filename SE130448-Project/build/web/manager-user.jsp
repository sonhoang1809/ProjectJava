<%-- 
    Document   : manager-user
    Created on : Dec 16, 2019, 5:36:39 PM
    Author     : sonho
--%>

<%@page import="sample.dtos.HouseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage User Page</title>
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
            List<UserDTO> listUserMU = (List<UserDTO>) request.getAttribute("LISTUSER");
            String searchValue = (String) request.getAttribute("SEARCHVALUE");
            if (searchValue == null) {
                searchValue = "";
            }
            if (listUserMU == null || searchValue == null) {
                searchValue = "";
                request.getRequestDispatcher("MainController?action=Search User&searchValue=" + searchValue).forward(request, response);
            }
            listUserMU = (List<UserDTO>) request.getAttribute("LISTUSER");

            
            if (listUserMU != null) {
        %>
        <div style="margin: auto;">
            <form action="MainController" method="GET">
                <%
                    if (searchValue == null || searchValue.equalsIgnoreCase("")) {
                %>
                <input type="text" name="searchValue" value=""/>
                <%} else {%>
                <input type="text" name="searchValue" value="<%=searchValue%>"/>
                <%}%>

                <input type="submit" name="action" value="Search User"/>
                <input type="submit" name="action" value="Create new User"/>
            </form>
            <%
                if (listUserMU != null) {%>
            <table border="1" style="margin: auto;">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (UserDTO x : listUserMU) {
                    %>
                    <tr>
                <form action="MainController" method="POST">
                    <td><%=++count%></td>
                    <td>
                        <input type="text" name="userID" value="<%=x.getUserID()%>" readonly="true"/>
                    </td>
                    <td>
                        <input type="text" name="userName" value="<%=x.getUserName()%>" required="true"/>
                    </td>
                    <td>
                        <input type="email" name="email" value="<%=x.getEmail()%>" readonly="true"/>
                    </td>
                    <td>
                        <select name="roleID">
                            <%
                                if (x.getRoleID().equalsIgnoreCase("AD")) {
                            %>
                            <option value="<%=x.getRoleID()%>">
                                Administrator
                            </option>
                            <option value="user">
                                Customer
                            </option>
                            <%} else {%>
                            <option value="<%=x.getRoleID()%>">
                                Customer
                            </option>
                            <option value="AD">
                                Administrator
                            </option>
                            <%}%>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="searchValue" value="<%=searchValue%>"/>
                        <input type="submit" class="site-btn btn-line" name="action" value="Update User"/>
                    </td>
                    <td>
                        <input type="hidden" name="searchValue" value="<%=searchValue%>"/>
                        <input type="submit" class="site-btn btn-line" name="action" value="Delete User"/>
                    </td>
                </form>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
        <%
                }
            }
        %>
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
