<%@page import="sample.dtos.PropertyDTO"%>
<%@page import="sample.dtos.DetailHouseDTO"%>
<%@page import="sample.dtos.HouseDTO"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Single Property Page </title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/flaticon.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="style/style.css" type="text/css">
        <script src="js/pop-up.js"></script>
    </head>

    <body>

        <!-- Header Section Begin -->
        <%@include file="header.jsp" %>

        <!--Show Property House-->
        <%
            HouseDTO dtoHouseSPH = (HouseDTO) request.getAttribute("HOUSEDTO");
            DetailHouseDTO dthDTOSPH = (DetailHouseDTO) request.getAttribute("DETAILHOUSE");
            HouseDTO dtoHouseSPHRelated = (HouseDTO) request.getAttribute("HOUSERELATED");
            DetailHouseDTO dthDTOSPHRelated = (DetailHouseDTO) request.getAttribute("DETAILHOUSERELATED");
            PropertyDTO propertyDTO = (PropertyDTO) request.getAttribute("PROPERTYDTO");

            String priceLowerSP = request.getParameter("priceLower");
            String priceHigherSP = request.getParameter("priceHigher");
            if (priceLowerSP == null) {
                priceLowerSP = (String) request.getAttribute("PRICELOWER");
                if (priceLowerSP == null) {
                    priceLowerSP = "1000000";
                }
            }
            if (priceHigherSP == null) {
                priceHigherSP = (String) request.getAttribute("PRICEHIGHER");
                if (priceHigherSP == null) {
                    priceHigherSP = "100000";
                }
            }
            //    String idCity = request.getParameter("idCity");
            String idCitySP = request.getParameter("idCity");
            if (idCitySP == null) {
                idCitySP = "";
                //     idCity = request.getParameter("idCity");
            }
            String idWaySP = request.getParameter("idWay");
            if (idWaySP == null) {
                idWaySP = "";
            }
            String typeIDSP = request.getParameter("typeID");
            if (typeIDSP == null) {
                typeIDSP = "";
            }
            if (dtoHouseSPH != null) {
        %>
        <!-- Single Property Section Begin -->
        <div class="single-property">
            <div class="container">
                <div class="row spad-p">
                    <div class="col-lg-12">
                        <div class="property-title">
                            <h3><%= dtoHouseSPH.getDescription()%></h3>
                            <a href="#"><i class="fa flaticon-placeholder"></i> <%=dthDTOSPH.getWayName()%>, <%=dthDTOSPH.getCityName()%></a>
                        </div>
                        <div class="property-price">
                            <p>For Sale</p>
                            <span>$<%=dtoHouseSPH.getPrice()%></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="property-img owl-carousel">
                            <div class="single-img" style="max-width: 65.5%; height: 70%;">
                                <img src="<%=dtoHouseSPH.getPicHouse()%>" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Single Property End -->
        <!-- Single Property Deatails Section Begin -->
        <section class="property-details">
            <div class="container">
                <div class="row sp-40 spt-40">
                    <div class="col-lg-8">
                        <div class="p-ins">
                            <div class="row details-top">
                                <div class="col-lg-12">
                                    <div class="t-details">
                                        <div class="register-id">
                                            <p>House ID: <span><%=dtoHouseSPH.getIdHouse()%></span></p>
                                        </div>
                                        <div class="popular-room-features single-property">
                                            <div class="size">
                                                <p>Lot Size</p>
                                                <img src="img/rooms/size.png" alt="">
                                                <i class="flaticon-bath"></i>
                                                <span><%=dtoHouseSPH.getFurniture().getLotSize()%> sqft</span>
                                            </div>
                                            <div class="beds">
                                                <p>Beds</p>
                                                <img src="img/rooms/bed.png" alt="">
                                                <span><%=dtoHouseSPH.getFurniture().getNumBed()%></span>
                                            </div>
                                            <div class="baths">
                                                <p>Baths</p>
                                                <img src="img/rooms/bath.png" alt="">
                                                <span><%=dtoHouseSPH.getFurniture().getNumBath()%></span>
                                            </div>
                                            <div class="garage">
                                                <p>Garage</p>
                                                <img src="img/rooms/garage.png" alt="">
                                                <span><%=dtoHouseSPH.getFurniture().getNumGarage()%></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <%if (propertyDTO != null) {%>
                                    <div class="property-features">
                                        <h4>Property Features</h4>
                                        <div class="property-table">
                                            <table>
                                                <tr>
                                                    <%if (propertyDTO.isMediaRoom()) {%>
                                                    <td><img src="img/check.png" alt=""> Media Room</td>
                                                        <%}%>
                                                        <%if (propertyDTO.isFamilyRoom()) {%>
                                                    <td><img src="img/check.png" alt=""> Family Room</td>
                                                        <%}%>
                                                        <%if (propertyDTO.isGymRoom()) {%>
                                                    <td><img src="img/check.png" alt="">Gym Room</td>
                                                        <%}%>
                                                </tr>
                                                <tr>
                                                    <%if (propertyDTO.isLibrary()) {%>
                                                    <td><img src="img/check.png" alt=""> Library</td>
                                                        <%}%>
                                                        <%if (propertyDTO.isPool()) {%>
                                                    <td><img src="img/check.png" alt=""> Pool</td>
                                                        <%}%>
                                                        <%if (propertyDTO.isTV()) {%>
                                                    <td><img src="img/check.png" alt=""> TV</td>
                                                        <%}%>
                                                </tr>
                                                <tr>
                                                    <%if (propertyDTO.isKitchen()) {%>
                                                    <td><img src="img/check.png" alt=""> Kitchen</td>
                                                        <%}%>
                                                        <%if (propertyDTO.isLivingRoom()) {%>
                                                    <td><img src="img/check.png" alt=""> Living Room</td>
                                                        <%}%>
                                                        <%if (propertyDTO.isGarden()) {%>
                                                    <td><img src="img/check.png" alt=""> Garden</td>
                                                        <%}%>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <%}%>
                                    <input type="hidden" name="idCity" value="<%=idCitySP%>"/>
                                    <input type="hidden" name="idWay" value="<%=idWaySP%>"/>
                                    <input type="hidden" name="typeID" value="<%=typeIDSP%>"/>
                                    <input type="hidden" name="priceLower" value="<%=priceLowerSP%>"/>
                                    <input type="hidden" name="priceHigher" value="<%=priceHigherSP%>"/>
                                    <input type="hidden" name="idHouse" value="<%=dtoHouseSPH.getIdHouse()%>"/>
                                    <%
                                        if (userDTO == null) {
                                            String currURL = "MainController?action=Add To Cart"
                                                    + "&idHouse="+dtoHouseSPH.getIdHouse()
                                                    +"&idCity="+idCitySP
                                                    +"&idWay="+idWaySP
                                                    +"&typeID="+typeIDSP;
                                                    ;
                                            session.setAttribute("POSITION", currURL);
                                    %>
                                    <input type="submit" class="site-btn btn-line" onclick="displayLogin()" name="action" value="Add To Cart"/>
                                    <%} else {%>
                                    <form action="MainController" method="POST">
                                        <input type="hidden" name="idCity" value="<%=idCitySP%>"/>
                                        <input type="hidden" name="idWay" value="<%=idWaySP%>"/>
                                        <input type="hidden" name="typeID" value="<%=typeIDSP%>"/>
                                        <input type="hidden" name="priceLower" value="<%=priceLowerSP%>"/>
                                        <input type="hidden" name="priceHigher" value="<%=priceHigherSP%>"/>
                                        <input type="hidden" name="idHouse" value="<%=dtoHouseSPH.getIdHouse()%>"/>
                                        <input type="hidden" name="price" value="<%=dtoHouseSPH.getPrice() + ""%>"/>
                                        <input type="submit" class="site-btn btn-line" name="action" value="Add To Cart"/>
                                    </form>
                                    <%}%>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="col-lg-4">
                        <div class="row pb-30">
                            Relative House
                        </div>
                        <%
                            if (dtoHouseSPHRelated != null) {
                        %>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="room-items">
                                    <form action="MainController">
                                        <div class="room-img set-bg" data-setbg="<%=dtoHouseSPHRelated.getPicHouse()%>">
                                            <a href="#" class="room-content">
                                                <i class="flaticon-heart"></i>
                                            </a>
                                        </div>
                                        <div class="room-text">
                                            <div class="room-details">
                                                <div class="room-title">
                                                    <h5>Country Style House with beautiful garden and terrace</h5>
                                                    <a href="#"><i class="fas fa-road"></i>
                                                        <span><%=dthDTOSPHRelated.getWayName()%></span>
                                                    </a>
                                                    <a href="#" class="large-width"><i class="fas fa-city"></i>
                                                        <span><%=dthDTOSPHRelated.getCityName()%></span>
                                                    </a>
                                                    <a href="#" class="large-width">
                                                        <i class="far fa-building"></i>
                                                        <span> <%=dthDTOSPHRelated.getTypeName()%></span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="room-features">
                                                <div class="room-info">
                                                    <div class="size">
                                                        <p>Lot Size</p>
                                                        <img src="img/rooms/size.png" alt="">
                                                        <i class="flaticon-bath"></i>
                                                        <span><%=dtoHouseSPHRelated.getFurniture().getLotSize()%> sqft</span>
                                                    </div>
                                                    <div class="beds">
                                                        <p>Beds</p>
                                                        <img src="img/rooms/bed.png" alt="">
                                                        <span><%=dtoHouseSPHRelated.getFurniture().getNumBed()%></span>
                                                    </div>
                                                    <div class="baths">
                                                        <p>Baths</p>
                                                        <img src="img/rooms/bath.png" alt="">
                                                        <span><%=dtoHouseSPHRelated.getFurniture().getNumBath()%></span>
                                                    </div>
                                                    <div class="garage">
                                                        <p>Garage</p>
                                                        <img src="img/rooms/garage.png" alt="">
                                                        <span><%=dtoHouseSPHRelated.getFurniture().getNumGarage()%></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="room-price">
                                                <p>For Sale</p>
                                                <span><%=dtoHouseSPHRelated.getPrice()%></span>
                                            </div>


                                            <input type="hidden" name="idCity" value="<%=idCitySP%>"/>
                                            <input type="hidden" name="idWay" value="<%=idWaySP%>"/>
                                            <input type="hidden" name="typeID" value="<%=typeIDSP%>"/>
                                            <input type="hidden" name="priceLower" value="<%=priceLowerSP%>"/>
                                            <input type="hidden" name="priceHigher" value="<%=priceHigherSP%>"/>
                                            <input type="hidden" name="idHouse" value="<%=dtoHouseSPHRelated.getIdHouse()%>"/>
                                            <input type="hidden" name="priceLower" value="<%=(String) request.getAttribute("PRICELOWER")%>"/>
                                            <input type="hidden" name="priceHigher" value="<%=(String) request.getAttribute("PRICEHIGHER")%>"/>
                                            <input type="submit" class="site-btn btn-line" name="action" value="View Property"/>

                                        </div>
                                    </form>    
                                </div>
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </section>
        <%}%>

        <%@include file="footer.html" %>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/main.js"></script>
    </body>


</html>