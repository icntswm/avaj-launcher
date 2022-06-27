package avajLauncher.Weather;

import avajLauncher.Aircraft.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();;
    private static final String[] weather = {"SUN", "SNOW", "FOG", "RAIN"};
    public WeatherProvider() {}

    public static WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }
    public String getCurrentWeather(Coordinates coordinates) {
        int sum = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();
        int rand;
        if (sum % 2 == 0) {
            rand = 1;
            while (rand < 2)
                rand = (int) (Math.random() * 4);
        }
        else
            rand = (int) (Math.random() * 2);
        return weather[rand];
    }
}
