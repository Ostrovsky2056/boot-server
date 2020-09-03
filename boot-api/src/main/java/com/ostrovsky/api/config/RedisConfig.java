package com.ostrovsky.api.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>Title: RedisConfig</p>
 * <p>Description: </p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/3 13:28
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);  // 配置连接工厂
        setRedisTemplateSerializer(redisTemplate);  // 设置序列化工具
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        setStringRedisTemplateSerializer(stringRedisTemplate);  // 设置序列化工具

        return stringRedisTemplate;
    }

    /**
     * 设置StringRedisTemplate序列化配置
     *
     * @param stringRedisTemplate StringRedis模版
     * @author ostrovsky
     * @since 2020/9/3 15:13
     */
    private void setStringRedisTemplateSerializer(StringRedisTemplate stringRedisTemplate){
        // 获取Jackson对象
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getJackson2JsonRedisSerializer();

        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    }

    /**
     * 设置RedisTemplate序列化配置
     *
     * @param redisTemplate redis模版
     * @author ostrovsky
     * @since 2020/9/3 14:45
     */
    private void setRedisTemplateSerializer(RedisTemplate<String, Object> redisTemplate) {
        // 获取Jackson对象
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getJackson2JsonRedisSerializer();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    }

    /**
     * 获取Jackson序列话对象
     *
     * @return {@link Jackson2JsonRedisSerializer<Object>} Jackson序列化对象
     * @author ostrovsky
     * @since 2020/9/3 15:04
     */
    private Jackson2JsonRedisSerializer<Object> getJackson2JsonRedisSerializer() {
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        PolymorphicTypeValidator ptv = om.getPolymorphicTypeValidator();
        // 指定要序列化的域,field,get和set,以及修饰符范围,ANY是都有,包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型,类必须是非final修饰的,final修饰的类,比如String,Integer等会跑出异常
        om.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}
