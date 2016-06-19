var result;
function validatePassword(lang){
    var password = document.getElementById("password")
        , retypePass = document.getElementById("retypePass"),
        result;
    if(password.value != retypePass.value) {
        if(lang === "ru_RU") {
            retypePass.setCustomValidity("Пароли не совпадают");
        }
        if(lang === "en_US"){
            retypePass.setCustomValidity("Passwords do not match");
        }
        result = false;
    } else {
        retypePass.setCustomValidity('');
        result = true;
    }
}

$(document).ready(function () {

    $('tbody tr').hover(function() {
        $(this).addClass('odd');
    }, function() {
        $(this).removeClass('odd');
    });

});

function validatePasswordNew(lang){
    var password = document.getElementById("newPassword")
        , retypePass = document.getElementById("retypeNewPass"),
        result;
    if(password.value != retypePass.value) {
        if(lang === "ru_RU") {
            retypePass.setCustomValidity("Пароли не совпадают");
        }
        if(lang === "en_US"){
            retypePass.setCustomValidity("Passwords do not match");
        }
        result = false;
    } else {
        retypePass.setCustomValidity('');
        result = true;
    }
}

function showLoginForm(){
    var loginButton = document.querySelector(".auth-link-login");
    var registerButton = document.querySelector(".auth-link-register");
    var backButtonLogin = document.querySelector(".form-back-login");
    var backButtonRegister = document.querySelector(".form-back-register");
    var error = document.querySelector(".error-check");
    if(error.value){
        document.querySelector(".background-wrapper").classList.toggle("visible");
        document.querySelector(".login-wrapper").classList.toggle("visible");
    }
    loginButton.addEventListener("click", function(){
        document.querySelector(".background-wrapper").classList.toggle("visible");
        document.querySelector(".login-wrapper").classList.toggle("visible");
    })
    registerButton.addEventListener("click", function(){
        document.querySelector(".background-wrapper").classList.toggle("visible");
        document.querySelector(".register-wrapper").classList.toggle("visible");
    })
    backButtonLogin.addEventListener("click", function(){
        document.querySelector(".background-wrapper").classList.toggle("visible");
        document.querySelector(".login-wrapper").classList.toggle("visible");
    })
    backButtonRegister.addEventListener("click", function(){
        document.querySelector(".background-wrapper").classList.toggle("visible");
        document.querySelector(".register-wrapper").classList.toggle("visible");
    })
}

showLoginForm();