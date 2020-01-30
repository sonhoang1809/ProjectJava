<%-- 
    Document   : searchComponents
    Created on : Dec 4, 2019, 8:54:43 PM
    Author     : sonho
--%>

<%@page import="sample.dtos.TypeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.dtos.WayDTO"%>
<%@page import="sample.dtos.CityDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
              rel="stylesheet">
        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/flaticon.css" type="text/css">
        <link rel="stylesheet" href="style/style.css" type="text/css">
    </head>
    <body>
        <%
            List<CityDTO> listCity = (List<CityDTO>) session.getAttribute("LISTCITY");
            if (listCity == null) {
                request.getRequestDispatcher("MainController?action=Search&priceLower=1000000&priceHigher=100000").forward(request, response);
            }
            listCity = (List<CityDTO>) session.getAttribute("LISTCITY");
            List<WayDTO> listWay = (List<WayDTO>) session.getAttribute("LISTWAY");
            List<TypeDTO> listType = (List<TypeDTO>) session.getAttribute("LISTTYPE");
            String priceLower = request.getParameter("priceLower");
            String priceHigher = request.getParameter("priceHigher");
            if (priceLower == null) {
                priceLower = (String) request.getAttribute("PRICELOWER");
                if (priceLower == null) {
                    priceLower = "1000000";
                }
            }
            if (priceHigher == null) {
                priceHigher = (String) request.getAttribute("PRICEHIGHER");
                if (priceHigher == null) {
                    priceHigher = "100000";
                }
            }
        %>
        <!-- Filter Search Section Begin -->
        <div class="filter-search">
            <div class="container ">
                <div class="row">
                    <div class="col-lg-12">
                        <form class="filter-form" action="MainController" method="POST">   
                            <input type="hidden" name="action" value="Search"/>

                            <div class="location">
                                <p>City</p>
                                <select class="filter-location" name="idCity" id="city" onchange="this.form.submit();">
                                    <option value="">--Choose a city</option>
                                    <%
                                        if (listCity != null) {
                                            for (CityDTO x : listCity) {%>
                                    <option value="<%=x.getIdCity()%>"
                                            <%
                                                String idCity = (String) request.getAttribute("IDCITY");
                                                if (idCity != null) {
                                                    if (x.getIdCity().equalsIgnoreCase(idCity)) {
                                                        out.print("selected");
                                                    }
                                                }
                                            %>    
                                            ><%=x.getNameCity()%>
                                    </option>
                                    <%}
                                        }%>
                                </select>
                                <!-- <form> -->
                            </div>

                            <div class="location">
                                <p>Way</p>
                                <select class="filter-location" name="idWay" onchange="this.form.submit();">
                                    <option value="">--Choose a way</option>
                                    <% 
                                        if (listWay != null) {
                                            for (WayDTO x : listWay) {%>
                                    <option value="<%=x.getIdWay()%>"
                                            <%
                                                String idWay = (String) request.getAttribute("IDWAY");
                                                if (idWay != null) {
                                                    if (x.getIdWay().equalsIgnoreCase(idWay)) {
                                                        out.print("selected");
                                                    }
                                                }
                                            %>
                                            ><%= x.getWayName()%>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="search-type">
                                <p>Property Type</p>
                                <select class="filter-property" name="typeID" onchange="this.form.submit();">
                                    <option value="">--Choose a type</option>
                                    <%
                                        if (listType != null) {
                                            for (TypeDTO x : listType) {
                                    %>
                                    <option value="<%=x.getTypeID()%>"
                                            <%
                                                String typeId = (String) request.getAttribute("TYPEID");
                                                if (typeId != null) {
                                                    if (typeId.equalsIgnoreCase(x.getTypeID())) {
                                                        out.print("selected");
                                                    }
                                                }
                                            %>
                                            >
                                        <%=x.getTypeName()%>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="price-range">
                                <p>Price Lower Than</p>
                                $<input type="number" name="priceLower" size="9" step="10000" min='500000' max="1000000" required="true"
                                        value="<%=priceLower%>" style="width: 90%;"/>
                            </div>
                            <div class="price-range">
                                <p>Price Higher Than</p>
                                $<input type="number" name="priceHigher" size="9" step="10000" min='100000' max="500000" required="true"
                                        value="<%=priceHigher%>" style="width: 90%;"/>
                            </div>
                            <div class="search-btn">
                                <button type="submit" name="action" value="Search"><i class="flaticon-search"></i>Search</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Filter Search Section End -->
    </body>
</html>
