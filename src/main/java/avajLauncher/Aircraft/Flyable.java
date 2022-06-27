package avajLauncher.Aircraft;

import avajLauncher.Weather.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
