/**
 * Created by FunkySoya on 2015/4/28.
 */
var Status = (function() {
    var constants = {//���峣��
        SUCCESS: 3001,
        FAILED: 3002
    }

    var Test={};

    Test.getConstant=function(name){//��ȡ�����ķ���
        return constants[name];
    }
    // ������һ����̬���� ��ȡ�����ķ���
    Test.SUCCESS=function() {
        return Test.getConstant("SUCCESS");
    }

    Test.FAILED=function() {
        return Test.getConstant("FAILED");
    }

    return Test;
})();