package pl.mikolo.controllers;

import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mikolo.model.city.City;
import pl.mikolo.model.weather.WeatherModel;
import pl.mikolo.services.ApiWeatherService;
import pl.mikolo.services.CityService;

import java.util.List;

@RequestMapping("/")
@Controller
public class MainController {

    private CityService cityService;
    private ApiWeatherService apiWeatherService;

    @Autowired
    public MainController(CityService cityService, ApiWeatherService apiWeatherService) {
        this.cityService = cityService;
        this.apiWeatherService = apiWeatherService;
    }


    @GetMapping(value = {"/", "/weatherservice"})
    public String getMainPage(Model model) {
//        model.addAttribute("hello", "hello");
        return "index";
    }

    @PostMapping(value = "/findtemp")
    public String cityTable(@RequestParam String city, Model model) {
        if(StringUtils.isEmptyOrWhitespaceOnly(city)){
            model.addAttribute("text", "empty");
            return "index";
        }
        List<City> searchedCities = cityService.findListByName(city);
        if(searchedCities.size()>0){
            model.addAttribute("cityList", searchedCities);
            model.addAttribute("temp", 17.5);
        }else {
            model.addAttribute("notfound", "Location not found in weather service.");
        }
        model.addAttribute("chosenCity", city);

        return "index";
    }

    @GetMapping("/showtemp")
    public String showCityPage(@RequestParam("cityid") final Long id, Model model){
        City city = cityService.findById(id);
        WeatherModel weatherModel = apiWeatherService.getActualWeather(id);
        Double temperature = weatherModel.getMain().getTemp();
        model.addAttribute("temperature", temperature);
        model.addAttribute("city", city);
        return "citypage";
    }

}
