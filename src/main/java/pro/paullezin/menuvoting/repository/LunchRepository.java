package pro.paullezin.menuvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.menuvoting.model.Lunch;

@Transactional(readOnly = true)
public interface LunchRepository extends JpaRepository<Lunch, Integer> {
}
