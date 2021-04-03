package edu.fzu.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author JohnCarraway
 * @create 2021-01-02 21:09
 */
public class GuliException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

    public GuliException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public GuliException() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
