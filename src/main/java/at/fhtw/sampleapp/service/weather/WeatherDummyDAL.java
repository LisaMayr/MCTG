package at.fhtw.sampleapp.service.weather;

import at.fhtw.sampleapp.dal.User;
import at.fhtw.sampleapp.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class UserDummyDAL {
    private List<User> userData;

    public WeatherDummyDAL() {
        UserData = new ArrayList<>();
        userData.add(new Weather(1,"Europe","Vienna", 9.f));
        userData.add(new Weather(2,"Europe","Berlin", 8.f));
        userData.add(new Weather(3,"Asia","Tokyo", 12.f));
    }

    // GET /weather/:id
    public Weather getWeather(Integer ID) {
        Weather foundWaether = weatherData.stream()
                .filter(waether -> ID == waether.getId())
                .findAny()
                .orElse(null);

        return foundWaether;
    }

    // GET /weather
    public List<Weather> getWeather() {
        return weatherData;
    }

    // POST /weather
    public void addWeather(Weather weather) {
        weatherData.add(weather);
    }
}
