package pro.paullezin.menuvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.paullezin.menuvoting.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}