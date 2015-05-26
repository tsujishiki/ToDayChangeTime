/**
 * Created by Administrator on 2015/4/16.
 */

var app = angular.module("mainApp", ["ngRoute"]);

/*****
 * Interceptor
 */
app.factory('errorInterceptor', ["$q","$location",function($q,$location) {
    var errorInterceptor = {
        response: function(response) {
            console.log(response);
            var deferred = $q.defer();
            if(response.data.status == Status.ERROR){
                $location.path("/error");
                return deferred.promise;
            }else{
                return response;
            }
        }
    };
    return errorInterceptor;
}]);

/****
 *路由 模板设置
 */
app.config(["$routeProvider","$locationProvider","$httpProvider", function ($routeProvider,$locationProvider,$httpProvider) {
    $locationProvider.html5Mode(true);

    $httpProvider.interceptors.push("errorInterceptor");

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
        .otherwise({
            redirectTo: '/'
        });
}]);

/*****
 * 全局变量
 */
app.value("loginInfo",{"hasLogin":false,"info":""});

/******
 * Controller
 */
app.controller("RouteMainCtl",function($scope,$http){

});

app.controller("RouteErrorCtl",function($scope,$http){

});

app.controller("RouteLoginCtl",["$scope","$http","loginInfo",function($scope,$http,loginInfo){
    var form = {};
    $scope.form = form;

    $scope.login = function(isValid){
        if(isValid) {
            $http.post("/ajax/login", $scope.form).success(function (obj) {
                if(obj.status==Status.SUCCESS) {
                    $.cookie("userName",obj.data.userName,{expires: 7});
                    $.cookie("nickName",obj.data.nickName,{expires: 7});
                    if(form.isRemember){
                        $.cookie("token",obj.data.token,{expires: 7});
                    }
                    loginInfo.info = obj.data.nickName;
                    loginInfo.hasLogin = true;
                    $scope.loginerror = false;
                    location.href = "/"
                }else{
                    loginInfo.hasLogin = false;
                    $scope.loginerror = true;
                    $scope.loginerroinfo = obj.msg;
                }
            });
        }
    };
}]);

app.controller("RouteRegisterCtl",function($scope,$http){
    var form = {};
    var user = {};

    form.user = user;

    $scope.form = form;
    $scope.register = function(){
        if($scope.registerForm.$valid && $scope.checkMatch()) {
            $http.post("/ajax/register/new",$scope.form).success(function(data) {
                $scope.registerForm.userName.$error.unique = false;
                $scope.registerForm.nickName.$error.unique = false;
                $scope.registerForm.kaptcha.$error.invalid = false;
                if(data.status == Status.SUCCESS){

                }else if(data.status == Status.USERNAME_DUPLICATE){
                    $scope.registerForm.userName.$error.unique = true;
                }else if(data.status == Status.NICKNAME_DUPLICATE){
                    $scope.registerForm.nickName.$error.unique = true;
                }else if(data.status == Status.CAPTCHA_INVALID){
                    $scope.registerForm.kaptcha.$error.invalid = true;
                }
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
            $http.post("/ajax/register/validUserName",{"userName":$scope.form.user.userName}).success(function(data) {
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
})



app.controller("HomeController",["$scope","$http","$location","loginInfo",function($scope,$http,$location,loginInfo) {
    $scope.loginInfo  = loginInfo;

    //自动登陆验证
    $http.post("/ajax/checkLogin").success(function(obj){
        if(obj.status==Status.SUCCESS){
            loginInfo.hasLogin = true;
            loginInfo.info = obj.data;
        } else{
            loginInfo.hasLogin = false;
        }
    });

    $scope.toRegister = function(){
        $location.path('register');
    };

    $scope.toLogin = function(){
        $location.path('login');
    };

    $scope.toLogoff = function(){
        $http.post("/ajax/logoff").success(function(obj){
            if(obj.status==Status.SUCCESS){
                $.cookie("token", '', { expires: -1 });
                $.cookie("nickName", '', { expires: -1 });
                location.href = "/"
            }else{
                alert(obj.msg);
            }
        });

    };

}]);