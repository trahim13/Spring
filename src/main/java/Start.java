import impls.T1000Pool;
import interfaces.Robot;
import interfaces.RobotConveyor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import robot.*;


public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");
        RobotT1000 robotT1000 = (RobotT1000) context.getBean("robotT1000");
        System.out.println(robotT1000);

        System.out.println();

        RobotT1000 model1 = (RobotT1000) context.getBean("model1");
        model1.action();

        System.out.println();

        RobotT1000 model2 = (RobotT1000) context.getBean("model2");
        model2.action();


    }
}
