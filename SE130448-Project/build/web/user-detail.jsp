<%-- 
    Document   : user-detail
    Created on : Dec 16, 2019, 12:29:50 PM
    Author     : sonho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail user</title>
        <link rel="stylesheet" href="style/style.css" type="text/css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <%
            UserDTO userDTOShowDetail = (UserDTO) session.getAttribute("USERDTO");
            List<BillDTO> listBillUser = (List<BillDTO>) request.getAttribute("LISTBILLUSER");
        %>

        <%if (userDTOShowDetail != null) {%>
        <div class="detail-user">
            <h3>Detail User</h3>
            <form action="MainController" method="POST">
                <table border="1">
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>User ID :</td>
                            <td><input type="text" name="userID" value="<%=userDTOShowDetail.getUserID()%>" required="true" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td>User Name :</td>
                            <td>
                                <input type="text" name="userName" value="<%=userDTOShowDetail.getUserName()%>" required="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>
                                <input type="email" name="email" value="<%=userDTOShowDetail.getEmail()%>" required="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Password: </td>
                            <td><input type="password" name="password" value="<%=userDTOShowDetail.getPassword()%>" required="true"/></td>
                        </tr>
                        <tr>
                            <td>Role ID</td>
                            <td>
                                <input type="text" name="roleID" value="<%=userDTOShowDetail.getRoleID()%>" readonly="true" required="true"/></td>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div>
                                    <input type="submit" class="site-btn btn-line" name="action" value="Update Detail"/>
                                </div>
                            </td>
                            <td>

                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <form action="MainController">
                <%
                    if (userDTOShowDetail.getRoleID().equalsIgnoreCase("user")) {
                %>
                <input type="submit" class="site-btn btn-line" name="action" value="View Bill User"/>
                <%}%>

                <input type="submit" class="site-btn btn-line" name="action" value="Logout"/>
            </form>

        </div>
        <%} else {
                response.sendRedirect("index.jsp");
            }%>
        <%
            if (listBillUser != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Id Bill</th>
                    <th>Total Bill</th>
                    <th>Date</th>
                    <th>Status Bill</th>
                    <th>Bill Number</th>
                    <th>View Detail Bill</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int n = 0;
                    for (BillDTO bill : listBillUser) {
                %>
                <tr>
                    <td><%=++n%></td>
                    <td><%=bill.getIdBill()%></td>
                    <td><%=bill.getTotal()%></td>
                    <td><%=bill.getDate()%></td>
                    <td><%=bill.getStatusBill()%></td>
                    <td><%=bill.getBillNum()%></td>
                    <td>
                        <form action="MainController">
                            <input type="hidden" name="idBill" value="<%=bill.getIdBill()%>"/>
                            <input type="submit" class="site-btn btn-line" name="action" value="View Detail Bill"/>
                        </form>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>

        <%} else {
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <%= message%>
        <%}
            }%>

        <%
            List<ProductsInBillDTO> listDetailProductInBill = (List<ProductsInBillDTO>) request.getAttribute("DETAILPRODUCTINBILL");
        %>

        <%
            if (listDetailProductInBill != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Id House</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int n2 = 0;
                    for (ProductsInBillDTO x : listDetailProductInBill) {
                %>
                <tr>
                    <td><%=++n2%></td>
                    <td><%=x.getIdHouse()%></td>
                    <td><%=x.getPrice()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>

        <%}%>

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
