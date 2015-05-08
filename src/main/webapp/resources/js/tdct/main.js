/**
 * Created by Administrator on 2015/4/16.
 */

var app = angular.module("mainApp", ["ngRoute"]);
//路由 模板设置
app.config(['$routeProvider',"$locationProvider", function ($routeProvider,$locationProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider
        .when('/', {
            templateUrl: 'view/main.html',
            controller: 'RouteMainCtl'
        })
        .when('/register', {
            templateUrl: 'view/register.html',
            controller: 'RouteRegisterCtl'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);

/******
 * Controller
 */
app.controller("RouteMainCtl",function($scope,$http){

})

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



app.controller("LoginController",function($scope,$http,$location) {
    //自动登陆验证
    $http.post("/ajax/checkLogin").success(function(obj){
       if(obj.status==Status.SUCCESS){
           $scope.haslogin = true;
           $scope.comeInfo = obj.data;
       } else{
           $scope.haslogin = false;
       }
    });

    var form = {};
    $scope.form = form;

    $scope.login = function(isValid){
        if(isValid) {
            $http.post("/ajax/login", $scope.form).success(function (obj) {
                if(obj.status==Status.SUCCESS) {
                    $.cookie("userName",obj.data.userName,{expires: 7});
                    $.cookie("nickName",obj.data.nickName,{expires: 7});
                    $scope.comeInfo = obj.data.nickName;
                    if(form.isRemember){
                        $.cookie("token",obj.data.token,{expires: 7});
                    }
                    $('#loginWin').modal('hide');
                    $scope.haslogin = true;
                }else{
                    $scope.haslogin = false;
                    alert(obj.msg);
                }
            });
        }
    };

    $scope.toRegister = function(){
        $location.path('register');
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

    $scope.changeCaptcha = function($event) {//生成验证码
        $event.target.src = '/ajax/captcha-image?' + Math.floor(Math.random()*100);
    };
});



