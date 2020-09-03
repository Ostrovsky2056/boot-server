package com.ostrovsky.common.base;

/**
 * <p>Title: IResult</p>
 * <p>Description: 自定义响应体</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/1 17:21
 */
public interface IResult<T> {

    /**
     * 获取代码
     *
     * @return {@link Integer} 代码
     * @author ostrovsky
     * @since 2020/9/1 17:26
     */
    Integer getCode();

    /**
     * 获取信息
     *
     * @return {@link String} 信息
     * @author ostrovsky
     * @since 2020/9/1 17:27
     */
    String getMsg();

    /**
     * 获取响应数据
     *
     * @return {@link T} 响应数据
     * @author ostrovsky
     * @since 2020/9/1 17:27
     */
    T getData();
}
