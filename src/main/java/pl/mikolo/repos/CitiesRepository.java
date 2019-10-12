package pl.mikolo.repos;

import org.springframework.stereotype.Repository;
import pl.mikolo.model.city.City;
import pl.mikolo.utils.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CitiesRepository {

    public List<City> findAll() {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<City> cityList = em.createQuery("select t from City t", City.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return cityList;
    }

    public City findById(Long id) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        City city = em.find(City.class, id);
        em.getTransaction().commit();
        em.close();
        return city;
    }

    public void save(City city) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();
        em.close();
    }

    public void saveList (List<City> cities){
        cities.forEach(c->save(c));
    }

    public void deleteById(Long id) {
        EntityManager em = PersistenceUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        City city = em.find(City.class, id);
        em.remove(city);
        em.getTransaction().commit();
        em.close();
    }
}
