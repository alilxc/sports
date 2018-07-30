package com.example.sports.dto;


import com.example.sports.constant.Errors;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 结果类,提供简化的dubbo接口返回类<br/>
 * Result&lt;XXX&gt; result = Result.create();<br/>
 * ...<br/>
 * return result.success();<br/>
 * or <br/>
 * return result.success(data);<br/>
 * or <br/>
 * return result.fail("SomeErrorCode","SomeDescription")<br/>
 * or <br/>
 * return result.fail("SomeErrorCode") <br/>
 * or you can do chained callings like below:<br/>
 *
 * result.data(data).code("SomeCode").description("SomeDescription").success();<br/>
 * <br/>
 * Created by luopeng
 * on 2015/9/14.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 返回数据，可为基本类型（包装类），可以为其它可序列化对象
     */
    @ApiModelProperty(value = "接口数据对象")
    private T data;
    /** 成功标志*/
    @ApiModelProperty(value = "接口成功标志")
    private boolean success;

    /** 信息码 */
    @ApiModelProperty(value = "接口状态编码")
    private String code;

    /** 描述 */
    @ApiModelProperty(value = "错误信息描述")
    private String description;
    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public static <T> Result<T> create() {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        return result;
    }

    public Result<T> success(){
        success(null);
        return this;
    }

    public Result<T> success(T data){
        this.setSuccess(true);
        this.data = data;
        return this;
    }

    public Result<T> fail(String code,String description){
        this.setSuccess(false);
        this.setCode(code);
        this.setDescription(description);
        return this;
    }

    public Result<T> fail(int code,String description){
        this.setSuccess(false);
        this.setCode(String.valueOf(code));
        this.setDescription(description);
        return this;
    }

    public Result<T> fail(Errors errors) {
        this.setSuccess(false);
        this.setCode(String.valueOf(errors.code));
        this.setDescription(errors.label);
        return this;
    }

    public Result<T> fail(String code){
        fail(code, null);
        return this;
    }

    public Result<T> code(String code){
        this.setCode(code);
        return this;
    }

    public Result<T> description(String description){
        this.setDescription(description);
        return this;
    }

    public Result<T> data(T data){
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
