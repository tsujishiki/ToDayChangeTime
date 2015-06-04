/**
 * Created by Administrator on 2015/4/16.
 */

var app = angular.module('mainApp', ['ngRoute'])

/*****
 * 全局变量
 */
.value('deferMsg',{'msg':''})

/*****
 * Interceptor
 */
.factory('statusInterceptor', ['$q','$location','deferMsg',function($q,$location,deferMsg) {
    var statusInterceptor = {
        response: function(response) {
            var deferred = $q.defer();
            if(response.data.status == Status.ERROR){//系统错误
                $location.path('/error');
                return deferred.promise;
            }else if(response.data.status == Status.DEFER_MESSAGE){//延时消息提示
                deferMsg.msg = response.data.msg;
                $location.path('/deferMessage');
                return deferred.promise;
            }else if(response.data.status == Status.REDIRECT){//页面跳转
                $location.path( response.data.redirectUrl);
                return deferred.promise;
            }else{
                return response;
            }
        }
    };
    return statusInterceptor;
}])

/****
 *路由 模板设置
 */
.config(['$routeProvider','$locationProvider','$httpProvider', function ($routeProvider,$locationProvider,$httpProvider) {
    $locationProvider.html5Mode(true);

    $httpProvider.interceptors.push('statusInterceptor');

    $routeProvider
        .when('/', {
            templateUrl: 'view/main.html',
            controller: 'RouteMainCtl'
        })
        .when('/register', {
            templateUrl: 'view/register.html',
            controller: 'RouteRegisterCtl'
        })
        .when('/login', {
            templateUrl: 'view/login.html',
            controller: 'RouteLoginCtl'
        })
        .when('/error', {
            templateUrl: 'view/error.html',
            controller: 'RouteErrorCtl'
        })
        .when('/deferMessage', {
            templateUrl: 'view/deferMsg.html',
            controller: 'RouteDeferMsgCtl'
        })
        .when('/createBusiness', {
            templateUrl: 'view/business/newBusiness.html',
            controller: 'RouteNewBusinessCtl'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);
