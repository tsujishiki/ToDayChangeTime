/**
 * Created by Administrator on 2015/4/16.
 */

var login = angular.module("login", []);

login.controller("LoginController",function($scope,$http) {
    var user = {};
    login.userName = "abc";
    login.password = "ccc";

    $scope.login = login;

    $scope.Login = function(){
        $http.post("/login",$scope.login).success(function(data) {alert(data.msg);});
    }
});

$(function(){
    $('#kaptchaImage').click(function () {//生成验证码
        $(this).hide().attr('src', '/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();
        event.cancelBubble=true;
    });
});
