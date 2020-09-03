package com.ostrovsky.api.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: MyBatisPlusConfig</p>
 * <p>Description: MyBatisPlus配置类</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/2 21:30
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * <p>分页插件配置</p>
     *
     * @return {@link PaginationInterceptor} 分页拦截器
     * @author Zhao Xinlei
     * @since 2020/8/10 14:47
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
