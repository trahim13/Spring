import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import robot.RobotT1000;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        RobotT1000 t1000;

        Object obj = context.getBean("t1000s");
        if (obj instanceof RobotT1000) {
            t1000 = (RobotT1000) obj;
            t1000.dance();
        }

//        RobotT1000 t1000 = (RobotT1000) context.getBean("t1000");

        RobotT1000 t1000l = (RobotT1000) context.getBean("t1000l");
        t1000l.action();
    }
}
