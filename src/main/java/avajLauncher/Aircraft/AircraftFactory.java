package avajLauncher.Aircraft;

import java.util.*;

public class AircraftFactory {

    private static HashMap<String, List<String>> aircrafts = new LinkedHashMap<>();
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (!type.equals("Helicopter") && !type.equals("JetPlane") && !type.equals("Baloon")) {
            System.err.println("Транспортное средство типа '" + type + "' не поддерживается.");
            System.exit(1);
        }
        if (aircrafts.containsKey(type)) {
            List<String> nameAircrafts = aircrafts.get(type);
            for (int i = 0; i < nameAircrafts.size(); ++i) {
                if (nameAircrafts.get(i).equals(name)) {
                    System.out.println("Воздушное судно типа '" + type + "' с именем '" + name + "' уже в полете.");
                    return null;
                }
            }
        }
        if (!aircrafts.containsKey(type))
            aircrafts.put(type, new ArrayList<>(){{
                add(name);
            }});
        else
            aircrafts.get(type).add(name);
        if (height > 100)
            height = 100;
        switch (type) {
            case "Helicopter" : return new Helicopter(name, new Coordinates(longitude, latitude, height));
            case "JetPlane" : return new JetPlane(name, new Coordinates(longitude, latitude, height));
            case "Baloon" : return new Baloon(name, new Coordinates(longitude, latitude, height));
        }
        return null;
    }
    public static HashMap<String, List<String>> getAircrafts() {
        return aircrafts;
    }
}
