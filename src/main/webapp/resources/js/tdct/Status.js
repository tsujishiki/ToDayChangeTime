/**
 * Created by FunkySoya on 2015/4/28.
 */
var Status = (function() {
    var constants = {//定义常量
        SUCCESS: 3001,
        FAILED: 3002
    }

    var Test={};

    Test.getConstant=function(name){//获取常量的方法
        return constants[name];
    }
    // 定义了一个静态方法 获取常量的方法
    Test.SUCCESS=function() {
        return Test.getConstant("SUCCESS");
    }

    Test.FAILED=function() {
        return Test.getConstant("FAILED");
    }

    return Test;
})();