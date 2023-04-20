import java.util.ArrayList;
import java.util.List;

public class TrafficLight {

  private List<TrafficLightObserver> observers;
  private TrafficLightState state;

  public TrafficLight() {
    observers = new ArrayList<>();
  }

  public void addObserver(TrafficLightObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(TrafficLightObserver observer) {
    observers.remove(observer);
  }

  public void setState(TrafficLightState state) {
    this.state = state;
    notifyObservers();
  }

  private void notifyObservers() {
    for (TrafficLightObserver observer : observers) {
      observer.update(state);
    }
  }
}
