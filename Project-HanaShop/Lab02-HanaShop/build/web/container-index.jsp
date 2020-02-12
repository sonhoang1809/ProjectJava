<%-- 
    Document   : container-index
    Created on : Jan 31, 2020, 11:19:56 PM
    Author     : sonho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Container</title>
        <script src="js/page.js"></script>
        <script src="js/pop-up.js"></script>
    </head>
    <body>
        <!-- Food Area starts -->
        <section class="food-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-md-5">
                        <div class="section-top">
                            <h3><span class="style-change">We serve</span> <br>delicious food</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    ${requestScope.SEARCHNULL}
                    <c:forEach items="${requestScope.LISTFOOD}" var="x">
                        <div class="col-md-4 col-sm-6">
                            <div class="single-food">
                                <div class="food-img" style="w">
                                    <img src="${x.imgFood}" class="img-fluid" alt="">
                                </div>
                                <div class="food-content" >
                                    <div class="d-flex justify-content-between">
                                        <h5>${x.nameFood}</h5>
                                        <span class="style-change">$${x.price}</span>
                                    </div>
                                    Kind: ${x.kind} <br>
                                    Category: ${x.categoryName}
                                    <p class="pt-3">${x.shortDescription}</p>

                                    <div style="display: inline-block; font-size: 17px;">
                                        <form style="float: left;">
                                            <input type="submit" class="genric-btn primary" name="action" value="View Details">
                                        </form>
                                        <form action="AddAFoodToCartController" style="float: right;margin-left: 10px;" method="POST">
                                            <input type="hidden" name="idFood" value="${x.idFood}">
                                            <input type="hidden" name="kindID" value="${param.kindID}">
                                            <input type="hidden" name="categoryID" value="${param.categoryID}">
                                            <input type="hidden" name="priceLower" value="${param.priceLower}">
                                            <input type="hidden" name="priceHigher" value="${param.priceHigher}">
                                            <input type="hidden" name="searchName" value="${param.searchName}">
                                            <input type="submit" id="myAnchor" style="" class="genric-btn danger" onclick="checkLoginYet(); " name="action" value="Add to cart">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <script>
            function checkLoginYet() {
            <c:if test="${empty sessionScope.USERDTO}">
                displayLogin();
                event.preventDefault();
            </c:if>
            <c:if test="${not empty sessionScope.USERDTO}">
                <c:if test="${sessionScope.USERDTO.roleID == 'US'}">
                $(this).closest('form').submit();
                </c:if>
            </c:if>
            }
        </script>
        <!-- Food Area End -->
        <%@include  file="paging.jsp" %>

        <!-- Reservation Area Starts -->
        <div class="reservation-area section-padding text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Natural ingredients and testy food</h2>
                        <h4 class="mt-4">some trendy and popular courses offerd</h4>
                        <a href="" class="template-btn template-btn2 mt-4">reservation</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Reservation Area End -->

        <!-- Deshes Area Starts -->
        <div class="deshes-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-top2 text-center">
                            <h3>Our <span>special</span> deshes</h3>
                            <p><i>Beast kind form divide night above let moveth bearing darkness.</i></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-5 col-md-6 align-self-center">
                        <h1>01.</h1>
                        <div class="deshes-text">
                            <h3><span>Garlic</span><br> green beans</h3>
                            <p class="pt-3">Be. Seed saying our signs beginning face give spirit own beast darkness morning moveth green multiply she'd kind saying one shall, two which darkness have day image god their night. his subdue so you rule can.</p>
                            <span class="style-change">$12.00</span>
                            <a href="#" class="template-btn3 mt-3">book a table <span><i class="fa fa-long-arrow-right"></i></span></a>
                        </div>
                    </div>
                    <div class="col-lg-5 offset-lg-2 col-md-6 align-self-center mt-4 mt-md-0">
                        <img src="assets/images/deshes1.png" alt="" class="img-fluid">
                    </div>
                </div>
                <div class="row mt-5">
                    <div class="col-lg-5 col-md-6 align-self-center order-2 order-md-1 mt-4 mt-md-0">
                        <img src="assets/images/deshes2.png" alt="" class="img-fluid">
                    </div>
                    <div class="col-lg-5 offset-lg-2 col-md-6 align-self-center order-1 order-md-2">
                        <h1>02.</h1>
                        <div class="deshes-text">
                            <h3><span>Lemon</span><br> rosemary chicken</h3>
                            <p class="pt-3">Be. Seed saying our signs beginning face give spirit own beast darkness morning moveth green multiply she'd kind saying one shall, two which darkness have day image god their night. his subdue so you rule can.</p>
                            <span class="style-change">$12.00</span>
                            <a href="#" class="template-btn3 mt-3">book a table <span><i class="fa fa-long-arrow-right"></i></span></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Deshes Area End -->

        <!-- Testimonial Area Starts -->
        <section class="testimonial-area section-padding4">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-top2 text-center">
                            <h3>Customer <span>says</span></h3>
                            <p><i>Beast kind form divide night above let moveth bearing darkness.</i></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="testimonial-slider owl-carousel">
                            <div class="single-slide d-sm-flex">
                                <div class="customer-img mr-4 mb-4 mb-sm-0">
                                    <img src="assets/images/customer1.png" alt="">
                                </div>
                                <div class="customer-text">
                                    <h5>adame nesane</h5>
                                    <span><i>Chief Customer</i></span>
                                    <p class="pt-3">
                                        You're had. Subdue grass Meat us winged years you'll doesn't. fruit two also won one yielding creepeth third giv
                                        e may never lie alternet food.
                                    </p>

                                </div>
                            </div>
                            <div class="single-slide d-sm-flex">
                                <div class="customer-img mr-4 mb-4 mb-sm-0">
                                    <img src="assets/images/customer2.png" alt="">
                                </div>
                                <div class="customer-text">
                                    <h5>adam nahan</h5>
                                    <span><i>Chief Customer</i></span>
                                    <p class="pt-3">You're had. Subdue grass Meat us winged years you'll doesn't. fruit two also won one yielding creepeth third give may never lie alternet food.</p>
                                </div>
                            </div>
                            <div class="single-slide d-sm-flex">
                                <div class="customer-img mr-4 mb-4 mb-sm-0">
                                    <img src="assets/images/customer1.png" alt="">
                                </div>
                                <div class="customer-text">
                                    <h5>Lalana</h5>
                                    <span><i>Chief Customer</i></span>
                                    <p class="pt-3">You're had. Subdue grass Meat us winged years you'll doesn't. fruit two also won one yielding creepeth third give may never lie alternet food.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Testimonial Area End -->

        <!-- Update Area Starts -->
        <section class="update-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-top2 text-center">
                            <h3>Our <span>food</span> update</h3>
                            <p><i>Beast kind form divide night above let moveth bearing darkness.</i></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="single-food">
                            <div class="food-img">
                                <img src="assets/images/update1.jpg" class="img-fluid" alt="">
                            </div>
                            <div class="food-content">
                                <div class="post-admin d-lg-flex mb-3">
                                    <span class="mr-5 d-block mb-2 mb-lg-0"><i class="fa fa-user-o mr-2"></i>Admin</span>
                                    <span><i class="fa fa-calendar-o mr-2"></i>18/09/2018</span>
                                </div>
                                <h5>no finer food can be found</h5>
                                <p>nancy boy off his nut so I said chimney pot be James Bond aking cakes he.</p>
                                <a href="#" class="template-btn3 mt-2">read more <span><i class="fa fa-long-arrow-right"></i></span></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-food my-5 my-md-0">
                            <div class="food-img">
                                <img src="assets/images/update2.jpg" class="img-fluid" alt="">
                            </div>
                            <div class="food-content">
                                <div class="post-admin d-lg-flex mb-3">
                                    <span class="mr-5 d-block mb-2 mb-lg-0"><i class="fa fa-user-o mr-2"></i>Admin</span>
                                    <span><i class="fa fa-calendar-o mr-2"></i>20/09/2018</span>
                                </div>
                                <h5>things go better with food</h5>
                                <p>nancy boy off his nut so I said chimney pot be James Bond aking cakes he.</p>
                                <a href="#" class="template-btn3 mt-2">read more <span><i class="fa fa-long-arrow-right"></i></span></a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-food">
                            <div class="food-img">
                                <img src="assets/images/update3.jpg" class="img-fluid" alt="">
                            </div>
                            <div class="food-content">
                                <div class="post-admin d-lg-flex mb-3">
                                    <span class="mr-5 d-block mb-2 mb-lg-0"><i class="fa fa-user-o mr-2"></i>Admin</span>
                                    <span><i class="fa fa-calendar-o mr-2"></i>22/09/2018</span>
                                </div>
                                <h5>food head above the rest</h5>
                                <p>nancy boy off his nut so I said chimney pot be James Bond aking cakes he.</p>
                                <a href="#" class="template-btn3 mt-2">read more <span><i class="fa fa-long-arrow-right"></i></span></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Update Area End -->

        <!-- Table Area Starts -->
        <section class="table-area section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-top2 text-center">
                            <h3>Book <span>your</span> table</h3>
                            <p><i>Beast kind form divide night above let moveth bearing darkness.</i></p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8 offset-lg-2">
                        <form action="#">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                                </div>
                                <input type="text" id="datepicker">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-clock-o"></i></span>
                                </div>
                                <input type="text" id="datepicker2">
                            </div>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fa fa-user-o"></i></span>
                                </div>
                                <input type="text">
                            </div>
                            <div class="table-btn text-center">
                                <a href="#" class="template-btn template-btn2 mt-4">book a table</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <!-- Table Area End -->
    </body>
</html>
