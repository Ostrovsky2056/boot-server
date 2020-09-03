package com.ostrovsky.common.base;

import com.ostrovsky.common.constants.ErrorCode;
import com.ostrovsky.common.utils.MessageUtils;
import lombok.Data;

/**
 * <p>Title: Result</p>
 * <p>Description: </p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/1 17:51
 */
@Data
public class Result<T> implements IResult<T> {

    //<editor-fold desc="属性">

    /** 代码 */
    private Integer code;

    /** 信息 */
    private String msg;

    /** 数据 */
    private T data;

    //</editor-fold>


    //<editor-fold desc="响应方法">

    public Result<T> success() {
        this.code = ErrorCode.RESPONSE_SUCCESS;
        this.msg = MessageUtils.getMessage(ErrorCode.RESPONSE_SUCCESS);
        return this;
    }

    public Result<T> success(T data) {
        this.code = ErrorCode.RESPONSE_SUCCESS;
        this.msg = MessageUtils.getMessage(ErrorCode.RESPONSE_SUCCESS);
        this.data = data;
        return this;
    }

    public Result<T> error() {
        this.code = ErrorCode.RESPONSE_FAILED;
        this.msg = MessageUtils.getMessage(ErrorCode.RESPONSE_FAILED);
        return this;
    }

    public Result<T> error(int errorCode) {
        this.code = errorCode;
        this.msg = MessageUtils.getMessage(errorCode);
        return this;
    }

    @Deprecated
    public Result<T> error(int errorCode, String message) {
        this.code = errorCode;
        this.msg = message;
        return this;
    }

    //</editor-fold>


    //<editor-fold desc="getter and setter">

    /**
     * 获取代码
     *
     * @return {@link Integer} 代码
     * @author ostrovsky
     * @since 2020/9/1 17:26
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    /**
     * 获取信息
     *
     * @return {@link String} 信息
     * @author ostrovsky
     * @since 2020/9/1 17:27
     */
    @Override
    public String getMsg() {
        return this.msg;
    }

    /**
     * 获取响应数据
     *
     * @return {@link T} 响应数据
     * @author ostrovsky
     * @since 2020/9/1 17:27
     */
    @Override
    public T getData() {
        return this.data;
    }

    //</editor-fold>
}
