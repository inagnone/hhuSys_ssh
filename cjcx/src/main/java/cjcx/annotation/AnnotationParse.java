package cjcx.annotation;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/2/18.
 */
public class AnnotationParse {
    public static String parse(Class classt,String methodName) throws NoSuchMethodException {
        String privilege = "";
        Method[] methods = classt.getDeclaredMethods();
        Method method = null;
        for(Method m:methods){
            if(m.getName().equals(methodName)){
                method = m;
                break;
            }
        }
        if(method == null){
            throw new NullPointerException();
        }
        if(method.isAnnotationPresent(PrivilegeInfo.class)){
            PrivilegeInfo privilegeInfo = method.getAnnotation(PrivilegeInfo.class);
            privilege = privilegeInfo.name();
        }
        return privilege;
    }
}
