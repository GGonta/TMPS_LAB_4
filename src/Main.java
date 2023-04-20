import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    TrafficLight trafficLight = new TrafficLight();

    // Add an observer
    trafficLight.addObserver(state -> System.out.println("Traffic light changed to: " + state.getColor()));

    // Create commands to change traffic light states
    Command changeToRed = new ChangeStateCommand(trafficLight, new RedState());
    Command changeToYellow = new ChangeStateCommand(trafficLight, new YellowState());
    Command changeToGreen = new ChangeStateCommand(trafficLight, new GreenState());

    // Execute commands to change states
    changeToRed.execute();
    changeToYellow.execute();
    changeToGreen.execute();
  }
}
