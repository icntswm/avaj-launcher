package avajLauncher.Aircraft;

import avajLauncher.Weather.WeatherTower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    protected JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.changeCoordinates = new HashMap<>(){{
            put("SUN", new ArrayList<>(){{
                addAll(Arrays.asList(10, 0, 2));
            }});
            put("RAIN", new ArrayList<>(){{
                addAll(Arrays.asList(5, 0, 0));
            }});
            put("FOG", new ArrayList<>(){{
                addAll(Arrays.asList(1, 0, 0));
            }});
            put("SNOW", new ArrayList<>(){{
                addAll(Arrays.asList(0, 0, -7));
            }});
        }};
    }

    @Override
    public void updateConditions() {
        String newWeather = weatherTower.getWeather(coordinates);
        System.out.print("JetPlane#" + this.name + "(" + this.id + "): ");
        this.determinationCoordinateChanges(this.changeCoordinates.get(newWeather).get(0), this.changeCoordinates.get(newWeather).get(1),
                this.changeCoordinates.get(newWeather).get(2), newWeather);
        if (coordinates.getHeight() <= 0) {
            System.out.println("JetPlane#" + this.name + "(" + this.id + "): приземлился.");
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
