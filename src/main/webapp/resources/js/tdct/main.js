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
        console.log($scope.login);
        $http.post("/login",$scope.login).success(function(data) {alert(data.msg);});
    }
});