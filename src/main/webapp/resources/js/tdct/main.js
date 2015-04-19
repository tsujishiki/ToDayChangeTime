/**
 * Created by Administrator on 2015/4/16.
 */

var login = angular.module("login", []);

login.controller("LoginController",function($scope,$http) {
    $scope.login = function(isValid){
        if(isValid) {
            $http.post("/login", $scope.login).success(function (data) {
                alert(data.msg);
            });
        }
    }

    $scope.toRegister = function(){
        location.href = "/register.html"
    }
});

$(function(){
    $('#kaptchaImage').click(function () {//生成验证码
        $(this).hide().attr('src', '/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();
        event.cancelBubble=true;
    });
});
