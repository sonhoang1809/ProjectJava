<%-- 
    Document   : manager-house
    Created on : Dec 16, 2019, 5:36:52 PM
    Author     : sonho
--%>

<%@page import="sample.dtos.HouseDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage House Page</title>
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
            List<HouseDTO> listHouse = (List<HouseDTO>) request.getAttribute("LISTHOUSE");
            if (listHouse == null) {
                request.getRequestDispatcher("MainController?action=GetAllHouse").forward(request, response);
            }
            listHouse = (List<HouseDTO>) request.getAttribute("LISTHOUSE");
            List<TypeDTO> listTypeMH = (List<TypeDTO>) session.getAttribute("LISTTYPE");
            String messageMH = (String) request.getAttribute("MESSAGE");
            if (listHouse != null) {
        %>
        <form action="MainController">
            <input type="submit" class="site-btn btn-line" id="InsertNewHouse" name="action" value="Insert New House"/>
        </form>
        <%
            if (messageMH != null) {
        %>
        <%=messageMH%>
        <%}%>
        <table border="1" style="margin: auto;">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Id House</th>
                    <th>Pic House</th>
                    <th>Description</th>
                    <th>Lot Size</th>
                    <th>Beds</th>
                    <th>Baths</th>
                    <th>Garage</th>
                    <th>Type ID</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (HouseDTO x : listHouse) {
                %>
                <tr>
            <form action="MainController">
                <td>
                    <%=++count%>
                </td>
                <td>
                    <input type="text" name="idHouse" value="<%=x.getIdHouse()%>" readonly="true" style="width: 63px;"/>
                </td>
                <td>
                    <input type="text" name="picHouse" value="<%=x.getPicHouse()%>" required="true"/>
                </td>
                <td>
                    <input type="text" name="description" value="<%=x.getDescription()%>" required="true" style="width: auto;"/>
                </td>
                <td>
                    <input type="number" name="lotSize" value="<%=x.getFurniture().getLotSize()%>" min="1" max="10000" required="true" style="width: 63px;"/>
                </td>
                <td>
                    <input type="number" name="numBed" value="<%=x.getFurniture().getNumBed()%>" min="1" required="true" style="width: 63px;"/>
                </td>
                <td>
                    <input type="number" name="numBath" value="<%=x.getFurniture().getNumBath()%>" min="1" required="true" style="width: 63px;"/>
                </td>
                <td>
                    <input type="number" name="numGarage" value="<%=x.getFurniture().getNumGarage()%>" min="1" required="true" style="width: 63px;"/>
                </td>
                <td>
                    <select name="typeID">
                        <%
                            if (listTypeMH != null) {%>
                        <option value="<%=x.getTypeId()%>">
                            <%
                                String typeNameMH = null;
                                for (TypeDTO type : listTypeMH) {
                                    if (type.getTypeID().equalsIgnoreCase(x.getTypeId())) {
                                        typeNameMH = type.getTypeName();
                                    }
                                }
                            %>
                            <%=typeNameMH%>
                        </option>
                        <%
                            for (TypeDTO type : listTypeMH) {
                                if (!type.getTypeID().equalsIgnoreCase(x.getTypeId())) {
                        %>
                        <option value="<%=type.getTypeID()%>"><%=type.getTypeName()%></option>
                        <%}
                                }
                            }%>
                    </select>

                </td>
                <td>
                    <input type="number" name="price" value="<%=x.getPrice()%>" min="100000" max="1000000" step="10" required="true" style="width: 100px;"/>
                </td>
                <td>
                    <select name="statusCode">
                        <option value="<%=x.getStatusCode()%>">
                            <%
                                String statusNameMH = null;

                                if (x.getStatusCode() == 0) {
                                    statusNameMH = "SOLD";
                                } else {
                                    statusNameMH = "SELLING";
                                }
                            %>
                            <%=statusNameMH%>
                        </option>
                        <%
                            if (x.getStatusCode() == 0) {
                        %>
                        <option value="1">SELLING</option>
                        <%
                        } else {
                        %>
                        <option value="0">SOLD</option>
                        <%
                            }
                        %>
                    </select>
                </td>
                <td>
                    <input type="hidden" name="statusCode" value="<%=x.getStatusCode()%>"/>
                    <input type="hidden" name="idWay" value="<%=x.getIdWay()%>"/>
                    <input type="submit" class="site-btn btn-line" name="action" value="Update House"/>
                </td>
                <td>
                    <input type="hidden" name="statusCode" value="<%=x.getStatusCode()%>"/>
                    <input type="hidden" name="idWay" value="<%=x.getIdWay()%>"/>
                    <input type="submit" class="site-btn btn-line" name="action" value="Delete House"/>
                </td>
            </form>
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
