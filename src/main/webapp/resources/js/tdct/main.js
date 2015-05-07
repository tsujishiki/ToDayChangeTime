/**
 * Created by Administrator on 2015/4/16.
 */

var app = angular.module("mainApp", ["ngRoute"]);
//路由 模板设置
app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'view/main.html',
            controller: 'RouteMainCtl'
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

app.controller("LoginController",function($scope,$http) {
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
    }

    $scope.toRegister = function(){
        location.href = "/register.html"
    }

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

    }
});

$(function(){
    $('#kaptchaImage').click(function () {//生成验证码
        $(this).hide().attr('src', '/ajax/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();
        event.cancelBubble=true;
    });

});
