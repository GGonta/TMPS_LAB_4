# În exemplul de cod pe care l-am scris sunt utilizate doua Behavioral Design Patterns diferite.
## Observer Pattern: 
Interfața TrafficLightObserver reprezintă observatorii semaforului și definește metoda update(TrafficLightState state), care este apelată atunci când starea semaforului se schimbă.
Clasa TrafficLight acționează ca subiectul observat. Aceasta menține o listă de observatori și oferă metode pentru a adăuga și elimina observatori.
Atunci când starea semaforului se schimbă prin intermediul metodei setState(TrafficLightState state), se apelează metoda notifyObservers() pentru a notifica toți observatorii înregistrați. Fiecare observator este apelat prin metoda update(TrafficLightState state), permițându-le să reacționeze la schimbarea stării semaforului.

```
public interface TrafficLightObserver {

  void update(TrafficLightState state);
}
----------------------------------------------------
----------------------------------------------------
public interface TrafficLightState {

  String getColor();
}
----------------------------------------------------
----------------------------------------------------
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

```
## Command Pattern:
Interfața Command reprezintă o comandă abstractă, care definește metoda execute().
Clasa ChangeStateCommand implementează interfața Command și reprezintă o comandă specifică pentru a schimba starea semaforului. Aceasta primește un obiect TrafficLight și o nouă stare (TrafficLightState) ca parametri în constructorul său.
Metoda execute() a clasei ChangeStateCommand schimbă starea semaforului, apelând metoda setState(TrafficLightState state) a obiectului TrafficLight pe care l-a primit în constructor.

```
public interface Command {

  void execute();
}
----------------------------------------------
----------------------------------------------
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
---------------------------------------------
---------------------------------------------
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

```
## Metoda Main
În metoda main din clasa Main, se demonstrează utilizarea design patterns:

Se creează un obiect TrafficLight și se adaugă un observator care afișează starea semaforului atunci când se schimbă.
Se creează trei comenzi (ChangeStateCommand) pentru a schimba stările semaforului la roșu, galben și verde.
Fiecare comandă este apelată prin metoda execute(), ceea ce duce la schimbarea stării semaforului și notificarea observatorului înregistrat, care afișează starea actualizată a semaforului.
```
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

```