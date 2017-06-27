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

    @Around("allMethods()) && execution(java.util.Map.*(..)")
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
    @SuppressWarnings("rawtypes")
    @AfterReturning(pointcut = "execution(java.util.Map *(..))", returning = "obj")
    public void printMap(Object obj) {

        System.out.println("Print map>>");

        Map map = (Map) obj;
        for (Object object : map.keySet()) {
            System.out.println("key=" + object + ", " + map.get(object));
        }

        System.out.println("End print map<<");
        System.out.println();
    }

    @SuppressWarnings("rawtypes")
    @AfterReturning(pointcut = "execution(java.util.Set *(..))", returning = "obj")
    public void printSet(Object obj) {
        System.out.println("Print Set");
        Set set = (Set) obj;
        for (Object object : set) {
            System.out.println(object);
        }
        System.out.println("End of Set");
        System.out.println();
    }





}
