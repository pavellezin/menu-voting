package pro.paullezin.menuvoting.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@ToString
@NoArgsConstructor
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_restaurant")})
public class Restaurant extends NamedEntity {

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
