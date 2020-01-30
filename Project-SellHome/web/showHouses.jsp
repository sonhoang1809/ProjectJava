<%-- 
    Document   : showHouses
    Created on : Dec 4, 2019, 9:21:03 PM
    Author     : sonho
--%>

<%@page import="sample.dtos.UserDTO"%>
<%@page import="java.util.Map"%>
<%@page import="sample.dtos.DetailHouseDTO"%>
<%@page import="sample.daos.TypeDAO"%>
<%@page import="sample.daos.WayDAO"%>
<%@page import="sample.dtos.HouseDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
            List<HouseDTO> listHouse = (List<HouseDTO>) request.getAttribute("LISTHOUSE");
//            if (listHouse == null) {
//                request.getRequestDispatcher("MainController?action=Search&priceLower=1000000&priceHigher=100000").forward(request, response);
//            }
//            listHouse = (List<HouseDTO>) request.getAttribute("LISTHOUSE");
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
            String idCitySH = (String) request.getAttribute("IDCITY");
            String idWaySH = (String) request.getAttribute("IDWAY");
            String typeIDSH = (String) request.getAttribute("TYPEID");

            Map<String, DetailHouseDTO> listDTHouse = (Map<String, DetailHouseDTO>) request.getAttribute("LISTHOUSEDETAIL");
        %>
        <section class="hotel-rooms spad">
            <div class="container">
                <div class="row">
                    <%
                        if (listHouse != null) {
                            for (HouseDTO dtoHouse : listHouse) {
                    %>
                    <div class="col-lg-4 col-md-6">
                        <div class="room-items">
                            <form action="MainController" method="GET">
                                <div class="room-img set-bg" data-setbg="">
                                    <img src="<%=dtoHouse.getPicHouse()%>" style="width: 100%; height: 100%;"/>
                                    <a href="#" class="room-content">
                                        <i class="flaticon-heart"></i>
                                    </a>
                                </div>
                                <div class="room-text">
                                    <div class="room-details">
                                        <div class="room-title">
                                            <h5><%=dtoHouse.getDescription()%></h5>
                                            <a href="#"><i class="fas fa-road"></i>
                                                <span><%=listDTHouse.get(dtoHouse.getIdHouse()).getWayName()%></span>
                                            </a>
                                            <a href="#" class="large-width">
                                                <i class="fas fa-city"></i>
                                                <span><%=listDTHouse.get(dtoHouse.getIdHouse()).getCityName()%></span>
                                            </a>
                                            <a href="#" class="large-width">
                                                <i class="far fa-building"></i>
                                                <span> <%=listDTHouse.get(dtoHouse.getIdHouse()).getTypeName()%></span>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="room-features">
                                        <div class="room-info">
                                            <div class="size">
                                                <p>Lot Size</p>
                                                <img src="img/rooms/size.png" alt=""/>
                                                <i class="flaticon-bath"></i>
                                                <span><%=dtoHouse.getFurniture().getLotSize() + ""%></span>
                                            </div>
                                            <div class="beds">
                                                <p>Beds</p>
                                                <img src="img/rooms/bed.png" alt=""/>
                                                <span><%=dtoHouse.getFurniture().getNumBed() + ""%></span>
                                            </div>
                                            <div class="baths">
                                                <p>Baths</p>
                                                <img src="img/rooms/bath.png" alt=""/>
                                                <span><%=dtoHouse.getFurniture().getNumBath() + ""%></span>
                                            </div>
                                            <div class="garage">
                                                <p>Garage</p>
                                                <img src="img/rooms/garage.png" alt=""/>
                                                <span><%=dtoHouse.getFurniture().getNumGarage() + ""%></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="room-price">
                                        <%
                                            if (dtoHouse.getStatusCode() == 1) {
                                        %>
                                        <p>Selling</p>
                                        <%} else {%>
                                        <p>Sold</p>
                                        <%}%>
                                        <span>$<%=dtoHouse.getPrice() + ""%></span>
                                    </div>
                                    <input type="hidden" name="idCity" value="<%=idCitySH%>"/>
                                    <input type="hidden" name="idWay" value="<%=idWaySH%>"/>
                                    <input type="hidden" name="typeID" value="<%=typeIDSH%>"/>
                                    <input type="hidden" name="priceLower" value="<%=priceLowerSH%>"/>
                                    <input type="hidden" name="priceHigher" value="<%=priceHigherSH%>"/>
                                    <input type="hidden" name="idHouse" value="<%=dtoHouse.getIdHouse()%>"/>
                                    <input type="submit" class="site-btn btn-line" name="action" value="View Property"/>
                                </div>
                            </form>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                    <%
                        UserDTO userDTOsH = (UserDTO) session.getAttribute("USERDTO");
                        if (userDTOsH != null) {
                            if (userDTOsH.getRoleID().equalsIgnoreCase("AD")) {
                    %>
                    <form action="MainController">
                        <input type="submit" class="site-btn btn-line" id="InsertNewHouse" name="action" value="Insert New House"/>
                    </form>

                    <%
                            }
                        }


                    %>

                </div>
            </div>
        </section>
    </body>
</html>
