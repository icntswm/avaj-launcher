package avajLauncher;

import avajLauncher.Aircraft.Flyable;
import avajLauncher.Exceptions.WeatherTowerException;
import avajLauncher.Weather.WeatherTower;

public class Main {
    private static int numberOfSimulations;
    private static WeatherTower weatherTower;


    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Отсутствует файл с данными.");
            System.exit(1);
        }
        DataProcessor.redirectingOutputToFile();
        DataProcessor.initData(args[0]);
        numberOfSimulations = DataProcessor.getNumberOfSimulations();
        weatherTowerIdentificationAndRegistration();
        runSimulation();
    }
    private static void weatherTowerIdentificationAndRegistration() {
        weatherTower = new WeatherTower();

        for (Flyable aircraft : DataProcessor.getFlyables())
            aircraft.registerTower(weatherTower);
    }
    private static void runSimulation() {
        try {
            while (numberOfSimulations-- > 0 && weatherTower.getAircraftRegistered().size() > 0)
                weatherTower.changeWeather();
            DataProcessor.closeFiles();
        } catch (WeatherTowerException e) {
            System.err.println(e.getMessage());
        }
    }
}
