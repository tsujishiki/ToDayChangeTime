/**
 * Created by Administrator on 2015/4/16.
 */

var login = angular.module("login", []);

login.$controller("loginController",function($scope,$http) {
    var user = {};
    user.userName = "abc";
    user.password = "ccc";

    $scope.user = user;
});