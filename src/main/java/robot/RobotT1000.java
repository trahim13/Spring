package robot;

import interfaces.Hand;
import interfaces.Head;
import interfaces.Leg;
import interfaces.Robot;

public class RobotT1000 implements Robot {

    private Hand hand;
    private Leg leg;
    private Head head;

    private String color;
    private int year;
    private boolean soundEnabled;


    public RobotT1000() {

    }

    public RobotT1000(Hand hand, Leg leg, Head head) {
        this.hand = hand;
        this.leg = leg;
        this.head = head;
    }

    public RobotT1000(Hand hand, Leg leg, Head head, String color, int year, boolean soundEnabled) {
        this.hand = hand;
        this.leg = leg;
        this.head = head;
        this.color = color;
        this.year = year;
        this.soundEnabled = soundEnabled;
    }

    public RobotT1000(String color, int year, boolean soundEnabled) {
        this.color = color;
        this.year = year;
        this.soundEnabled = soundEnabled;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
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
        head.calc();
        leg.go();
        hand.catchSomething();
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
}
