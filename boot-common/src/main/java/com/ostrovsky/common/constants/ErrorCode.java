package com.ostrovsky.common.constants;

/**
 * <p>Title: ErrorCode</p>
 * <p>Description:
 *   错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 *     如：10001（10代表系统模块，001代表业务代码）</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/2 13:30
 */
public interface ErrorCode {

    /** 系统响应码：错误 */
    int SYSTEM_ERROR = 0;

    /** 系统响应码：成功 */
    int SYSTEM_SUCCESS = 1;

    /** 响应失败 */
    int RESPONSE_FAILED = 10000;

    /** 响应成功 */
    int RESPONSE_SUCCESS = 10001;

}
