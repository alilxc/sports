package com.example.sports.aspect;

import com.example.sports.annotation.AvoidRepeatableCommit;
import com.example.sports.util.HttpRequestUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class AvoidRepeatableCommitAspect {

    public LoadingCache<String, Boolean> requestCache = CacheBuilder.newBuilder().refreshAfterWrite(30, TimeUnit.SECONDS)
            //缓存的最大记录数，默认10000条
            .maximumSize(15000)
            .build(new CacheLoader<String, Boolean>() {
                @Override
                public Boolean load(String key) throws Exception {
                    return false;
                }
            });

    @Around("@annotation(com.example.sports.annotation.AvoidRepeatableCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = HttpRequestUtil.getRemoteIp(request);
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String name = method.getName();
        String ipKey = String.format("%s#%s#%s",className,name,ip);

        if(requestCache.get(ipKey)){
            return "请勿重复提交";
        }
        //写回缓存
        requestCache.put(ipKey, true);
        //执行方法
        Object object = point.proceed();
        return object;
    }
}
