package robot;

import java.lang.reflect.Method;

public class ActionReplacer implements org.springframework.beans.factory.support.MethodReplacer {
    @Override
    public Object reimplement(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("New action.");
        return null;
    }
}
