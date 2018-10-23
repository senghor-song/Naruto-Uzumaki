package com.xiaoyi.ssm.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.xiaoyi.ssm.dto.BaseResult;

/**
 * @author song
 * 采用AOP的方式处理参数问题。
 */
@Component
@Aspect
public class BindingResultAop {
	
    private static Logger logger = Logger.getLogger(BindingResultAop.class.getName());

    @Pointcut("execution(* com.xiaoyi.ssm.web.*.*(..))")
    public void aopMethod(){}

    @Around("aopMethod()")
    public Object  around(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("before method invoking!");
        BindingResult bindingResult = null;
        for(Object arg:joinPoint.getArgs()){
            if(arg instanceof BindingResult){
                bindingResult = (BindingResult) arg;
            }
        }
        if(bindingResult != null){
            if(bindingResult.hasErrors()){
                String errorInfo="["+bindingResult.getFieldError().getField()+"]"+bindingResult.getFieldError().getDefaultMessage();
                return new BaseResult<Object>(false, errorInfo);
            }
        }
        return joinPoint.proceed();
    }
}
