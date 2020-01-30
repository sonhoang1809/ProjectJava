<%-- 
    Document   : single-detail-bill
    Created on : Dec 17, 2019, 11:33:06 PM
    Author     : sonho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Bill</title>
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

            List<ProductsInBillDTO> listDetailProductInBill = (List<ProductsInBillDTO>) request.getAttribute("DETAILPRODUCTINBILL");
        %>

        <%
            if (listDetailProductInBill != null) {
        %>
        <h3>Detail Bill's ID: <%=request.getParameter("idBill")%> - User ID: <%=request.getParameter("userID")%></h3>
        <table border="1" style="width: 50%;margin: auto;">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Id House</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int n3 = 0;
                    for (ProductsInBillDTO x : listDetailProductInBill) {
                %>
                <tr>
                    <td><%=++n3%></td>
                    <td><%=x.getIdHouse()%></td>
                    <td><%=x.getPrice()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
            <h4>Total: <%=request.getParameter("total")%></h4>
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
