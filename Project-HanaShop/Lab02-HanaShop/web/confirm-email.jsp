<%-- 
    Document   : confirm-email
    Created on : Jan 10, 2020, 10:36:58 PM
    Author     : sonho
--%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Email Page</title>
    </head>
    <body>
        
        <c:out value="${requestScope.NOTCONFIRMYET}"/>
        <form action="MainController" method="POST">
            <input type="hidden" name="code" value="${sessionScope.code}"/>
            <input type="hidden" name="email" value="${param.email}"/>
            <input type="hidden" name="password" value="${param.password}"/>
            <input type="number" required="" name="confirmCode"/>
            <input type="submit" name="action" value="Confirm"/>
        </form>
        <form action="MainController" method="POST">
            <input type="hidden" name="code" value="${sessionScope.code}"/>
            <input type="hidden" name="email" value="${param.email}"/>
            <input type="hidden" name="password" value="${param.password}"/>
            <input type="submit" name="action" value="Send code again"/>
        </form>
        ${requestScope.ERRORCODE}

        <a href="index.jsp">Back to home page</a>
    </body>
</html>
