

<%@page import="java.util.List"%>
<%@page import="sample.dtos.UserDTO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
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

        <script src="https://use.fontawesome.com/releases/v5.11.2/js/all.js" data-auto-replace-svg="nest"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <script src="js/pop-up.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

        <!--google api-->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>

        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id"
              content="1024671952377-00c5knld88233as98hk51acafnip2j86.apps.googleusercontent.com">

        <!--facebook api-->
    <div id="fb-root"></div>

    <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v5.0&appId=120134942650484&autoLogAppEvents=1"></script>

    <script src="js/login-signup.js"></script>
</head>
<body>

    <!-- Page Preloder -->

    <!-- Header Section Begin -->

    <header class="header-section">

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div class="logo">
                        <a href="MainController?action=Search">
                            <h3>Simple Blog</h3>
                        </a>
                    </div>
                    <ul class="main-menu">
                        <li><a href="MainController?action=Search">Home</a></li>
                            <c:if test="${empty sessionScope.USERDTO}">
                            <li><a onclick="displaySignup()" href="#">Sign up</a></li>
                            <li><a onclick="displayLogin()" href="#">Login</a></li>
                            </c:if>
                            <c:if test="${not empty sessionScope.USERDTO}">
                                <c:if test="${sessionScope.USERDTO.roleID=='AD'}">
                                <li> <a href="MainController?action=ViewDetailUser&emailUser=${sessionScope.USERDTO.email}">Welcome admin ${sessionScope.USERDTO.name}</a></li>
                                </c:if>
                                <c:if test="${sessionScope.USERDTO.roleID=='ME'}">
                                <li> <a href="MainController?action=ViewDetailUser&emailUser=${sessionScope.USERDTO.email}">Welcome ${sessionScope.USERDTO.name} !!</a></li>
                                </c:if>

                        </c:if>

                    </ul>

                    <div id="mobile-menu-wrap"></div>
                </div>
            </div>
        </div>
    </header>

    <div id="login-form" class="login-form" style="display: none;">
        <button class="close-form" onclick="closeLogin()" >x</button> <br>
        <form action="MainController" method="POST">
            <h1>Login</h1>
            <div class="txtb">
                <c:out value="${requestScope.LOGINERROR.errorExistEmail}"/>
                <input type="text" name="email" required="" value="${param.email}">
                <span data-placeholder="Email"></span>
            </div>
            <div class="txtb">
                <c:out value="${requestScope.LOGINERROR.errorPassword}"/>
                <input type="password" name="password" required="" value="${param.password}">
                <span data-placeholder="Password"></span>
            </div>

            <input type="hidden" name="searchTitle" value="${param.searchTitle}"/>
            <input type="submit" class="logbtn" name="action" value="Login" style="width: 100%; "/>
            <div class="middle">
                <p>
                    Or Login With
                </p>

                <div class="fb-login-button" data-width="280px" data-size="large" data-button-type="login_with" data-auto-logout-link="false" data-use-continue-as="false"></div>
                
                <div style="height: 10px;">
                    
                </div>
                <div class="g-signin2" data-width="280%" data-height="50%" data-longtitle="true" data-onsuccess="onSignIn" ></div>

            </div>
            <div class="bottom-text">
                Don't have account ?
                <a onclick="fromLoginToSignup()" href="#">Sign up</a>
                <br>
                <br>
            </div>
        </form>
    </div>

    <div id="signup-form" class="signup-form" style="display: none;">
        <button class="close-form" onclick="closeSignup()">x</button> <br>
        <form action="MainController" method="POST">
            <h1>Sign Up</h1>
            <div class="txtb">
                ${requestScope.SIGNUPERROR.errorEmail}
                <input type="email" name="email" required="" value="${param.email}">
                <span data-placeholder="Email"></span>
            </div>
            <div class="txtb">
                ${requestScope.SIGNUPERROR.errorName}
                <input type="text" name="userName" required="" value="${param.userName}">
                <span data-placeholder="User Name"></span>
            </div>
            <div class="txtb">
                ${requestScope.SIGNUPERROR.errorPassword}
                <input type="password" name="password" required="" value="${param.password}">
                <span data-placeholder="Password"></span>
            </div>
            <div class="txtb">
                ${requestScope.SIGNUPERROR.errorConfirm}
                <input type="password" name="confirm" required="" value="${param.confirm}">
                <span data-placeholder="Confirm Password"></span>
                ${requestScope.SIGNUPERROR.errorConfirmPassword}
            </div>
            <input type="hidden" name="searchTitle" value="${param.searchTitle}"/> 
            <input type="submit" class="signbtn" name="action" value="Sign up">
            <div class="middle">
                <p>
                    Or Sign up With
                </p>

                <div class="g-signin2" data-width="280%" data-height="50%;" data-longtitle="true" data-onsuccess="onSignUp" ></div>
            </div>
            <div class="bottom-text">
                Already have account ?
                <a onclick="fromSignupToLogin()" href="#">Login</a>
                <br>
                <br>
            </div>
        </form>
    </div>




    <c:if test="${not empty requestScope.SIGNUPERROR}">
        <script>
            displaySignup();
        </script>
    </c:if>
    <c:if test="${not empty requestScope.LOGINERROR}">
        <script>
            displayLogin();
        </script>
    </c:if>
    <script>
        $(".txtb input").on("focus", function () {
            $(this).addClass("focus");
        });
        $(".txtb input").on("blur", function () {
            if ($(this).val() == "")
                $(this).removeClass("focus");
        });


    </script>
    <!-- Header Section End -->
    <!-- Hero Section Begin -->
    <section class="hero-section home-page set-bg" data-setbg="img/bg.jpg">
        <div class="container hero-text text-white">
            <h2>Create your great</h2>
            <h1>Article</h1>
        </div>
    </section>


    <div class="filter-search">
        <div class="container ">
            <div class="row">
                <div class="col-lg-12">
                    <form class="filter-form" action="MainController" method="POST">   
                        <input type="hidden" name="action" value="Search"/>
                        <c:if test="${not empty sessionScope.USERDTO.roleID}">
                            <c:if test="${sessionScope.USERDTO.roleID == 'AD'}">
                                <div class="search-type">
                                    <p>Status Article</p>
                                    <select class="filter-property" name="statusArticleID" onchange="this.form.submit();">
                                        <option value="">Choose a status</option>
                                        <c:forEach items="${requestScope.LISTSTATUSARTICLE}" var="x">
                                            <option value="${x.statusID}"
                                                    <c:if test="${not empty requestScope.ARTICLESTATUSID}">
                                                        <c:if test="${requestScope.ARTICLESTATUSID == x.statusID}">
                                                            selected=""
                                                        </c:if>
                                                    </c:if>
                                                    >${x.statusDetail}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                        </c:if>
                        <div class="wrap">
                            <p>Search content title</p>
                            <div class="search">
                                <input type="text" name="searchTitle" value="${param.searchTitle}" class="searchTerm" placeholder="What are you looking for?">

                            </div>
                        </div>

                        <div class="search-btn">
                            <button type="submit" name="action" value="Search"><i class="flaticon-search"></i>Search</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/main.js"></script>
</body>

</html>  