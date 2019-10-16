package pl.mikolo.services;

import pl.mikolo.model.weather.WeatherModel;
import pl.mikolo.model.weatherForecast.WeatherForecastModel;

public interface IApiWeatherService {
    public WeatherModel getActualWeather(long id);
    public WeatherForecastModel getForecastWeather(long id);
}
