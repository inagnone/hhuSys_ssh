package cjcx.advoice;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/2/14.
 */
@Aspect
@Component("log")
public class Log {

    @Pointcut("execution(* cjcx.service.*.*(..))")
    private void method(){}

    private Logger logger;

    public void actionLog(Joinpoint joinpoint){

    }

    @AfterThrowing(pointcut = "execution(* cjcx.service.*.*(..))",throwing = "ex")
    public void exceptionLog(JoinPoint joinPoint, Throwable ex){
        Logger logger = Logger.getLogger(joinPoint.getClass());
        logger.error("---------------------------异常信息--------------------------");
        logger.error("出现异常的方法:"+joinPoint.getSignature().getName());
        logger.error(ex.fillInStackTrace());
    }
}
