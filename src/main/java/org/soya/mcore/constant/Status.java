package org.soya.mcore.constant;

/**
 * 系统状态常量，返回对象（ReturnBody）中请求处理各种状态
 * Created by FunkySoya on 2015/4/28.
 */
public  class Status {
    public static final int SUCCESS = 3001;
    public static final int FAILED =  3002;
    public static final int USERNAME_DUPLICATE =  3003;
    public static final int NICKNAME_DUPLICATE =  3004;
    public static final int CAPTCHA_INVALID = 3005;
    public static final int ERROR = 3006;
    public static final int DEFER_MESSAGE = 3007;
    public static final int INVALIDATE = 3008;
}
