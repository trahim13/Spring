package robot;

import interfaces.Hand;
import interfaces.Head;
import interfaces.Leg;
import interfaces.Robot;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class RobotT1000 extends BaseModel implements Robot, InitializingBean, DisposableBean {



    private String color;
    private int year;
    private boolean soundEnabled;


    public RobotT1000() {

    }

    public RobotT1000(Hand hand, Leg leg, Head head) {
//        super(hand, leg, head);
    }

    public RobotT1000(Hand hand, Leg leg, Head head, String color, int year, boolean soundEnabled) {
//        super(hand, leg, head);
        this.color = color;
        this.year = year;
        this.soundEnabled = soundEnabled;
    }

    public RobotT1000(String color, int year, boolean soundEnabled) {
        this.color = color;
        this.year = year;
        this.soundEnabled = soundEnabled;
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    public void action() {
        getHead().calc();
        getLeg().go();
        getHand().catchSomething();
        System.out.println("color: " + color);
        System.out.println("year: " + year);
        System.out.println("can play sound: " + soundEnabled);

    }

    public void dance() {
        System.out.println("T1000 is dancing!");

    }

    public void initObject() {
        System.out.println("init");
    }

    public void destroyObject() {
        System.out.println("destroy");

    }

    public void defaultInit() {
        System.out.println("Default init");

    }

    public void defaultDestroy() {
        System.out.println("Default destroy");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this+" method destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this + " method init");

    }
}
