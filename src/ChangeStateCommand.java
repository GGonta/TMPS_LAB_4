public class ChangeStateCommand implements Command {

  private TrafficLight trafficLight;
  private TrafficLightState newState;

  public ChangeStateCommand(TrafficLight trafficLight, TrafficLightState newState) {
    this.trafficLight = trafficLight;
    this.newState = newState;
  }

  @Override
  public void execute() {
    trafficLight.setState(newState);
  }
}
