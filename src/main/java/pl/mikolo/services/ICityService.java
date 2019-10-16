package pl.mikolo.services;

import pl.mikolo.model.city.City;

import java.util.List;

public interface ICityService {
    public List<City> findListByName(String cityName);
    public List<City> findPageByName(String cityName, long page, long rowsOnPage);
    public City findById(Long id);
}
