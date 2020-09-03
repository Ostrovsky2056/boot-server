package com.ostrovsky.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * <p>Title: SpringContextUtils</p>
 * <p>Description: Spring容器工具类</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/2 14:09
 */
@Component
@Lazy(false)
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtils.applicationContext == null) {
            SpringContextUtils.applicationContext = applicationContext;
        }
    }

    /** 获取applicationContext */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /** 通过name获取 Bean */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /** 通过class获取Bean */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /** 通过name,以及Clazz返回指定的Bean */
    public static <T> T getBean(String name,Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /** 通过name,判断当前Bean是否存在 */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /** 通过name,判断当前Bean是否是单例 */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }
}
