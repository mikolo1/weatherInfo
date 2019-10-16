package pl.mikolo.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pl.mikolo.model.city.City;
import pl.mikolo.repos.CitiesRepository;

import javax.persistence.NoResultException;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CityService implements ICityService{

    private Gson gson;
    private CitiesRepository citiesRepository;
    private Type type;
    private ResourceLoader resourceLoader;

    @Autowired
    public CityService(Gson gson, CitiesRepository citiesRepository, ResourceLoader resourceLoader) {
        this.gson = gson;
        this.citiesRepository = citiesRepository;
        this.resourceLoader = resourceLoader;
        type = new TypeToken<List<City>>() {
        }.getType();
    }

    @Override
    public List<City> findListByName(String cityName) {
        try {
            String path = "C:\\Users\\Mikołaj\\IdeaProjects\\weathertemp\\src\\main\\resources\\city.list.json";
//            InputStream weatherJson = resourceLoader.getResource("classpath:resources/city.list.json").getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(weatherJson, StandardCharsets.UTF_8));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

            List<City> cityList = gson.fromJson(reader, type);
            return cityList.stream()
                    .filter(c -> c.getName().toLowerCase().contains(cityName.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new NoResultException("brak miasta w pliku");
    }

    @Override
    public List<City> findPageByName(String cityName, long page, long rowsOnPage) {
        try {
            String path = "C:\\Users\\Mikołaj\\IdeaProjects\\weathertemp\\src\\main\\resources\\city.list.json";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            List<City> cityList = gson.fromJson(reader, type);
            return cityList.stream()
                    .filter(c -> c.getName().toLowerCase().contains(cityName.toLowerCase()))
                    .skip(page*rowsOnPage)
                    .limit(rowsOnPage)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new NoResultException("brak miasta w pliku");
    }

    @Override
    public City findById(Long id) {
        try {
            String path = "C:\\Users\\Mikołaj\\IdeaProjects\\weathertemp\\src\\main\\resources\\city.list.json";
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
//            FileReader fileReader = new FileReader("C:\\Users\\Mikołaj\\IdeaProjects\\weathertemp\\src\\main\\resources\\city.list.json");
            List<City> cityList = gson.fromJson(reader, type);
            return cityList.stream().filter(c -> id == c.getId()).findFirst().get();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new NoResultException("brak miasta w pliku");
    }
}
