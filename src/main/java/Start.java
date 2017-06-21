import impls.T1000Pool;
import interfaces.Robot;
import interfaces.RobotConveyor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import robot.RobotT1000;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");

        T1000Pool t1000Pool = (T1000Pool) context.getBean("t1000Pool");
        t1000Pool.action();



    }
}
