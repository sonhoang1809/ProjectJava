<%-- 
    Document   : container-index
    Created on : Jan 13, 2020, 4:03:24 PM
    Author     : sonho
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>container</title>
        
    </head>
    <body>
        <c:if test="${empty requestScope.LISTARTICLE && empty requestScope.SEARCHNULL}">
            <c:redirect url="MainController?action=Search"/>
        </c:if>

        <p style="margin: auto; font-size: 30px;">
            ${requestScope.MESSAGE}
        </p>
        <br>
        <br>
        <c:if test="${sessionScope.USERDTO.roleID=='ME'}">
            <form action="MainController" >
                <input class="float-right btn btn-outline-primary ml-2" style="font-size: 20px;" type="submit"
                       name="action" value="Create a article"/>
            </form>
        </c:if>
        <br>
        <br>
        <div class="container" style="margin-top: 20px;">
            <p>
                ${requestScope.SEARCHNULL}
            </p>
            <c:forEach items="${requestScope.LISTARTICLE}" var="art">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-2">
                                <img src="${art.avatarUserPost}" style="width: 100px; height: 100px; border-radius: 50px;" class="img img-rounded img-fluid"/>
                            </div>
                            <div class="col-md-10">
                                <h4>    
                                    <a class="float-left" href="MainController?action=ViewDetailUser&emailUser=${art.email}">
                                        <strong>${art.author}</strong>
                                    </a>
                                </h4>
                                <br>
                                <br>
                                <h5> <span>${art.postingDate}</span> </h5>
                                <div class="clearfix"></div>
                                <h2>${art.title}</h2>
                                <img src="${art.img}" />
                                <p style="font-size: 20px;">
                                    ${art.shortDescription}
                                </p>
                                <form action="MainController" method="POST">
                                    <input type="hidden" name="emailUserArticle" value="${art.email}"/>
                                    <input type="hidden" name="articleID" value="${art.articleID}"/>
                                    <input type="hidden" name="searchTitle" value="${param.searchTitle}"/>
                                    <input type="hidden" name="statusArticleID" value="${param.statusArticleID}"/>
                                    <input class="float-right btn btn-outline-primary ml-2" style="font-size: 20px;" type="submit"
                                           name="action" value="Read more"/>
                                </form>
                                <c:if test="${not empty sessionScope.USERDTO}">
                                    <c:if test="${sessionScope.USERDTO.roleID == 'AD'}">
                                        <c:if test="${art.statusArticle == 0}">
                                            <form action="MainController" method="POST">
                                                <input type="hidden" name="emailUserArticle" value="${art.email}"/>
                                                <input type="hidden" name="articleID" value="${art.articleID}"/>
                                                <input type="hidden" name="searchTitle" value="${param.searchTitle}"/>
                                                <input type="hidden" name="statusArticleID" value="${param.statusArticleID}"/>
                                                <input class="float-right btn btn-outline-primary ml-2" style="font-size: 20px;" type="submit"
                                                       name="action" value="Active Article"/>
                                            </form>
                                        </c:if>
                                        <c:if test="${art.statusArticle != 2}">
                                            <form>
                                                <input type="hidden" name="emailUserArticle" value="${art.email}"/>
                                                <input type="hidden" name="articleID" value="${art.articleID}"/>
                                                <input type="hidden" name="searchTitle" value="${param.searchTitle}"/>
                                                <input type="hidden" name="statusArticleID" value="${param.statusArticleID}"/>
                                                <input class="float-right btn text-white btn-danger" style="font-size: 20px;"
                                                       type="submit" name="action" value="Delete Article"/>
                                            </form>
                                        </c:if>
                                    </c:if>
                                </c:if>


                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="page-num" style="padding: auto;"> 
            <c:if test="${requestScope.NUMBEROFPAGE>1}">
                <c:forEach begin="1" end="${requestScope.NUMBEROFPAGE}"  var="numPage">
                    <form action="MainController?action=Search">
                        <input type="hidden" name="searchTitle" value="${param.searchTitle}" class="searchTerm">
                        <input type="hidden" name="statusArticleID" value="${requestScope.ARTICLESTATUSID}"/>
                        <input type="hidden" name="numPage" value="${numPage}"/>
                        <input class="btn-num-page" style="font-size: 20px; float: left;" type="submit"
                               name="action" value="${numPage}" />
                    </form>
                </c:forEach>
            </c:if>
        </div>



    </body>


</body>
</html>
