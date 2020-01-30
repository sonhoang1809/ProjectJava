<%-- 
    Document   : manager-bill
    Created on : Dec 17, 2019, 11:00:53 PM
    Author     : sonho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage bill</title>
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
            List<BillDTO> listBill = (List<BillDTO>) request.getAttribute("LISTBILL");
            if (listBill != null) {

        %>

        <table border="1" style="width: 60%; margin: auto;">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Id Bill</th>
                    <th>Id User</th>
                    <th>Total</th>
                    <th>Date</th>
                    <th>Status Bill</th>
                    <th>Bill Number</th>
                    <th>View Detail A Bill</th>
                </tr>
            </thead>
            <tbody>
                <%                    int count = 0;
                    for (BillDTO x : listBill) {

                %>
                <tr>
                    <td>
                        <%=++count%>
                    </td>
                    <td>
                        <%=x.getIdBill()%>
                    </td>
                    <td>
                        <%=x.getUserID()%>
                    </td>
                    <td><%=x.getTotal()%></td>
                    <td><%=x.getDate()%></td>
                    <%
                        if (x.getIdStatusBill() == 0) {
                    %>
                    <td>
                        Not Payed
                    </td>
                    <%} else {%>
                    <td>
                        Payed
                    </td>
                    <%}%>
                    <td><%=x.getBillNum()%></td>
                    <td>
                        <form action="MainController">
                            <input type="hidden" name="total" value="<%=x.getTotal()+""%>"/>
                            <input type="hidden" name="userID" value="<%=x.getUserID()%>"/>
                            <input type="hidden" name="idBill" value="<%=x.getIdBill()%>"/>
                            <input type="submit" name="action" class="site-btn btn-line" value="View Detail Bill User"/>
                        </form>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>





        <%            }%>
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
