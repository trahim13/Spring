package robot;

import interfaces.Hand;
import interfaces.Head;
import interfaces.Leg;
import interfaces.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by root on 21.06.2017.
 */
public abstract class BaseModel implements Robot {
    @Autowired//(required = false)
    private Hand hand;

    @Autowired//(required = false)
    private Leg leg;

    @Autowired//(required = false)
    private Head head;

    public BaseModel() {
        System.out.println(this + " baseModel constructor.");
    }

   @Autowired
    public BaseModel(Hand hand, Leg leg, Head head) {
        this();
        this.hand = hand;
        this.leg = leg;
        this.head = head;
    }

    public Hand getHand() {
        return hand;
    }

//    @Required
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Leg getLeg() {
        return leg;
    }

//    @Required
    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public Head getHead() {
        return head;
    }

   // @Required
    public void setHead(Head head) {
        this.head = head;
    }
}
