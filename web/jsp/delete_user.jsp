<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.content" var="rb"/>
<html>
<head>
    <title><fmt:message key="Label.deleteUserHead" bundle="${rb}"/></title>
    <link rel="stylesheet" href="/css/stylesheet.css">
</head>
<body>
<%@ include file="shared/header.jsp"%>
<article class="container">
    <br/>
    <table id="users">
        <thead>
        <tr>
            <th scope="col" rowspan="2"><b><fmt:message key="Table.userLogin" bundle="${rb}"/> </b></th>
            <th scope="col" rowspan="2"><b><fmt:message key="Table.userCredit" bundle="${rb}"/></b></th>
            <th scope="col" rowspan="2"><b> <fmt:message key="Table.userName" bundle="${rb}"/> </b></th>
            <th scope="col" rowspan="2"><b> <fmt:message key="Table.userSurname" bundle="${rb}"/> </b></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="elem" items="${nonAdmins}" varStatus="status">
            <tr>
                <td><c:out value="${ elem.login}" /></td>
                <td><c:out value="${ elem.credit }" /></td>
                <td><c:out value="${ elem.name }" /></td>
                <td><c:out value="${ elem.surname }" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form name="DeleteUserForm" action="controller" method="POST">
        <select class="select-center" name="userToDelete">
            <c:forEach var="elem" items="${nonAdmins}" varStatus="status">
                <option value="${elem.login}">${elem.login}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="command" value="deleteUser" />
        <input type="hidden" name="page" value="/jsp/profile.jsp" />
        <input type="submit" name="backButton" class="input-button" value=<fmt:message key="Button.deleteUser" bundle="${rb}"/> />
    </form>
    <form name="BackForm" action="controller" method="POST">
        <input type="hidden" name="command" value="back" />
        <input type="hidden" name="backPage" value="/jsp/profile.jsp" />
        <input type="submit" name="backButton" class="input-button" value=<fmt:message key="Button.back" bundle="${rb}"/> />
    </form>
</article>
<%@ include file="shared/footer.jsp"%>
<script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="/js/validate.js"></script>
</body>
</html>
