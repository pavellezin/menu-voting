package pro.paullezin.menuvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.menuvoting.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @RestResource(rel = "by-name", path = "by-name")
    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE CONCAT('%',:name,'%')")
    List<Restaurant> findByName(String name);

    @Override
    @Transactional
    @Modifying
    @Secured("ROLE_ADMIN")
    Restaurant save (Restaurant restaurant);

    @Override
    @Transactional
    @Modifying
    @Secured("ROLE_ADMIN")
    void deleteById(Integer id);
}
