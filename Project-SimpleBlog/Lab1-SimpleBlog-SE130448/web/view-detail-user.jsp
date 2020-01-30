<%-- 
    Document   : ViewDetailUserController
    Created on : Jan 12, 2020, 2:38:18 PM
    Author     : sonho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail User Page</title>
        <script src="https://use.fontawesome.com/releases/v5.11.2/js/all.js" data-auto-replace-svg="nest"></script>
    </head>
    <body>

        <%@include file="header.jsp" %>

        <c:if test="${not empty requestScope.USERVIEW}">
            <c:if test="${not empty sessionScope.USERDTO}">
                <c:if test="${sessionScope.USERDTO.email == requestScope.emailUser}">
                    ${requestScope.MESSAGE}
                    <div class="profile" style="width: 100%; margin: auto; padding: auto;">
                        <div class="container">
                            <div class="row">
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="well well-sm">
                                        <div class="row">
                                            <div class="col-sm-6 col-md-4">
                                                <img src="${requestScope.USERVIEW.avatar}" alt="" class="img-rounded img-responsive" />
                                                <br>
                                                <br>
                                                upload avatar
                                                <form action="MainController" enctype="multipart/form-data" method="POST">
                                                    <input type="file" name="avatar" title="avatar" required="" accept=".jpg"/>
                                                    <input type="hidden" name="emailUser" value="${requestScope.emailUser}"/>
                                                    <input  type="submit" name="action" class="float-right btn text-white btn-danger" style="font-size: 20px;"
                                                            value="Update Profile"/>
                                                </form>
                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                <h2>
                                                    ${requestScope.USERVIEW.name}
                                                </h2>
                                                <h4>
                                                    <i class="far fa-user"></i>     ${requestScope.USERVIEW.email}
                                                </h4>
                                                <h4>
                                                    <i class="fas fa-map-marker-alt"></i>       Viet Nam
                                                </h4>
                                                <h4>
                                                    <i class="fas fa-user-tie"></i> 
                                                    <c:if test="${requestScope.USERVIEW.roleID=='ME'}">
                                                        Member
                                                    </c:if>
                                                    <c:if test="${requestScope.USERVIEW.roleID=='AD'}">
                                                        Admin
                                                    </c:if>
                                                </h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <form action="MainController">
                                <input class="float-right btn btn-outline-primary ml-2" style="font-size: 20px;" type="submit"
                                       name="action" onclick="logOut()" value="Logout"/>
                            </form>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.USERDTO.email != requestScope.emailUser}">
                    <div class="profile" style="width: 100%; margin: auto; padding: auto;">
                        <div class="container">
                            <div class="row">
                                <div class="col-xs-12 col-sm-6 col-md-6">
                                    <div class="well well-sm">
                                        <div class="row">
                                            <div class="col-sm-6 col-md-4">
                                                <img src="${requestScope.USERVIEW.avatar}" alt="" class="img-rounded img-responsive" />

                                            </div>
                                            <div class="col-sm-6 col-md-8">
                                                <h2>
                                                    ${requestScope.USERVIEW.name}
                                                </h2>
                                                <h4>
                                                    <i class="far fa-user"></i>     ${requestScope.USERVIEW.email}
                                                </h4>
                                                <h4>
                                                    <i class="fas fa-map-marker-alt"></i>       Viet Nam
                                                </h4>

                                                <h4>
                                                    <i class="fas fa-user-tie"></i> 
                                                    <c:if test="${requestScope.USERVIEW.roleID=='ME'}">
                                                        Member
                                                    </c:if>
                                                    <c:if test="${requestScope.USERVIEW.roleID=='AD'}">
                                                        Admin
                                                    </c:if>
                                                </h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${empty sessionScope.USERDTO}">
                <div class="profile" style="width: 100%; margin: auto; padding: auto;">
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="well well-sm">
                                    <div class="row">
                                        <div class="col-sm-6 col-md-4">
                                            <img src="${requestScope.USERVIEW.avatar}" alt="" class="img-rounded img-responsive" />
                                        </div>
                                        <div class="col-sm-6 col-md-8">
                                            <h2>
                                                ${requestScope.USERVIEW.name}
                                            </h2>
                                            <h4>
                                                <i class="far fa-user"></i>     ${requestScope.USERVIEW.email}
                                            </h4>
                                            <h4>
                                                <i class="fas fa-map-marker-alt"></i>       Viet Nam
                                            </h4>

                                            <h4>
                                                <i class="fas fa-user-tie"></i> 
                                                <c:if test="${requestScope.USERVIEW.roleID=='ME'}">
                                                    Member
                                                </c:if>
                                                <c:if test="${requestScope.USERVIEW.roleID=='AD'}">
                                                    Admin
                                                </c:if>
                                            </h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:if>

        <%@include file="footer.html" %>
    </body>
</html>
