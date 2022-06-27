package avajLauncher.Aircraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0;
    protected HashMap<String, List<Integer>> changeCoordinates;
    private static final HashMap<String, ArrayList<String>> messageFromAircraft = new HashMap<>(){{
        put("SUN", new ArrayList<>() {{
            add("очень сильно печет, поднимемся немного.");
            add("мы как на сковородке, набираем высоту!");
            add("ох, сегодня жаркий денек.");
        }});
        put("RAIN", new ArrayList<>(){{
            add("поливает как из ведра, наберем высоту, пожалуй.");
            add("в прогнозе погоды дождя не было.");
            add("день обещал быть хорошим, но увы, дождь...");
        }});
        put("FOG", new ArrayList<>(){{
            add("давно не видел такого тумана.");
            add("видимость практически нулевая!");
            add("какой-то слишком необычный туман, как из фильма ужасов!");
        }});
        put("SNOW", new ArrayList<>(){{
            add("сразу новогоднее настроение появилось.");
            add("очень сильный снегопад, давно такого не было.");
            add("нужно снижаться, иначе нас тут завалит снегом!");
        }});
        put("LANDED", new ArrayList<>(){{
            add("думаю стоит приземляться, погода ужасная.");
        }});
    }};
    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
        this.changeCoordinates = null;
    }
    protected void determinationCoordinateChanges(int longitude, int latitude, int height, String newWeather) {
        coordinates.setLongitude(coordinates.getLongitude() + longitude);
        coordinates.setLatitude(coordinates.getLatitude() + latitude);
        coordinates.setHeight(coordinates.getHeight() + height);
        int rand = (int) (Math.random() * 3);
        if (coordinates.getHeight() > 100)
            coordinates.setHeight(100);
        else if (coordinates.getHeight() <= 0) {
            newWeather = "LANDED";
            rand = 0;
        }
        System.out.println(messageFromAircraft.get(newWeather).get(rand));
    }
    private long nextId() {
        return ++idCounter;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
