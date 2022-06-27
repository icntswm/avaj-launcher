package avajLauncher.Weather;

import avajLauncher.Aircraft.Baloon;
import avajLauncher.Aircraft.Helicopter;
import avajLauncher.Aircraft.JetPlane;
import avajLauncher.Exceptions.WeatherTowerException;
import avajLauncher.Aircraft.Flyable;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower{
    private String news;

    private List<Flyable> aircraftRegistered;

    private void aircraftDataPrint(Flyable flyable) {
        System.out.print("Tower says: ");
        if (flyable instanceof Helicopter)
            System.out.print("Helicopter#" + ((Helicopter) flyable).getName() + "(" + ((Helicopter) flyable).getId() + ")");
        else if (flyable instanceof JetPlane)
            System.out.print("JetPlane#" + ((JetPlane) flyable).getName() + "(" + ((JetPlane) flyable).getId() + ")");
        else if (flyable instanceof Baloon)
            System.out.print("Baloon#" + ((Baloon) flyable).getName() + "(" + ((Baloon) flyable).getId() + ")");
    }
    public void register(Flyable flyable) {
        if (flyable != null) {
            if (aircraftRegistered == null)
                aircraftRegistered = new CopyOnWriteArrayList<>();
            aircraftDataPrint(flyable);
            if (aircraftRegistered.contains(flyable)) {
                System.out.println(" уже зарегистрировано.");
                return;
            } else {
                System.out.println(" зарегистрировано в метеостанции.");
                aircraftRegistered.add(flyable);
            }
        }
    }
    public void unregister(Flyable flyable) {
        if (flyable != null) {
            aircraftDataPrint(flyable);
            if (!aircraftRegistered.contains(flyable)) {
                System.out.println(" не было зарегистрировано в метеостанции.");
                return;
            }
            System.out.println(" теперь не зарегистрировано в метеостанции.");
            aircraftRegistered.remove(flyable);
        }
    }
    protected void conditionsChanged() throws WeatherTowerException {
        if (aircraftRegistered == null)
            throw new WeatherTowerException();
        for (Flyable aircraft : aircraftRegistered) {
            aircraft.updateConditions();
        }
    }
    public List<Flyable> getAircraftRegistered() {
        return aircraftRegistered;
    }
}
