/**
 * Created by FunkySoya on 2015/6/5.
 * Controller
 */
app.controller('RouteMainCtl',['$scope','$location',function($scope,$location){

    $scope.createBusiness = function(){
        $location.path('createBusiness');
    };
}])
.controller('RouteErrorCtl',function($scope,$http){

})
.controller('RouteNewBusinessCtl',['$scope','BaseDataService',function($scope,BaseDataService){
    //游戏类型
    BaseDataService.getByType('gameType').then(function(data){
        $scope.gameType = data;
    });
    //游戏平台
    BaseDataService.getByType('platform').then(function(data){
        $scope.platform = data;
    });
}])
.controller('RouteDeferMsgCtl',['$scope','deferMsg',function($scope,deferMsg){
    $scope.deferMsg = deferMsg;
}])
.controller('RouteLoginCtl',['$scope','$location','LoginService',function($scope,$location,LoginService){
    var form = {};
    $scope.form = form;

    $scope.login = function(isValid){
        if(isValid) {
            LoginService.login($scope.form).then(function(data){
                if(data.hasLogin){
                    $location.path("/");
                }
                //派发登陆事件
                $scope.$emit('onLogin', data);
            })
        }else{
            angular.forEach($scope.loginForm,function(e){
                if(typeof(e) == 'object' && typeof(e.$dirty) == 'boolean'){
                    e.$dirty = true;
                }
            });
        }
    };
}])
.controller('RouteRegisterCtl',['$scope','RegisterService',function($scope,RegisterService){
    var form = {};
    var user = {};

    form.user = user;

    $scope.form = form;
    $scope.register = function(){
        if($scope.registerForm.$valid && $scope.checkMatch()) {
            RegisterService.register($scope.form).then(function(data) {
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
            RegisterService.remoteValid($scope.form.user.userName).then(function(data) {
                if(data.status == Status.FAILED()){
                    $scope.registerForm.userName.$error.unique = true;
                    $scope.registerForm.$invalid = true;
                    $scope.registerForm.$valid = false;
                }else{
                    $scope.registerForm.userName.$error.unique = false;
                }
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
}])
.controller('HomeController',['$scope','$location','LoginService',function($scope,$location,LoginService) {
    var loginInfo = {};
    $scope.loginInfo = loginInfo;

    LoginService.autoLogin();

    //监听登陆事件
    $scope.$on('onLogin',function(d,data){
        $scope.loginInfo = data;
    });

    //路由用户登陆验证
    $scope.$on('$routeChangeStart', function(scope, next, current) {
        var needPermission = next.$$route.needPermission;
        if(needPermission && !LoginService.getLoginInfo().hasLogin){
            $location.path('/login');
        }
    });

    $scope.toRegister = function(){
        $location.path('register');
    };

    $scope.toLogin = function(){
        $location.path('login');
    };

    $scope.toLogoff = function(){
        LoginService.logOff();
    };

}]);