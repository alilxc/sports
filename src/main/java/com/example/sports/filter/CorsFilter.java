package com.example.sports.filter;
import com.example.sports.conf.SysConfig;
import com.example.sports.constant.SysEnv;
import com.example.sports.util.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 跨域
 */
@Component
public class CorsFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SysConfig sysConfig;

    /**
     * 设置请求
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //logger.info("请求HEAD: " + this.getAllHeaders(request));
        String ip = HttpRequestUtil.getRemoteIp(request);
        //logger.info("客户端请求 IP ADDRESS: " + ip);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (!SysEnv.isPROD(sysConfig.getEnv())) {
            response.addHeader("Access-Control-Allow-Origin", ip);
            response.addHeader("Access-Control-Allow-Credentials", "true");
            if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
                response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type,Accept,X-Auth-Token,X-Data-Type,X-Forwarded-For");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getAllHeaders(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            sb.append(key).append("=").append(value).append(";");
        }
        return sb.toString();
    }
}
