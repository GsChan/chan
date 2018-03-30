package com.gitsome.chan.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author : 130801(cgs)
 *         Date : 2017-11-06
 *         Time : 18:03
 */
public class ContextUtils {
    private final static String KEY_USER_ID = "USER_ID";
    /**
     *  0 idaas 用户 1 uc 用户
     */
    private final static String KEY_USER_TYPE = "USER_TYPE";
    private final static String KEY_REQUEST = "HTTP_SERVLET_REQUEST";
    private final static String KEY_RESPONSE =  "HTTP_SERVLET_RESPONSE";

    /**
     * 线程存储
     */
    private static ThreadLocal<HashMap> local = new ThreadLocal();

    public static String getUserId() {
        return get(KEY_USER_ID,String.class);
    }

    public static void setUserId(String userId) {
        put(KEY_USER_ID,userId);
    }

    public static Integer getUserType() {
        return get(KEY_USER_TYPE,Integer.class);
    }

    public static void setUserType(Integer userType) {
        put(KEY_USER_TYPE,userType);
    }

    public static HttpServletRequest getRequest() {
        return get(KEY_REQUEST,HttpServletRequest.class);
    }

    public static void setRequest(HttpServletRequest request) {
        put(KEY_REQUEST,request);
    }

    public static HttpServletResponse getResponse() {
        return get(KEY_RESPONSE,HttpServletResponse.class);
    }

    public static void setResponse(HttpServletResponse response) {
        put(KEY_RESPONSE,response);
    }

    public static HashMap getLocal() {
        HashMap hashMap = local.get();
        if(hashMap == null) {
            hashMap = new HashMap();
            local.set(hashMap);
        }

        return hashMap;
    }

    public static <T> T get(String key, Class<T> clazz) {
        return (T) getLocal().get(key);
    }

    public static void put(String key, Object value) {
        getLocal().put(key, value);
    }

}
