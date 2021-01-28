package pro.paullezin.menuvoting.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.menuvoting.model.Menu;
import pro.paullezin.menuvoting.model.Vote;
import pro.paullezin.menuvoting.repository.UserRepository;
import pro.paullezin.menuvoting.repository.VoteRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public Optional<Vote> getForUserAndDate(int userId, LocalDate date) {
        return voteRepository.findByUserAndDate(userId, date);
    }

    @Transactional
    public VoteWithStatus save(int userId, final Menu menu) {
        LocalDate date = menu.getDate();
        VoteWithStatus voteWithStatus = voteRepository.findByUserAndDate(userId, date)
                .map(v -> {
                    v.setMenu(menu);
                    return new VoteWithStatus(v, false);
                })
                .orElseGet(() -> new VoteWithStatus(
                        new Vote(date, userRepository.getOne(userId), menu), true));

        voteRepository.save(voteWithStatus.getVote());
        return voteWithStatus;
    }

    @Transactional
    public VoteWithStatus saveIfAbsent(int userId, final Menu menu) {
        LocalDate date = menu.getDate();
        return voteRepository.findByUserAndDate(userId, date)
                .map(v -> new VoteWithStatus(v, false))
                .orElseGet(() -> new VoteWithStatus(voteRepository.save(new Vote(date, userRepository.getOne(userId), menu)), true));
    }

    public static class VoteWithStatus {
        private final Vote vote;
        private final boolean created;

        public VoteWithStatus(Vote vote, boolean updated) {
            this.vote = vote;
            this.created = updated;
        }

        public Vote getVote() {
            return vote;
        }

        public boolean isCreated() {
            return created;
        }
    }
}
