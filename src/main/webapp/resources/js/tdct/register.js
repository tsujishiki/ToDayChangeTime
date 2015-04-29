/**
 * Created by FunkySoya on 2015/4/19.
 */

var register = angular.module("uregister", []);

register.controller("registerController",function($scope,$http) {
    var form = {};
    var user = {};

    form.user = user;

    $scope.form = form;
    $scope.register = function(){
        if($scope.registerForm.$valid && $scope.checkMatch()) {
            $http.post("/register/new",$scope.form.user).success(function(data) {
                console.log(data);
            }).error(function() {

            });
        }else{
            angular.forEach($scope.registerForm,function(e){
                if(typeof(e) == "object" && typeof(e.$dirty) == "boolean"){
                    e.$dirty = true;
                }
            });
        }
    }

    $scope.validExists = function(){
        if($scope.form.user.userName){
            $http.post("/register/validUserName",{"userName":$scope.form.user.userName}).success(function(data) {
                if(data.status == Status.FAILED()){
                    $scope.registerForm.userName.$error.unique = true;
                    $scope.registerForm.$invalid = true;
                    $scope.registerForm.$valid = false;
                }else{
                    $scope.registerForm.userName.$error.unique = false;
                }
            }).error(function() {
                $scope.registerForm.userName.$error.unique=false;
            });
        }
    };

    $scope.checkMatch = function(){
        var user = $scope.form.user;
        if(user.password && user.passwordConfirm ){
            if(user.password != user.passwordConfirm){
                $scope.registerForm.passwordConfirm.$error.match=true;
                return false;
            }
        }
        $scope.registerForm.passwordConfirm.$error.match=false;
        return true;
    }
});

$(function(){
    $('#password').focus(function(){
        this.type="password";
    });

    $('#passwordConfirm').focus(function(){
        this.type="password";
    });

    $('#kaptchaImage').click(function () {//������֤��
        $(this).hide().attr('src', '/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();
        event.cancelBubble=true;
    });
});