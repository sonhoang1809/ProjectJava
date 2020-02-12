<%-- 
    Document   : shopping-cart-hover
    Created on : Feb 10, 2020, 11:35:38 PM
    Author     : sonho
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style/style-shopping-cart-hover.css"/>
    </head>
    <body>
        <div>
            <header id="site-header">
                <div class="container">
                    <h3>Your shopping cart</h3>
                </div>
            </header>
            <div class="container">
                <section id="cart">
                    <c:if test="${empty sessionScope.LISTFOODINBILL}">
                        <h5>No products!</h5>
                    </c:if>
                    <c:forEach items="${sessionScope.LISTFOODINBILL}" var="x">
                        <article class="product">
                            <header>
                                <img src="${x.imgFood}" alt="">
                            </header>
                            <div class="content">
                                <h5>${x.nameFood}</h5>
                                ${x.shortDescription}
                            </div>
                            <footer class="content">
                                <span class="qt">Quantity: ${x.quantity}</span>
                                <c:if test="${x.statusFoodID==0}">
                                    <span class="qt">Status: Out of Stock</span>
                                </c:if>
                                <h5 class="full-price">
                                    ${x.total}$
                                </h5>
                                <h5 class="price">
                                    ${x.price}$
                                </h5>
                            </footer>
                        </article>
                    </c:forEach>
                </section>
            </div>
            <footer id="site-footer">
                <div class="container clearfix">

                    <div class="left">
                        <h5 class="subtotal">Subtotal: <span>${sessionScope.BILLDTO.total}</span>$</h5>
                        <h5 class="tax">Taxes (10%): <span>${sessionScope.BILLDTO.total/10}</span>$</h5>
                        <h5 class="shipping">Shipping: <span>5.00</span>$</h5>
                    </div>

                    <div class="right">
                        <h3 class="total">Total: <span>${sessionScope.BILLDTO.total *1.1+5}</span>$</h3>
                        <a class="btn">Checkout</a>
                    </div>

                </div>
            </footer>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="js/shopping-cart.js"></script>
    </body>
</html>
