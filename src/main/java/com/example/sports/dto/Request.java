package com.example.sports.dto;

import java.io.Serializable;

/**
 * 请求类,提供简化的dubbo接口请求类<br/>
 * Request&lt;XXX&gt; request = Request.create();<br/>
 * ...<br/>
 * <br/>
 * Created by luopeng
 * on 2015/9/14.
 */
public class Request<T> implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;
    /**
     * 请求数据，可为基本类型（包装类），可以为其它可序列化对象
     */
    private T data;

    public static <T> Request<T> create() {
        Request<T> result = new Request<T>();
        return result;
    }

    public Request<T> data(T data){
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
