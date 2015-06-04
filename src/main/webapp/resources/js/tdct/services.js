/**
 * Created by FunkySoya on 2015/6/5.
 */
app.service('BaseData',['$q','$http',function($q,$http){
    this.getByType = function(type){
        $http.get('/ajax/baseData/'+type).success(function(obj){
            if(obj.status==Status.SUCCESS) {
                return $q.defer().promise;
            }
        });
    }
}])

