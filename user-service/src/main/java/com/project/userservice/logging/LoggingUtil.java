package com.project.userservice.logging;

import com.project.userservice.annotations.PII;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class LoggingUtil {
    public static String LOGGER_BEFORE_PREFIX = "===> Service request:  ";
    public static String LOGGER_AFTER_PREFIX = "===> Service response:  ";

    public static String logBeforeResponse(JoinPoint joinPoint, boolean logHeader) {

        StringBuilder logText = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        if (logHeader) {
            ServletRequestAttributes servReqAttr = (ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes();
            if ( servReqAttr != null && servReqAttr.getRequest() != null) {
                getServletRequestHeaderInfo(logText, servReqAttr);
            }
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Annotation[][] annotationsMatrix = null;
        String[] parameterNames = signature.getParameterNames();

        try {
            annotationsMatrix = joinPoint.getTarget().getClass().getMethod(methodName, parameterTypes)
                    .getParameterAnnotations();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < args.length; i++) {
            Annotation[] annotations = annotationsMatrix == null ? null : annotationsMatrix[i];
            String loggingDataTypeHeader = "\n " + parameterNames[i] + "=";
            boolean isPII = false;
            int keepLastDigits = 0;
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    if (annotation instanceof RequestBody) {
                        loggingDataTypeHeader = "\n " + "RequestBody=";
                    }
                    if (annotation instanceof PII) {
                        keepLastDigits = ((PII) annotation).keepLastDigits();
                        isPII = true;
                    }
                }
            }

            logText.append(loggingDataTypeHeader);
            if (isPII) {
                var piiMask = new PIIMask();
                logText.append(piiMask.mask((String) args[i], keepLastDigits));
            } else {
                logText.append(JsonConverter.convertToJsonString(args[i]));
            }

        }
        return logText.toString();
    }

    public static String logAfterResponse(Object retVal, boolean logHeader) {

        StringBuilder logText = new StringBuilder();
        if (logHeader) {
            ServletRequestAttributes servReqAttr = (ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes();
            if (servReqAttr != null && servReqAttr.getRequest() != null) {
                getServletRequestHeaderInfo(logText, servReqAttr);
            }
        }
        logText.append("\n "+ "Response=");
        logText.append(JsonConverter.convertToJsonString(retVal));
        return logText.toString();
    }

    public static String logAfterThrowingRequest(Object[] args, boolean logHeader) {
        StringBuilder logText = new StringBuilder();
        if (logHeader) {
            ServletRequestAttributes servReqAttr = (ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes();
            if ( servReqAttr != null && servReqAttr.getRequest() != null) {
                getServletRequestHeaderInfo(logText, servReqAttr);
            }
        }
        if (args != null && args.length > 0) {
            logText.append("\n "+ "Exception Response=");
            logText.append(JsonConverter.convertToJsonString(args[0]));
        }
        return logText.toString();
    }

    public static void getServletRequestHeaderInfo(StringBuilder logText, ServletRequestAttributes servReqAttr) {
        HttpServletRequest request = servReqAttr.getRequest();
        logText.append("\n "+ "ClientId=");
        logText.append(request.getHeader("Client-id"));
        logText.append("\n "+ "RequestURI=");
        logText.append(request.getRequestURI());
        logText.append("\n "+ "RequestURL=");
        logText.append(request.getRequestURL().toString());
    }

    public static HttpHeaders processRestHttpEntityHeader(HttpEntity requestedCls) {
        HttpHeaders headerClone = new HttpHeaders();
        for (String headerName : requestedCls.getHeaders().keySet()) {
            boolean doMask = MaskNames.contains(headerName);
            List<String > values = requestedCls.getHeaders().get(headerName);
            List<String > valuesMasked = new ArrayList<String>();

            for (String value : values) {
                if (doMask) {
                    var piiMask = new PIIMask();
                    value = piiMask.mask(value, 0);
                    valuesMasked.add(value);
                }
            }

            if(doMask) {
                headerClone.put(headerName, valuesMasked);
            }else {
                headerClone.put(headerName, values);
            }
        }
        return headerClone;
    }

}