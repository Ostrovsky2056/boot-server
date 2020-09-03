package com.ostrovsky.api.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: CorsFilter</p>
 * <p>Description: 跨域请求过滤器</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/2 15:06
 */
@Component
public class CorsFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /* 获取请求头部信息 */
        Object origin = request.getHeader("origin");  // 表示跨域请求的原始域
        Object connection = request.getHeader("connection");
        Object accessRequestMethod = request.getHeader("Access-Control-Request-Method");
        Object accessRequestHeaders = request.getHeader("Access-Control-Request-Headers");

        /* 设置响应头部信息 */
        response.addHeader("Access-Control-Allow-Credentials", "true");  // 表示是否允许客户端发送 Cookie，是一个布尔值。默认情况下，Cookie不包括在 CORS 请求之中，设为 true，即表示服务器明确许可，Cookie 可以包含在请求中，一起发给服务器
        response.addHeader("Access-Control-Allow-Origin", origin == null ? "*" : origin.toString());  // 表示允许哪些原始域进行跨域访问,它的值要么是请求时Origin字段的值,要么是一个 *,表示接受任意域名的请求
        response.addHeader("Access-Control-Allow-Methods", accessRequestMethod == null ? "GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD" : accessRequestMethod.toString());
        response.addHeader("Access-Control-Allow-Headers", accessRequestHeaders == null ? "Content-Type, Origin, Accept, Authorization, X-Request-With, X-CAF-Authorization-Token, sessionToken, X-TOKEN, token" : accessRequestHeaders.toString());

        // 支持HTTP 1.1
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Connection", connection == null ? "keep-alive": connection.toString());

        if (request.getMethod().equals("OPTIONS")) {
            response.getWriter().println("ok");
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
