package com.ostrovsky.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * <p>Title: MessageUtils</p>
 * <p>Description: 国际化消息工具</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/2 14:05
 */
@DependsOn("springContextUtils")
public class MessageUtils {

    private static final MessageSource messageSource;
    static {
        messageSource = (MessageSource) SpringContextUtils.getBean("messageSource");
    }

    /**
     * 通过code,获取国际化信息
     *
     * @param code 代码
     * @return {@link String} 国际化信息
     * @author ostrovsky
     * @since 2020/9/2 14:18
     */
    public static String getMessage(int code){
        return getMessage(code, new String[0]);
    }

    /**
     * 通过code和参数,获取国际化信息
     *
     * @param code      代码
     * @param params    参数
     * @return {@link String} 国际化信息
     * @author ostrovsky
     * @since 2020/9/2 14:19
     */
    public static String getMessage(int code, String... params){
        return messageSource.getMessage(code + "", params, LocaleContextHolder.getLocale());
    }
}
