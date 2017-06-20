import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import robot.RobotT1000;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("all_context.xml");

        RobotT1000 t1000;

        Object obj = context.getBean("t1000s");
        if (obj instanceof RobotT1000) {
            t1000 = (RobotT1000) obj;
            t1000.dance();
        }

        System.out.println();

        RobotT1000 t1000l = (RobotT1000) context.getBean("t1000l");
        t1000l.action();

        System.out.println();
        System.out.println("Constructor param arg");


        RobotT1000 robotConArg = (RobotT1000) context.getBean("t1000ConArg");
        robotConArg.action();

        System.out.println();

        System.out.println("Hash");
        RobotT1000 t1000One = (RobotT1000) context.getBean("t1000ConArg");
        System.out.println(t1000One.getHand());
        t1000One = (RobotT1000) context.getBean("t1000ConArg");
        System.out.println(t1000One.getHand());

    }
}
