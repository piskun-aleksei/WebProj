<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.content" var="rb"/>
<html>
    <head>
        <title><fmt:message key="Label.welcome" bundle="${rb}"/></title>
        <link rel="stylesheet" href="/css/stylesheet.css">
    </head>
    <body>
        <%@ include file="shared/header.jsp"%>
        <article class="container">
            <c:if test="${isAdmin == true}">
                <form name="AddFilmPageForm" action="controller" method="POST">
                    <input type="hidden" name="command" value="open"/>
                    <input type="hidden" name="page" value="/jsp/profile.jsp" />
                    <input type="hidden" name="forwPage" value="/jsp/add_film.jsp" />
                    <input type="submit" name="showButton" class="input-button" value=<fmt:message key="Button.addFilm" bundle="${rb}"/> />
                </form>
                <form name="DeleteUserPageForm" action="controller" method="POST">
                    <input type="hidden" name="command" value="findUsersPreparation"/>
                    <input type="hidden" name="page" value="/jsp/profile.jsp" />
                    <input type="hidden" name="forwPage" value="/jsp/delete_user.jsp" />
                    <input type="submit" name="showButton" class="input-button" value=<fmt:message key="Button.deleteUser" bundle="${rb}"/> />
                </form>
                <form name="AddUserBonusPageForm" action="controller" method="POST">
                    <input type="hidden" name="command" value="findUsersPreparation"/>
                    <input type="hidden" name="page" value="/jsp/profile.jsp" />
                    <input type="hidden" name="forwPage" value="/jsp/add_bonus.jsp" />
                    <input type="submit" name="showButton" class="input-button" value=<fmt:message key="Button.addUserBonus" bundle="${rb}"/> />
                </form>
                <form name="UpdateFilmPageForm" action="controller" method="POST">
                    <input type="hidden" name="command" value="findFilms"/>
                    <input type="hidden" name="page" value="/jsp/profile.jsp" />
                    <input type="hidden" name="forwPage" value="/jsp/choose_film_to_update.jsp" />
                    <input type="submit" name="showButton" class="input-button" value=<fmt:message key="Button.updateFilm" bundle="${rb}"/> />
                </form>
            </c:if>
            <form name="AddCashPageForm" action="controller" method="POST">
                <input type="hidden" name="command" value="open"/>
                <input type="hidden" name="page" value="/jsp/profile.jsp"/>
                <input type="hidden" name="forwPage" value="/jsp/add_credit.jsp" />
                <input type="submit" name="showButton" class="input-button" value=
                        <fmt:message key="Button.addCash" bundle="${rb}"/>/>
            </form>
            <form name="ShowCreditPageForm" action="controller" method="POST">
                <input type="hidden" name="command" value="showCredit"/>
                <input type="hidden" name="page" value="/jsp/account_credit.jsp"/>
                <input type="submit" name="showButton" class="input-button" value=
                        <fmt:message key="Button.showCredit" bundle="${rb}"/>/>
            </form>
            <c:if test="${isAdmin == false}">
                <form name="FilmPaymentForm" action="controller" method="POST">
                    <input type="hidden" name="command" value="payForFilmPreparation"/>
                    <input type="hidden" name="page" value="/jsp/film_payment.jsp"/>
                    <input type="submit" name="showButton" class="input-button" value=
                            <fmt:message key="Button.payForFilm" bundle="${rb}"/>/>
                </form>
                <form name="ShowFilmsForm" action="controller" method="POST">
                    <input type="hidden" name="command" value="showFilmsOnAccount"/>
                    <input type="hidden" name="page" value="/jsp/films_on_account.jsp"/>
                    <input type="submit" name="showButton" class="input-button" value=
                            <fmt:message key="Button.showFilmsOnAccount" bundle="${rb}"/>/>
                </form>
                <form name="ChangeLoginForm" action="controller" method="POST">
                    <input type="hidden" name="command" value="open"/>
                    <input type="hidden" name="page" value="/jsp/profile.jsp" />
                    <input type="hidden" name="forwPage" value="/jsp/change_login.jsp" />
                    <input type="submit" name="showButton" class="input-button" value=<fmt:message key="Button.changeLogin" bundle="${rb}"/> />
                </form>
            </c:if>
            <form name="ChangePassword" action="controller" method="POST">
                <input type="hidden" name="command" value="open"/>
                <input type="hidden" name="page" value="/jsp/profile.jsp" />
                <input type="hidden" name="forwPage" value="/jsp/change_password.jsp" />
                <input type="submit" name="showButton" class="input-button" value=<fmt:message key="Button.changePassword" bundle="${rb}"/> />
            </form>
            <form name="BackForm" action="controller" method="POST">
                <input type="hidden" name="command" value="back" />
                <input type="hidden" name="backPage" value="/jsp/main.jsp" />
                <input type="submit" name="backButton" class="input-button" value=<fmt:message key="Button.back" bundle="${rb}"/> />
            </form>
        </article>
        <%@ include file="shared/footer.jsp"%>
        <script type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
        <script type="text/javascript" src="/js/validate.js"></script>
    </body>
</html>
