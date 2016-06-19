<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.content" var="rb"/>
<html>
    <head>
        <title><fmt:message key="Label.errorTitle" bundle="${rb}"/></title>
        <link rel="stylesheet" href="/css/stylesheet.css">
    </head>
    <body>
        <%@ include file="shared/header.jsp"%>
        Request from ${pageContext.errorData.requestURI} is failed
        <br/>
        Servlet name or type: ${pageContext.errorData.servletName}
        <br/>
        Status code: ${pageContext.errorData.statusCode}
        <br/>
        Exception:  <c:forEach var="trace"
                          items="${pageContext.exception.stackTrace}">
                        <p>${trace}</p>
                    </c:forEach>
        <form name="BackForm" action="controller" method="POST">
            <input type="hidden" name="command" value="back">
            <input type="hidden" name="backPage" value="/jsp/index.jsp">
            <input type="submit" name="backButton" value=
                <fmt:message key="Button.back" bundle="${rb}"/> />
        </form>
    </body>
</html>
