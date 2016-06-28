<%@ taglib prefix="ctg" uri="customtags" %>
<div class="header-container">
    <ctg:authCheckingTag/>
    <input type="hidden" class="error-check-login" value="${errorLoginMessage}">
    <input type="hidden" class="error-check-register" value="${errorRegisterMessage}">
    <div>
        <c:if test="${login != null}">
        <c:if test="${isAdmin == true}">
            <p class="login-message-wrapper"><fmt:message key="Label.admin" bundle="${rb}"/> ${login}</p>
        </c:if>
        <c:if test="${isAdmin == false}">
            <p class="login-message-wrapper"><fmt:message key="Label.user" bundle="${rb}"/> ${login}</p>
        </c:if>
        </c:if>
    </div>
    <div class="locale">
        <form name="LocalizeForm" action="controller" method="POST">
            <input type="hidden" name="command" value="locale">
            <input type="submit" class="locale-button " name="localizeButtonMainRus" value=<fmt:message key="Button.localizeRus" bundle="${rb}"/> />
            <input type="submit" class="locale-button" name="localizeButtonMainEng" value=<fmt:message key="Button.localizeEng" bundle="${rb}"/> />
        </form>
    </div>
    <div class="background-wrapper"></div>
    <article class="login-wrapper">
        <form name="LoginForm" action="controller" method="POST">
            <input type="hidden" name="command" value="login" />
            <input type="hidden" name="page" value="/jsp/login.jsp" />
            <input type="text" name="login" placeholder="<fmt:message key="Label.login" bundle="${rb}"/>" class="input-field" required/>
            <input type="password" name="password" placeholder="<fmt:message key="Label.password" bundle="${rb}"/>" class="input-field" value="" pattern="[A-z]{1,1}[A-z0-9]{3,}" required/>
            <div class="error-message">${errorLoginMessage}</div>
            <br/>
            <br/>
            <input type="submit" name="loginButton" class="input-button" value=<fmt:message key="Button.login" bundle="${rb}" /> />
        </form>
        <input type="submit" name="backButton" class="input-button form-back-login" value=<fmt:message key="Button.back" bundle="${rb}"/> />
    </article>
    <article class="register-wrapper">
        <form name="RegisterForm" action="controller" method="POST">
            <input type="hidden" name="command" value="register" />
            <input type="hidden" name="page" value="/jsp/registration.jsp" />
            <input type="text" value="${regLogin}" name="login" placeholder="<fmt:message key="Label.login" bundle="${rb}"/>" class="input-field" required/>
            <input type="password" value="${regPass}" id="password" name="password"
                   placeholder="<fmt:message key="Label.password" bundle="${rb}"/>" class="input-field"
                   onchange="validatePassword('${locale}')" value="" pattern="[A-z]{1,1}[A-z0-9]{3,}" required/>
            <input type="password" value="${regPass}" id="retypePass"  name="retypePass"
                   placeholder="<fmt:message key="Label.retypePass" bundle="${rb}"/>" class="input-field"
                   onkeyup="validatePassword('${locale}')" value="" pattern="[A-z]{1,1}[A-z0-9]{3,}" required/>
            <input type="text" name="name" value="${regName}" placeholder="<fmt:message key="Label.name" bundle="${rb}"/>" class="input-field" required/>
            <input type="text" name="surname" value="${regSurname}" placeholder="<fmt:message key="Label.surname" bundle="${rb}"/>" class="input-field" required/>
            <div class="error-message">${errorRegisterMessage}</div>
            <br/>
            <input type="submit" name="registerButton" class="input-button" value=<fmt:message key="Button.register" bundle="${rb}"/> />
        </form>
        <input type="submit" name="backButton" class="input-button form-back-register" value=<fmt:message key="Button.back" bundle="${rb}"/> />
    </article>
</div>