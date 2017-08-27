package com.zong.paycommon.utils.httpclient;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpException;

import java.io.IOException;
import java.util.Map;

/**
 * 包装了HttpClinet往服务器发送请求的接口
 * 让用户不适用任何HttpClient的api的
 * 直接调用改接口就可以实现相应的操作
 * Created by 宗叶青 on 2017/8/13.
 */
public interface HttpClientWrapper {
    /**
     * 设置协议头的信息，用户可以根据自己的需求而设定，否则使用默认的设置
     * @param headers
     */
    void addHttpHeader(Map<String, String> headers);

    /**
     * 清除cookie信息
     */
    void clearCookie();

    /**
     * 把一组cookies加到httpclietn中
     * @param cookies
     */
    void addCookies(Cookie[] cookies);

    /**
     *
     * @param method
     * @param url
     * @param params
     * @param charset
     * @return 返回带编码集的结果
     * @throws HttpException
     * @throws IOException
     *
     */
    String doRequest(MethodType method, String url,
                     Map<String, String> params, String charset)throws HttpException,IOException;

    /**
     *
     * @param callback HttpResponseCallBack 是设置的一个回调类，主要是考虑由于httpClient返回的流
     *                 当连接关闭流也关闭了。所以利用回调的方式在流关闭之间嵌入用户代码来操作流
     * @param method
     * @param url
     * @param params
     * @param charset
     * @throws HttpException
     * @throws IOException
     */
    void doRequest(HttpResponseCallBack callback, MethodType method,
                   String url, Map<String, String> params, String charset)
        throws HttpException, IOException;

    /**
     * 无返回值 外部可以利用到流来得到结果，主要考虑的是多线程下载的情况
     * @param callback HttpResponseCallBack 是设置的一个回调类，主要是考虑由于httpClient返回的流
     *                 当连接关闭流也关闭了。所以利用回调的方式在流关闭之间嵌入用户代码来操作流
     * @param method
     * @param url
     * @param charset
     * @throws HttpException
     * @throws IOException
     */
    void doRequest(HttpResponseCallBack callback, MethodType method,
                   String url, String charset) throws HttpException, IOException;
}
