package pro.paullezin.menuvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.menuvoting.model.Vote;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}