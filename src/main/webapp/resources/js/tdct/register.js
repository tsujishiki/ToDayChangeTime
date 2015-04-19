/**
 * Created by FunkySoya on 2015/4/19.
 */

var register = angular.module("uregister", []);

register.controller("registerController",function($scope,$http) {
    var form = {};
    var user = {};

    form.user = user;

    $scope.form = form;
    $scope.register = function(isValid){
        if(isValid) {
            //do something
        }
    }
});

$(function(){
    $('#password').focus(function(){
        this.type="password";
    });

    $('#passwordConfirm').focus(function(){
        this.type="password";
    });

    $('#kaptchaImage').click(function () {//生成验证码
        $(this).hide().attr('src', '/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();
        event.cancelBubble=true;
    });
});