<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.content" var="rb"/>
<html>
<head>
    <title><fmt:message key="Label.changePasswordHead" bundle="${rb}"/></title>
    <link rel="stylesheet" href="/css/stylesheet.css">
</head>
<body>
<%@ include file="shared/header.jsp"%>
<article class="container">
    <form name="ChangePasswordForm" action="controller" method="POST">
        <input type="hidden" name="command" value="changePassword" />
        <input type="hidden" name="page" value="/jsp/profile.jsp" />
        <input type="password" id="password" name="oldPassword"
               placeholder="<fmt:message key="Label.oldPass" bundle="${rb}"/>" class="input-field"
               value="" pattern="[A-z]{1,1}[A-z0-9]{3,}" required/>
        <input type="password" id="newPassword" name="newPassword"
               placeholder="<fmt:message key="Label.newPass" bundle="${rb}"/>" class="input-field"
               onchange="validatePasswordNew('${locale}')" value="" pattern="[A-z]{1,1}[A-z0-9]{3,}" required/>
        <input type="password" id="retypeNewPass" name="retypeNewPass"
               placeholder="<fmt:message key="Label.retypeNewPass" bundle="${rb}"/>" class="input-field"
               onchange="validatePasswordNew('${locale}')" value="" pattern="[A-z]{1,1}[A-z0-9]{3,}" required/>
        <div class="error-message">${errorPassMessage}</div>
        <br/>
        <input type="submit" name="changeLoginButton" class="input-button" value=<fmt:message key="Button.changePassword" bundle="${rb}" /> />
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
