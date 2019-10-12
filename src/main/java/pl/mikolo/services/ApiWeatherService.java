package pl.mikolo.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Slf4j
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
        Optional<WeatherModel> inMemoryData = weatherDataMap
                .entrySet()
                .stream()
                .filter(e -> id == e.getKey() && e.getValue().getUploadDateTime().isAfter(LocalDateTime.now().minusMinutes(60)))
                .map(Map.Entry::getValue)
                .findFirst();
        if (inMemoryData.isPresent()) {
            log.info("Rekord załadowany z pamięci, czas załadowania: {}, temperatura: {} ", inMemoryData.get().getUploadDateTime(), inMemoryData.get().getMain().getTemp());
            return inMemoryData.get();
        } else {
            WeatherModel weatherModel = getWeatherModel(id);
            weatherDataMap.put(id, weatherModel);
            log.info("Rekord załadowany z API, czasa załadowania: {}, temperatura: {} ", weatherModel.getUploadDateTime(), weatherModel.getMain().getTemp());
            return weatherModel;
        }
    }

    private WeatherModel getWeatherModel(long id) {
        String fullUrl = url.replace("{id}", String.valueOf(id)).replace("{appId}", apiKey);
        ResponseEntity<WeatherModel> weatherEntity = restTemplate.exchange(fullUrl, HttpMethod.GET, null, WeatherModel.class);
        WeatherModel wm = weatherEntity.getBody();
        wm.setUploadDateTime(LocalDateTime.now());
        return wm;
    }
}