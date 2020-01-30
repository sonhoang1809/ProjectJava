<%-- 
    Document   : single-post-detail
    Created on : Jan 12, 2020, 11:23:02 PM
    Author     : sonho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Single Article Page</title>
    </head>
    <body>

        <%@include file="header.jsp" %>

        <c:catch var="error">
            ${requestScope.MESSAGE}

            <c:if test="${not empty requestScope.ARTICLEDTO}">
                <div class="container" style="margin-top: 20px;">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-2">
                                    <img src="${requestScope.ARTICLEDTO.avatarUserPost}"  style="width: 120px; height: 120px; border-radius: 60px;" class="img img-rounded img-fluid"/>
                                </div>
                                <div class="col-md-10">
                                    <h4>    
                                        <a class="float-left" href="#">
                                            <strong>${requestScope.ARTICLEDTO.author}</strong>
                                        </a>
                                    </h4>
                                    <br>
                                    <br>
                                    <h5> <span>${requestScope.ARTICLEDTO.postingDate}</span> </h5>
                                    <div class="clearfix"></div>
                                    <br>
                                    <br>
                                    <br>

                                    <h2>${requestScope.ARTICLEDTO.title}</h2>
                                    <img src="${requestScope.ARTICLEDTO.img}" />
                                    <h4>
                                        ${requestScope.ARTICLEDTO.shortDescription}
                                    </h4>
                                    <p>
                                        ${requestScope.ARTICLEDTO.postContent}
                                    </p>
                                    <c:if test="${empty sessionScope.USERDTO}">
                                        <a class="float-right btn btn-outline-primary ml-2" onclick="checkLoginYet()" style="font-size: 20px;"> 
                                            <i class="fa fa-reply"></i> Comment
                                        </a>
                                    </c:if>


                                    <c:if test="${not empty sessionScope.USERDTO}">
                                        <c:if test="${sessionScope.USERDTO.roleID!='AD'}">
                                            <a class="float-right btn btn-outline-primary ml-2" onclick="checkLoginYet()" style="font-size: 20px;"> 
                                                <i class="fa fa-reply"></i> Comment
                                            </a>
                                        </c:if>
                                        <c:if test="${sessionScope.USERDTO.roleID=='AD'}">
                                            <c:if test="${requestScope.ARTICLEDTO.statusArticle != 2}">
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="emailUserArticle" value="${requestScope.ARTICLEDTO.email}"/>
                                                    <input type="hidden" name="articleID" value="${requestScope.ARTICLEDTO.articleID}"/>
                                                    <input type="hidden" name="searchTitle" value="${param.searchTitle}"/>
                                                    <input type="hidden" name="statusArticleID" value="${param.statusArticleID}"/>
                                                    <input class="float-right btn text-white btn-danger" style="font-size: 20px;"
                                                           type="submit" name="action" value="Delete Article"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${requestScope.ARTICLEDTO.statusArticle == 0}">
                                                <form action="MainController" method="POST">
                                                    <input type="hidden" name="emailUserArticle" value="${requestScope.ARTICLEDTO.email}"/>
                                                    <input type="hidden" name="articleID" value="${requestScope.ARTICLEDTO.articleID}"/>
                                                    <input type="hidden" name="searchTitle" value="${param.searchTitle}"/>
                                                    <input type="hidden" name="statusArticleID" value="${param.statusArticleID}"/>
                                                    <input class="float-right btn btn-outline-primary ml-2" style="font-size: 20px;"
                                                           type="submit" name="action" value="Active Article"/>
                                                </form>
                                            </c:if>
                                        </c:if>
                                    </c:if>

                                </div>
                            </div>
                            <div style="width: 100%; border: 1px solid; margin: 5px;">

                            </div>
                            <c:if test="${not empty sessionScope.USERDTO}">
                                <c:if test="${sessionScope.USERDTO.roleID != 'AD'}">
                                    <div class="card card-inner" id="user-comment" style="margin-left: 80px; display: none;">
                                        <form action="MainController" method="POST">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-md-2">
                                                        <img src="${sessionScope.USERDTO.avatar}" class="img img-rounded img-fluid" style="width: 80px; height: 80px; border-radius: 40px;"/>
                                                    </div>
                                                    <div class="col-md-10">
                                                        <p>
                                                            <a href="#" style="font-size: 15px;">
                                                                <strong>${sessionScope.USERDTO.name}</strong>
                                                            </a>
                                                        </p>
                                                        <textarea name="txtCommentUser" id="text-user-comment" style="width: 85%;height: 70%; font-size: 13px;">
                                                    
                                                        </textarea>
                                                        <input type="hidden" name="articleID" value="${requestScope.ARTICLEDTO.articleID}"/>
                                                        <input type="hidden" name="emailUserArticle" value="${requestScope.ARTICLEDTO.email}"/>
                                                        <input class="float-right btn text-white btn-danger" style="font-size: 12px;"
                                                               type="submit" name="action" value="Post Comment"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${not empty requestScope.LISTCOMMENT}">
                                <c:forEach items="${requestScope.LISTCOMMENT}" var="cmt">
                                    <div class="card card-inner" style="margin-left: 80px;">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-2">
                                                    <img src="${cmt.avatarUserComment}" class="img img-rounded img-fluid"  style="width: 80px; height: 80px; border-radius: 40px;"/>
                                                </div>
                                                <div class="col-md-10" style="margin-left: -4%;">
                                                    <p><a href="#"><strong>${cmt.author}</strong></a></p>
                                                    <p>
                                                        ${cmt.content}
                                                    </p>
                                                    <p class="text-secondary text-center" style="float: right;">${cmt.dateComment}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
                <c:set scope="session" var="POSITION" value="MainController?action=Read more&emailUserArticle=${requestScope.ARTICLEDTO.email}&articleID=${requestScope.ARTICLEDTO.articleID}"/>
            </c:if>

        </c:catch>
        <c:if test="${error!=null}">
            <h1>
                ${error}
            </h1>
        </c:if>
    </body>
    <script>
        function checkLoginYet() {
        <c:if test="${empty sessionScope.USERDTO}">
            displayLogin();
        </c:if>
        <c:if test="${not empty sessionScope.USERDTO}">
            displayUserCommentBox();
        </c:if>
        }
    </script>
    <%@include file="footer.html" %>
</body>
</html>
