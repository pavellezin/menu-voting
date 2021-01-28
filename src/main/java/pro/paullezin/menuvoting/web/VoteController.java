package pro.paullezin.menuvoting.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.paullezin.menuvoting.AuthUser;
import pro.paullezin.menuvoting.model.Menu;
import pro.paullezin.menuvoting.model.Restaurant;
import pro.paullezin.menuvoting.service.VoteService;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api/vote", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {
    private static final LocalTime EXPIRED_TIME = LocalTime.parse("11:00");

    private final VoteService voteService;

    @RequestMapping(method = GET)
    public ResponseEntity<Restaurant> current(@AuthenticationPrincipal AuthUser authUser) {
        return voteService.getForUserAndDate(authUser.id(), LocalDate.now())
                .map(vote -> new ResponseEntity<>(vote.getMenu().getRestaurant(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Restaurant> vote(@PathVariable("id") Menu menu, @AuthenticationPrincipal AuthUser authUser) {
        LocalDate today = LocalDate.now();
        if (menu == null || !menu.getDate().equals(today)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int userId = authUser.id();
        boolean expired = LocalTime.now().isAfter(EXPIRED_TIME);
        VoteService.VoteWithStatus voteWithStatus = expired ?
                voteService.saveIfAbsent(userId, menu) :
                voteService.save(userId, menu);
        return new ResponseEntity<>(voteWithStatus.getVote().getMenu().getRestaurant(),
                voteWithStatus.isCreated() ? HttpStatus.CREATED : (expired ? HttpStatus.CONFLICT : HttpStatus.OK));
    }
}
