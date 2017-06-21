package impls;

import interfaces.Robot;
import interfaces.RobotPool;

import java.util.Collection;
import java.util.Map;

public class T1000Pool implements RobotPool {

    private Map<String, Robot> robotCollection;


    public T1000Pool(Map<String, Robot> robotCollection) {
        this.robotCollection = robotCollection;
    }

    @Override
    public Map<String, Robot> getRobotCollection() {
        return robotCollection;
    }

    public void action() {
        for (Map.Entry<String, Robot> entry : robotCollection.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().action();
        }
    }
}
