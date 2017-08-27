package com.zong.paycommon.utils.httpclient;

import java.util.List;
import java.util.Map;

/**
 * @author 宗叶青 on 2017/8/13/23:02
 */
public class SimpleHttpResult {

    /**
     * HTTP状态码
     */
    private int statusCode;

    /**
     * HTTP结果
     */
    private String content;

    private String exceptionMsg;

    private Exception exception;

    private Map<String,List<String>> headers;

    private String contentType;

    public SimpleHttpResult(int code){
        this.statusCode = code;
    }

    public SimpleHttpResult(int code, String _content){
        this.statusCode = code;
        this.content = _content;
    }

    public SimpleHttpResult(Exception e){
        if(e==null){
            throw new IllegalArgumentException("exception must be specified");
        }
        this.statusCode = -1;
        this.exception = e;
        this.exceptionMsg = e.getMessage();
    }

    public String getContentType(){
        return contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }


    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public Exception getException() {
        return exception;
    }

    public boolean isSuccess(){
        return statusCode==200;
    }

    public boolean isError(){
        return exception!=null;
    }


    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
