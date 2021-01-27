package pro.paullezin.menuvoting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lunch", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "name"}, name = "unique_lunch")})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Lunch extends NamedEntity {

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    public Lunch(Integer id, String name, int priceInCents) {
        super(id, name);
        this.price = priceInCents;
    }
}
