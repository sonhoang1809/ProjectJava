<%@page import="sample.dtos.HouseDTO"%>
<%@page import="sample.dtos.DetailHouseDTO"%>
<%@page import="sample.dtos.PropertyDTO"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Login Page</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style/styleLogin.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/js/all.js" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="UTF-8"></script>
        <script src="https://kit.fontawesome.com/de96c8b80a.js" crossorigin="anonymous"></script>

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


        %>
        <form action="MainController" class="login-form" method="POST">
            <h1>Login</h1>
            <div class="txtb">
                <input type="text" name="userID" required="true">
                <span data-placeholder="UserID"></span>
            </div>
            <div class="txtb">
                <input type="password" name="password" required="true">
                <span data-placeholder="Password"></span>
            </div>
            <div class="btnForgot">
                <a href="#">Forgot password?</a>
            </div>
            <input type="hidden" name="idCity" value="<%=idCitySH%>"/>
            <input type="hidden" name="idWay" value="<%=idWaySH%>"/>
            <input type="hidden" name="typeID" value="<%=typeIDSH%>"/>
            <input type="hidden" name="priceLower" value="<%=priceLowerSH%>"/>
            <input type="hidden" name="priceHigher" value="<%=priceHigherSH%>"/>
            <input type="hidden" name="idHouse" value="<%=request.getParameter("idHouse")%>"/>
            <input type="hidden" name="price" value="<%=request.getParameter("price")%>"/>
            <input type="submit" class="logbtn" name="action" value="Login">

            <div class="middle">
                <p>
                    Or Login With
                </p>
                <a class="btnLoginBy" href="#">
                    <i class="fab fa-facebook-f"></i>
                </a>
                <a class="btnLoginBy" href="#">
                    <i class="fab fa-twitter"></i>
                </a>
                <a class="btnLoginBy" href="#">
                    <i class="fab fa-google"></i>
                </a>
                <a class="btnLoginBy" href="#">
                    <i class="fab fa-instagram"></i>
                </a>
            </div>
            <div class="bottom-text">
                Don't have account ? 
                <%
                    String urlSignup = "MainController?action=Go to signup"
                            + "&idCity=" + idCitySH
                            + "&idWay=" + idWaySH
                            + "&typeID=" + typeIDSH
                            + "&priceLower=" + priceLowerSH
                            + "&priceHigher=" + priceHigherSH;
                %>

                <a href="<%=urlSignup%>">Sign up</a>
                <br>
                <br>
                <a href="MainController?action=Search">Back to Home Page</a> <br>

            </div>
        </form>

        <script>
            $(".txtb input").on("focus", function () {
                $(this).addClass("focus");
            });
            $(".txtb input").on("blur", function () {
                if ($(this).val() == "")
                    $(this).removeClass("focus");
            });
        </script>


    </body>

</html>