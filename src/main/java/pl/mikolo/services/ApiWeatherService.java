package pl.mikolo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.mikolo.model.weather.WeatherModel;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ApiWeatherService {

    private RestTemplate restTemplate;
    @Value("${api.key}")
    private String apiKey;

    @Value("${api.weather.url}")
    private String url;
    private Map<Long, WeatherModel> weatherDataMap;

    @Autowired
    public ApiWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.weatherDataMap = new HashMap<>();
    }

    public WeatherModel getActualWeather(long id) {
        Optional<WeatherModel> foundedData = weatherDataMap
                        .entrySet()
                        .stream()
                        .filter(e -> id == e.getKey())
                        .filter(element -> element.getValue().getUploadDateTime().isAfter(LocalDateTime.now().minusMinutes(60)))
                        .map(Map.Entry::getValue).findFirst();
        if (foundedData.isPresent()) {
            System.out.println("pogoda z mapy");
            return foundedData.get();
        } else {
            WeatherModel weatherModel = getWeatherModel(id);
            weatherDataMap.put(id, weatherModel);
            System.out.println("pogoda z API");
            return weatherModel;
        }
    }

    public WeatherModel getWeatherModel(long id) {
        String fullUrl = url.replace("{id}", String.valueOf(id)).replace("{appId}", apiKey);
        ResponseEntity<WeatherModel> weatherEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, null, WeatherModel.class);
        WeatherModel wm = weatherEntity.getBody();
        wm.setUploadDateTime(LocalDateTime.now());
        return wm;
    }
}