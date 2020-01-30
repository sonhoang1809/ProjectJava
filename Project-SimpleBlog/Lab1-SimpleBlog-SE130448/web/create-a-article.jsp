<%-- 
    Document   : create-a-blog
    Created on : Jan 15, 2020, 3:43:11 PM
    Author     : sonho
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Create blog Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <c:if test="${empty sessionScope.USERDTO}">
            <c:redirect url="MainController?action=Search"/>
        </c:if>
        <c:if test="${not empty sessionScope.USERDTO}">
            <c:if test="${sessionScope.USERDTO.roleID =='AD'}">
                <c:redirect url="MainController?action=Search"/>
            </c:if>
        </c:if>    
            <%@include file="header.jsp" %>
            <div class="create-blog-form" style="width: 800px; margin: auto;">
                <form action="MainController" enctype="multipart/form-data" method="POST" >
                    <div>
                        <h3>Title:    </h3>
                        <input type="text" name="title" title="Title" style="width: 200px; font-size: 20px;" required="">
                        <span data-placeholder="Title"></span>
                    </div>
                    <div>
                        <h3> Short description: </h3>
                        <input type="text" name="shortDescription" title="Short Description" 
                               style="width: 200px; font-size: 20px;" required="">
                        <span data-placeholder="Short Description"></span>
                    </div>
                    <div>
                        <h3> Image:</h3>
                        <input type="file" name="imgFile" title="Image Article" accept=".jpg"
                               style="width: 400px; font-size: 20px;" required="">
                        <span data-placeholder="Image Article"></span>
                    </div>
                    <div>
                        <h3>  Content: </h3>
                        <textarea name="txtContentArticle" style="width: 85%;height: 150px; font-size: 13px;">
                        
                        </textarea>
                    </div>
                    <div>
                        <h3> Author: </h3>
                        <input type="text" name="author" title="Short Description" 
                               style="width: 200px; font-size: 20px;" required="true"
                               value="${sessionScope.USERDTO.name}" readonly="true">
                    </div>
                    <input  type="submit" name="action" class="float-right btn text-white btn-danger" style="font-size: 20px;"
                            value="Post Article"/>
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
