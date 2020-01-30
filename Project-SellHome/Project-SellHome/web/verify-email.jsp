<%-- 
    Document   : verify-email
    Created on : Dec 18, 2019, 9:18:18 AM
    Author     : sonho
--%>

<%@page import="sample.dtos.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify email Page</title>
    </head>
    <body>
        <%
            String priceLowerSH = request.getParameter("priceLower");
            String priceHigherSH = request.getParameter("priceHigher");
            if (priceLowerSH == null) {
                priceLowerSH = (String) request.getAttribute("PRICELOWER");
                if (priceLowerSH == null) {
                    priceLowerSH = "1000000";
                }
            }
            if (priceHigherSH == null) {
                priceHigherSH = (String) request.getAttribute("PRICEHIGHER");
                if (priceHigherSH == null) {
                    priceHigherSH = "100000";
                }
            }
            String idCitySH = request.getParameter("idCity");
            String idWaySH = request.getParameter("idWay");
            String typeIDSH = request.getParameter("typeID");
            if (idCitySH == null) {
                idCitySH = (String) request.getAttribute("IDCITY");
                if (idCitySH == null) {
                    idCitySH = "";
                }
            }
            if (idWaySH == null) {
                idWaySH = (String) request.getAttribute("IDWAY");
                if (idWaySH == null) {
                    idWaySH = "";
                }
            }
            if (typeIDSH == null) {
                typeIDSH = (String) request.getAttribute("TYPEID");
                if (typeIDSH == null) {
                    typeIDSH = "";
                }
            }
            String code = (String) request.getAttribute("CODE");
            String ERRORCODE = (String) request.getAttribute("ERRORCODE");
            UserDTO dtoUserSignup = (UserDTO) request.getAttribute("USERDTOSIGNUP");
            String confirmPassword = (String) request.getAttribute("CONFIRM");
        %>
        <div style="width: 30%; height: 200px; margin: auto;">
            <form action="MainController" method="POST">
                <h3>Input code we send to you to verify email: <input type="text" name="verify" required="true"/></h3><br>
                <input type="hidden" name="code" value="<%=code%>"/>
                <input type="hidden" name="idCity" value="<%=idCitySH%>"/>
                <input type="hidden" name="idWay" value="<%=idWaySH%>"/>
                <input type="hidden" name="typeID" value="<%=typeIDSH%>"/>
                <input type="hidden" name="priceLower" value="<%=priceLowerSH%>"/>
                <input type="hidden" name="priceHigher" value="<%=priceHigherSH%>"/>
                <%
                    if (dtoUserSignup != null) {
                %>
                <input type="hidden" name="userID" value="<%=dtoUserSignup.getUserID()%>"/>
                <input type="hidden" name="userName" value="<%=dtoUserSignup.getUserName()%>"/>
                <input type="hidden" name="password" value="<%=dtoUserSignup.getPassword()%>"/>
                <input type="hidden" name="email" value="<%=dtoUserSignup.getEmail()%>"/>
                <input type="hidden" name="roleID" value="<%=dtoUserSignup.getRoleID()%>"/>
                <input type="hidden" name="confirm" value="<%=confirmPassword%>"/>
                <%}%>
                <%
                    if (ERRORCODE != null) {
                %>
                <%=ERRORCODE%>
                <%}%>
                <input type="submit" name="action" value="Verify"/>
            </form>

        </div>
    </body>
</html>
