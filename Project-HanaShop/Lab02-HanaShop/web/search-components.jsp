<%-- 
    Document   : search-components
    Created on : Jan 31, 2020, 11:42:26 PM
    Author     : sonho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link rel="stylesheet" href="style/style-search.css" type="text/css">
        <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
       
    </head>
    <body>
        <!-- Filter Search Section Begin -->
        <div class="filter-search">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <form class="filter-form" action="SearchController" method="POST">
                            <div class="search-type">
                                <p>Kind of Food</p>
                                <select class="filter-property" name="kindID" onchange="this.form.submit();">
                                    <option value="">Choose one</option>
                                    <c:forEach items="${requestScope.LISTKIND}" var="x">
                                        <option value="${x.kindID}"
                                                <c:if test="${not empty requestScope.KINDID}">
                                                    <c:if test="${requestScope.KINDID == x.kindID}">
                                                        selected=""
                                                    </c:if>
                                                </c:if>             
                                                >${x.kindFood}
                                        </option>
                                    </c:forEach> 
                                </select>
                            </div>
                            <div class="search-type">
                                <p>Category</p>
                                <select class="filter-property" name="categoryID" onchange="this.form.submit();">
                                    <option value="">Choose one</option>
                                    <c:forEach items="${requestScope.LISTCATEGORIES}" var="x">
                                        <option value="${x.categoryID}"
                                                <c:if test="${not empty requestScope.CATEGORYID}">
                                                    <c:if test="${requestScope.CATEGORYID == x.categoryID}">
                                                        selected=""
                                                    </c:if>
                                                </c:if>
                                                >${x.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="price-range">
                                <p>Price Lower Than</p>
                                <c:if test="${empty param.priceLower}">
                                    $<input type="number" name="priceLower" size="9" step="0.5" min='10' max="100" required="true" value="100"
                                            style="width: 90%;"/>
                                </c:if>
                                <c:if test="${not empty param.priceLower}">
                                    $<input type="number" name="priceLower" size="9" step="0.5" min='10' max="100" required="true" value="${param.priceLower}"
                                            style="width: 90%;"/>
                                </c:if>
                            </div>
                            <div class="price-range">
                                <p>Price Higher Than</p>
                                <c:if test="${empty param.priceHigher}">
                                    $<input type="number" name="priceHigher" size="9" step="0.5" min='1' max="10" required="true" value="1"
                                            style="width: 90%;"/>
                                </c:if>
                                <c:if test="${not empty param.priceHigher}">
                                    $<input type="number" name="priceHigher" size="9" step="0.5" min='1' max="10" required="true" value="${param.priceHigher}"
                                            style="width: 90%;"/>
                                </c:if>
                            </div>
                            <div class="search-name">
                                <p>Search name</p>
                                <div class="search">
                                    <input type="text" name="searchName" value="${param.searchName}" class="searchTerm" placeholder="What food you looking for ?">
                                </div>
                            </div>

                            <input type="submit" class="genric-btn success" id="search-btn" name="action" value="Search">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Filter Search Section End -->
    </body>
</html>
