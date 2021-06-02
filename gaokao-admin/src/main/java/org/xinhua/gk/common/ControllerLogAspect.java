package org.xinhua.gk.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xinhua.gk.domain.vo.ResultVO;



@Aspect
@Component
public class ControllerLogAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    /**
     * controller 层切点
     */
    @Pointcut("execution(* org.xinhua.gk.web.*.*(..))")
    public void controllerPointcut() {
    }

    private static final ThreadLocal<Map<String, Object>> localValue = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            Map<String, Object> threadLocalValue = new HashMap<>();
            threadLocalValue.put("uuid", UUID.randomUUID().toString());
            return threadLocalValue;
        }
    };

    @Around(value = "controllerPointcut()")
    public Object controllerLogAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String uuid = (String) localValue.get().get("uuid");

        /**
         * 获取参数
         */
        Object[] objects = joinPoint.getArgs();
        Stream<Object> stream = ArrayUtil.isEmpty(objects) ? Stream.empty() : Arrays.stream(objects);
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)))
                .collect(Collectors.toList());
        String params = "";
        if (!joinPoint.getSignature().getName().equals("uploadAndImport")) {
            params = JSONUtil.toJsonStr(logArgs);
        }

        String functionName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();

        /**
         * 入参日志
         */
        logger.info("[控制层接口入参]\n\tMark: {}\n\tFunctionName: {}\n\tParameters: {}", uuid, functionName, params);

        /**
         * 出参日志
         */
        Object result = joinPoint.proceed();
        if (result instanceof ResultVO) {
            ResultVO resultVO = (ResultVO) result;
            logger.info("[控制层接口出参]\n\tMark: {}\n\tCode: {}\n\tMessage: {}\n\tData: {}",
                    uuid,
                    resultVO.getCode(),
                    resultVO.getDesc(),
                    JSONUtil.toJsonStr(resultVO.getResult()));
        } else {
            logger.info("[控制层接口出参]\n\tMark: {}\n\tResult: {}",
                    uuid,
                    result);
        }

        return result;
    }

    private ThreadLocal<Map<String, Object>> timeValue;

    @Before(value = "controllerPointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        timeValue = new ThreadLocal<Map<String, Object>>() {
            @Override
            protected Map<String, Object> initialValue() {
                Map<String, Object> threadLocalValue = new HashMap<>();
                threadLocalValue.put("time", System.currentTimeMillis());
                return threadLocalValue;
            }
        };

        localValue.get();
    }

    @After(value = "controllerPointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        if (timeValue != null && timeValue.get() != null && timeValue.get().get("time") != null) {
            double costTime = (System.currentTimeMillis() - (Long) timeValue.get().get("time")) / 1000.0;
            String uuid = (String) localValue.get().get("uuid");
//			if(logger.isDebugEnabled()) {
            logger.info("[控制层接口耗时]\n\tMark: {}\n\tCostTime: {}", uuid, costTime);
//			}
        }
    }
}
