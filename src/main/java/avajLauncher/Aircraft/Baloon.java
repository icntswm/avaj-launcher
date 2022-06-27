package avajLauncher.Aircraft;

import avajLauncher.Weather.WeatherTower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    protected Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.changeCoordinates = new HashMap<>(){{
            put("SUN", new ArrayList<>(){{
                addAll(Arrays.asList(0, 2, 2));
            }});
            put("RAIN", new ArrayList<>(){{
                addAll(Arrays.asList(0, 0, -5));
            }});
            put("FOG", new ArrayList<>(){{
                addAll(Arrays.asList(0, 0, -3));
            }});
            put("SNOW", new ArrayList<>(){{
                addAll(Arrays.asList(0, 0, -15));
            }});
        }};
    }

    @Override
    public void updateConditions() {
        String newWeather = weatherTower.getWeather(coordinates);
        System.out.print("Baloon#" + this.name + "(" + this.id + "): ");
        this.determinationCoordinateChanges(this.changeCoordinates.get(newWeather).get(0), this.changeCoordinates.get(newWeather).get(1),
                this.changeCoordinates.get(newWeather).get(2), newWeather);
        if (coordinates.getHeight() <= 0) {
            System.out.println("Baloon#" + this.name + "(" + this.id + "): приземлился.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        if (weatherTower == null) {
            System.out.println("Метеовышка не обнаружена");
            return;
        }
        this.weatherTower = weatherTower;
        weatherTower.register(this);
    }

}
