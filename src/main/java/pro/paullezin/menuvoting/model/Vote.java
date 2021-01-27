package pro.paullezin.menuvoting.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "unique_vote")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Vote extends BaseEntity {

    @NotNull
    @Column(name = "vote_date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

}
