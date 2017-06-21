import interfaces.Robot;
import interfaces.RobotConveyor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import robot.RobotT1000;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");

        RobotConveyor conveyor = (RobotConveyor) context.getBean("t1000Conveyor");

        Robot terminator1 = conveyor.createRobot();
        terminator1.action();




    }
}
