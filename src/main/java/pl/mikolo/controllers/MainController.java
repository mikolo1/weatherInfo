package pl.mikolo.controllers;

import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mikolo.model.city.City;
import pl.mikolo.model.weather.WeatherModel;
import pl.mikolo.model.weatherForecast.WeatherForecastModel;
import pl.mikolo.services.IApiWeatherService;
import pl.mikolo.services.ICityService;

import java.util.List;

@Controller
public class MainController {

    private ICityService cityService;
    private IApiWeatherService apiWeatherService;

    @Autowired
    public MainController(ICityService cityService, IApiWeatherService apiWeatherService) {
        this.cityService = cityService;
        this.apiWeatherService = apiWeatherService;
    }


    @GetMapping(value = {"/", "/weatherservice"})
    public String getMainPage(Model model) {
        return "index";
    }

    @PostMapping(value = "/findtemp")
    public String cityTable(@RequestParam String city, Model model) {
        if (StringUtils.isEmptyOrWhitespaceOnly(city)) {
            model.addAttribute("text", "empty");
            return "index";
        }
        List<City> searchedCities = cityService.findListByName(city);
        if (searchedCities.size() > 0) {
            model.addAttribute("cityList", searchedCities);
        } else {
            model.addAttribute("notfound", "Location not found in weather service.");
        }
        model.addAttribute("chosenCity", city);

        return "index";
    }

    @GetMapping("/showtemp")
    public String showCityPage(@RequestParam("cityid") final Long id, @RequestParam("forecast") boolean showForecast, Model model) {
        City city = cityService.findById(id);
        WeatherModel weatherModel = apiWeatherService.getActualWeather(id);
        if(showForecast){
            WeatherForecastModel weatherForecastModel = apiWeatherService.getForecastWeather(id);
            model.addAttribute("forecastList", weatherForecastModel.getList());
        }
        Double temperature = weatherModel.getMain().getTemp();
        model.addAttribute("temperature", temperature);
        model.addAttribute("city", city);
        return "citypage";
    }

}
