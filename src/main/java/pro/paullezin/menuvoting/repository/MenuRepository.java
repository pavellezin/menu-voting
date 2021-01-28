package pro.paullezin.menuvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.menuvoting.model.Menu;
import pro.paullezin.menuvoting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @RestResource(rel = "by-date", path = "by-date")
    @Query("SELECT m FROM Menu m WHERE m.date=:date")
    List<Menu> findByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @RestResource(rel = "by-restaurant", path = "by-restaurant")
    @Query("SELECT m FROM Menu m WHERE m.restaurant=:restaurant")
    List<Menu> findByRestaurant(Restaurant restaurant);

    @Override
    @Transactional
    @Modifying
    @Secured("ROLE_ADMIN")
    Menu save(Menu menu);

    @Override
    @Transactional
    @Modifying
    @Secured("ROLE_ADMIN")
    void deleteById(Integer id);
}
