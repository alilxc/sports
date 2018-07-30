package com.example.sports.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @title
 * @Author huangjiarui
 * @date: 2018-04-11
 */
@ConfigurationProperties(prefix = "sys")
@Component
public class SysConfig {

    /**
     * 系统环境
     */
    private String  env;

    /**
     * session过期时间
     */
    private long    sessionTimeout;


    private boolean genApi              = false;


    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public boolean isGenApi() {
        return genApi;
    }

    public void setGenApi(boolean genApi) {
        this.genApi = genApi;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
