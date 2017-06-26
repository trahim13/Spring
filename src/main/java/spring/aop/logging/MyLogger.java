package spring.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Aspect
public class MyLogger {

    @Pointcut("execution(* spring.aop.object.Manager.*(..))")
    private void allMethods() {
    }

    @Around("allMethods())")
    public Object whatTime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        System.out.println("method begin: " + joinPoint.getSignature().toShortString());
        Object output = null;

        for (Object object :
                joinPoint.getArgs()) {
            System.out.println("Param: " +object);
        }

        try {
            output = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }

        long time = System.currentTimeMillis() - start;
        System.out.println("method end: " + joinPoint.getSignature().toShortString() + " ,time= " + time + " ms");

        return output;
    }

    @AfterReturning(pointcut = "allMethods()", returning = "obj")
    public void print(Object obj) {

        System.out.println("Print info begin>>");

        if (obj instanceof Set) {
            Set set = (Set) obj;
            for (Object object : set) {
                System.out.println(object);
            }
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            for (Object object : map.keySet()) {
                System.out.println("key=" + object + ", " + map.get(object));
            }
        }

        System.out.println("Print info end<<");
        System.out.println();
    }

}
