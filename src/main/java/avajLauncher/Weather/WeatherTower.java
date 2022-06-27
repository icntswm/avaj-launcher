package avajLauncher.Weather;

import avajLauncher.Aircraft.Coordinates;
import avajLauncher.Exceptions.WeatherTowerException;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(coordinates);
    }
    public void changeWeather() throws WeatherTowerException {
        this.conditionsChanged();
    }
}
