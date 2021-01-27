package pro.paullezin.menuvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.menuvoting.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
