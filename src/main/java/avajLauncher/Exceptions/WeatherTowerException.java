package avajLauncher.Exceptions;

public class WeatherTowerException extends Exception{
    public WeatherTowerException() {
        super("Не удалось обнаружить метеовышку поблизовсти");
    }
}
