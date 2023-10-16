package com.project.userservice.logging;

import netscape.javascript.JSObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ControllerLoggingAspect {

    @Pointcut("execution(* com.project.userservice.controller.*.*(..))")
    public void loggerAnnotation() {

    }

    @Before("loggerAnnotation()")
    public void controllerStart(JoinPoint joinPoint) {

        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
//        if(logger.isDebugEnabled())
        {
            String logBuilder =   "Controller Start- " +
                    getName(joinPoint) + LoggingUtil.logBeforeResponse(joinPoint,true); ;

            logger.info(logBuilder);
        }
    }

    @AfterReturning(value = "execution(* com.project.userservice.controller.*.*(..))", returning = "retVal")
    public void controllerEnd(JoinPoint joinPoint, Object retVal) throws Throwable {

        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());

//        if(logger.isDebugEnabled())
        {
            String logBuilder =  "Controller End- " +
                    getName(joinPoint) + LoggingUtil.logAfterResponse(retVal, true);

            logger.info(logBuilder);
        }
    }

    @AfterThrowing(pointcut = "loggerAnnotation()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        String logBuilder =  "After Logger Throwing - " + getName(joinPoint)
                + "\n " + "Error Message=" + e.getMessage();

        logger.error(logBuilder);
    }

    private String getName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName() + " ";
    }
}
