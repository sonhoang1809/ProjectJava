<%-- 
    Document   : insert-house
    Created on : Dec 17, 2019, 8:43:46 AM
    Author     : sonho
--%>

<%@page import="sample.dtos.HouseDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert New House Page</title>
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
            List<TypeDTO> listTypeIH = (List<TypeDTO>) session.getAttribute("LISTTYPE");
            if (listTypeIH == null) {
                request.getRequestDispatcher("MainController?action=GetListType").forward(request, response);
            }
            listTypeIH = (List<TypeDTO>) session.getAttribute("LISTTYPE");
            List<WayDTO> listWayIH = (List<WayDTO>) request.getAttribute("LISTWAY");
            if (listWayIH == null) {
                request.getRequestDispatcher("MainController?action=GetListWay").forward(request, response);
            }
            listWayIH = (List<WayDTO>) request.getAttribute("LISTWAY");
        %>
        <div class="insert-house" style="width: 800px; height: auto; margin: auto; padding: auto;">
            <form action="MainController" enctype="multipart/form-data" method="POST">
                Select a Picture House: <input type="file" name="imgFile" accept=".jpg" /> <br>
                Description: <input type="text" name="description" required="true"><br>
                Furniture: 
                Lot size: <input type="number" name="lotSize" min="1000" value="1000" step="10" max="10000" required="true" style="width: 80px;"/>
                Number bed: <input type="number" name="numBed" min="1" value="1" required="true" style="width: 63px;"/>
                Number bath: <input type="number" name="numBath" min="1" value="1" required="true" style="width: 63px;"/>
                Number garage: <input type="number" name="numGarage" min="1" value="1" required="true" style="width: 63px;"/> <br>
                Price: <input type="number" name="price" value="100000" min="100000" max="1000000" step="10000" required="true" style="width: 100px;"/>
                Type: 
                <select name="typeID">
                    <%
                        if (listTypeIH != null) {
                            for (TypeDTO x : listTypeIH) {
                    %>
                    <option value="<%=x.getTypeID()%>"><%=x.getTypeName()%></option>  
                    <%
                            }
                        }%>
                </select>
                <br>
                Way:
                <select name="idWay">
                    <% if (listWayIH != null) {
                            for (WayDTO x : listWayIH) {%>
                    <option value="<%=x.getIdWay()%>">
                        <%= x.getWayName()%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
                
                <input type="submit" class="site-btn btn-line" name="action" value="Insert House"/>
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
