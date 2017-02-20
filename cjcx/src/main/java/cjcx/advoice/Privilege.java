package cjcx.advoice;

import cjcx.Exception.UserException;
import cjcx.annotation.AnnotationParse;
import cjcx.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2017/2/18.
 */
public class Privilege {
    public Object controlTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Class targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        String needPrivilege = AnnotationParse.parse(targetClass, methodName);
        if(StringUtils.isEmpty(needPrivilege)){
            return joinPoint.proceed();
        }
        User user = (User) joinPoint.getArgs()[0];
        if(user == null){
            throw new UserException("用户未登陆");
        }
        boolean privilege = user.getRole().hasPrivilege(needPrivilege);
        if(privilege){
            return joinPoint.proceed();
        }else{
            throw new UserException("用户没有相关权限");
        }
    }
}
