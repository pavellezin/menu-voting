package pro.paullezin.menuvoting.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_date", "restaurant_id"}, name = "unique_menu")})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Menu extends BaseEntity {

    @NotNull
    @Column(name = "menu_date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Menu(Integer id, Restaurant restaurant, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
    }

}
