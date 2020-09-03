package com.ostrovsky.api.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: WebMvcConfig</p>
 * <p>Description: Web层配置</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/1 18:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .exposedHeaders(
                        "access-control-allow-headers",
                        "access-control-allow-methods",
                        "access-control-allow-origin",
                        "access-control-max-age",
                        "X-Frame-Options")
                .allowCredentials(true)  // 是否发送Cookie
                .maxAge(3600);  // 响应体在客户端保存的时间,单位：秒
    }

    /**
     * 格式化转换器
     *
     * @param converters 格式化转化器
     * @author ostrovsky
     * @since 2020/9/1 22:31
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));// @ResponseBody 解决乱码
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        converters.add(fastJsonHttpMessageConverter());
    }

    /**
     * FastJson序列化配置
     *
     * @return {@link FastJsonHttpMessageConverter}
     * @author ostrovsky
     * @since 2020/9/1 22:29
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        //Long类型转String类型
        // SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        // ToStringSerializer 是这个包 com.alibaba.fastjson.serializer.ToStringSerializer
        // serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        // serializeConfig.put(Long.class, ToStringSerializer.instance);
        // serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        // config.setSerializeConfig(serializeConfig);
        config.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue, // 保留map空的字段
                SerializerFeature.WriteNullStringAsEmpty, // 将String类型的null转成""
                SerializerFeature.WriteNullNumberAsZero, // 将Number类型的null转成0
                SerializerFeature.WriteNullListAsEmpty, // 将List类型的null转成[]
                SerializerFeature.WriteNullBooleanAsFalse, // 将Boolean类型的null转成false
                SerializerFeature.WriteDateUseDateFormat,  //日期格式转换
                SerializerFeature.DisableCircularReferenceDetect // 避免循环引用
        );
        config.setSerializeFilters(valueFilter);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        return converter;
    }

    /**
     * FastJson过滤器将null值转换为字符串
     * obj 是class
     * s 是key值
     * o1 是value值
     */
    public static final ValueFilter valueFilter = (obj, s, v) -> {
        if (v == null) {
            return "";
        }
        return v;
    };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
    }
}
