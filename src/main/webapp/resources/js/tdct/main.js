/**
 * Created by Administrator on 2015/4/16.
 */

var login = angular.module("login", []);

login.controller("LoginController",function($scope,$http) {
    var form = {};
    if(localStorage){
        form.userName = localStorage.getItem("userName");
    }

    $scope.form = form;

    $scope.login = function(isValid){
        if(isValid) {
            console.log($scope.form);
            $http.post("/login", $scope.form).success(function (obj) {
                if(obj.status=="T") {
                    $.cookie("userName", $scope.form.userName,{expires:7});
                    $.cookie("token",obj.data);
                }else{
                    alert(data.msg);
                }
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

    $

});
