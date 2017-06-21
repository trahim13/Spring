import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import robot.RobotT1000;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");




        RobotT1000 t1000b = (RobotT1000) context.getBean("t1000b");
        t1000b.action();

       ;


    }
}
