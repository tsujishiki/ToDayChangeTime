/**
 * Created by FunkySoya on 2015/6/5.
 */
app.factory('BaseDataService',['$q','$http',function($q,$http){
    return {
        getByType:function(type){
            var deferred = $q.defer();
            $http.get('/ajax/baseData/'+type).success(function(obj){
                if(obj.status==Status.SUCCESS) {
                    deferred.resolve(obj.data);
                }
            });
            return deferred.promise;
        }
    }
}])
.factory('LoginService',['$q','$http',function($q,$http){
    var loginInfo = {};
    return {
        login:function(form){
            var deferred = $q.defer();
            $http.post('/ajax/login', form).success(function (obj) {
                if(obj.status==Status.SUCCESS) {
                    $.cookie('userName',obj.data.userName,{expires: 7});
                    $.cookie('nickName',obj.data.nickName,{expires: 7});
                    if(form.isRemember){
                        $.cookie('token',obj.data.token,{expires: 7});
                    }
                    loginInfo.info = obj.data.nickName;
                    loginInfo.hasLogin = true;
                    loginInfo.loginerror = false;
                }else{
                    loginInfo.hasLogin = false;
                    loginInfo.loginerror = true;
                    loginInfo.loginerroinfo = obj.msg;
                }
                deferred.resolve(loginInfo);
            });
            return deferred.promise;
        },
        logOff : function(){
            $http.post('/ajax/logoff').success(function(obj){
                if(obj.status==Status.SUCCESS){
                    $.cookie('token', '', { expires: -1 });
                    $.cookie('nickName', '', { expires: -1 });
                    location.href = '/'
                }
            });
        },
        autoLogin : function(){
            //自动登陆验证
            $http.post('/ajax/checkLogin').success(function(obj){
                if(obj.status==Status.SUCCESS){
                    loginInfo.hasLogin = true;
                    loginInfo.info = obj.data;
                } else{
                    loginInfo.hasLogin = false;
                    $.cookie('token','',{expires: -1});
                }
            });
        },
        getLoginInfo:function(){
            return loginInfo;
        }
    }
}])
.factory("RegisterService",['$q','$http',function($q,$http){
   return{
       register : function(form){
           var deferred = $q.defer();
           $http.post('/ajax/register/new',form).success(function(data){
               deferred.resolve(data);
           });
           return deferred.promise;
       },
       remoteValid : function(userName){
           var deferred = $q.defer();
           $http.post('/ajax/register/validUserName',{'userName':userName}).success(function(data){
               deferred.resolve(data);
           });
           return deferred.promise;
       }
   }
}])
