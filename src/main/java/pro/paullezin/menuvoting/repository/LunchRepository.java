package pro.paullezin.menuvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.menuvoting.model.Lunch;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface LunchRepository extends JpaRepository<Lunch, Integer> {

    @RestResource(rel = "by-date", path = "by-date")
    @Query("SELECT l FROM Lunch l WHERE l.menu.date=:date")
    List<Lunch> findByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @RestResource(rel = "by-menu-id", path = "by-menu-id")
    @Query("SELECT l FROM Lunch l WHERE l.menu.id=:id")
    List<Lunch> findByMenuId(Integer id);

    @Override
    @Transactional
    @Modifying
    @Secured("ROLE_ADMIN")
    Lunch save(Lunch lunch);

    @Override
    @Transactional
    @Modifying
    @Secured("ROLE_ADMIN")
    void deleteById(Integer id);
}
