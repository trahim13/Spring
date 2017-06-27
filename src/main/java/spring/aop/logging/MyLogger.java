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
            output = joinPoint.proceed(new Object[]{"C:\\Users\\petrovski\\IdeaProjects"});
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }

        long time = System.currentTimeMillis() - start;
        System.out.println("method end: " + joinPoint.getSignature().toShortString() + " ,time= " + time + " ms");

        return output;
    }
    @SuppressWarnings("rawtypes")
    @AfterReturning(pointcut = "allMethods()&&execution(java.util.Map *(String))&&args(folder)", returning = "obj")
    public void printMap(Object obj, String folder) {

        System.out.println("Print map>>");
        System.out.println("Folder: "+folder);

        Map map = (Map) obj;
        for (Object object : map.keySet()) {
            System.out.println("key=" + object + ", " + map.get(object));
        }

        System.out.println("End print map<<");
        System.out.println();
    }

    @SuppressWarnings("rawtypes")
    @AfterReturning(pointcut = "allMethods()&&execution(java.util.Set *(String))&&args(folder)", returning = "obj")
    public void printSet(Object obj, String folder) {
        System.out.println("Print Set");
        System.out.println("Folder: "+folder);
        Set set = (Set) obj;
        for (Object object : set) {
            System.out.println(object);
        }
        System.out.println("End of Set");
        System.out.println();
    }





}
