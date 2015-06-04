/**
 * Created by FunkySoya on 2015/6/5.
 */
/******
 * Controller
 */
app.controller('RouteMainCtl',['$scope','$http','$location',function($scope,$http,$location){

    $scope.createBusiness = function(){
        $location.path('createBusiness');
    };
}])
.controller('RouteErrorCtl',function($scope,$http){

})
.controller('RouteNewBusinessCtl',['$scope','$http','BaseData',function($scope,$http,BaseData){

    //游戏类型
    BaseData.getByType('gameType').then(function(data){
        console.log(data);
        $scope.gameType = data;
    });
    //游戏平台
    $scope.platform = BaseData.getByType('platform');

}])
.controller('RouteDeferMsgCtl',['$scope','$http','deferMsg',function($scope,$http,deferMsg){
    $scope.deferMsg = deferMsg;
}])
.controller('RouteLoginCtl',['$scope','$http',function($scope,$http){
    var form = {};
    $scope.form = form;

    $scope.login = function(isValid){
        if(isValid) {
            $http.post('/ajax/login', $scope.form).success(function (obj) {
                var loginInfo = {};
                if(obj.status==Status.SUCCESS) {
                    $.cookie('userName',obj.data.userName,{expires: 7});
                    $.cookie('nickName',obj.data.nickName,{expires: 7});
                    if(form.isRemember){
                        $.cookie('token',obj.data.token,{expires: 7});
                    }
                    loginInfo.info = obj.data.nickName;
                    loginInfo.hasLogin = true;
                    $scope.loginerror = false;
                    location.href = '/'
                }else{
                    loginInfo.hasLogin = false;
                    $scope.loginerror = true;
                    $scope.loginerroinfo = obj.msg;
                }
                //派发登陆事件
                $scope.$emit('onLogin', loginInfo);
            });
        }
    };
}])
.controller('RouteRegisterCtl',function($scope,$http){
    var form = {};
    var user = {};

    form.user = user;

    $scope.form = form;
    $scope.register = function(){
        if($scope.registerForm.$valid && $scope.checkMatch()) {
            $http.post('/ajax/register/new',$scope.form).success(function(data) {
                $scope.registerForm.userName.$error.unique = false;
                $scope.registerForm.nickName.$error.unique = false;
                $scope.registerForm.kaptcha.$error.invalid = false;
                if(data.status == Status.SUCCESS){
                    location.href = '/';
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
                if(typeof(e) == 'object' && typeof(e.$dirty) == 'boolean'){
                    e.$dirty = true;
                }
            });
        }
    }

    $scope.validExists = function(){
        if($scope.form.user.userName){
            $http.post('/ajax/register/validUserName',{'userName':$scope.form.user.userName}).success(function(data) {
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

    $scope.changeCaptcha = function($event) {//生成验证码
        $event.target.src = '/ajax/captcha-image?' + Math.floor(Math.random()*100);
    };
})
.controller('HomeController',['$scope','$http','$location',function($scope,$http,$location) {
    var loginInfo = {};
    $scope.loginInfo = loginInfo;

    //监听登陆事件
    $scope.$on('onLogin',function(d,data){
        loginInfo = data;
    });

    //自动登陆验证
    $http.post('/ajax/checkLogin').success(function(obj){
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
        $http.post('/ajax/logoff').success(function(obj){
            if(obj.status==Status.SUCCESS){
                $.cookie('token', '', { expires: -1 });
                $.cookie('nickName', '', { expires: -1 });
                location.href = '/'
            }
        });

    };

}]);