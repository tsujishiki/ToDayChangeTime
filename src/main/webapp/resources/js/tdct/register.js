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

register.directive("ensureUnique", function($http) {
    return {
        restrict: 'AE',
        require: 'ngModel',
        link: function(scope, ele, attrs, ctrl) {
            scope.$watch(attrs.ngModel, function() {
                if(scope.form.user.userName){
                    $http.post("/register/validUserName",{"userName":scope.form.user.userName}).success(function(data, status, headers, cfg) {
                        if(data.status == Status.FAILED){
                            ctrl.$setValidity('unique', true);
                        }else{
                            ctrl.$setValidity('unique', false);
                        }
                    }).error(function(data, status, headers, cfg) {
                        ctrl.$setValidity('unique', false);
                    });
                }
            });
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