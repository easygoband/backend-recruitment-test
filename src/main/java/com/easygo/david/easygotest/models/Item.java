package com.easygo.david.easygotest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity(name = "items")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @Column(name = "item_id", nullable = false)
    @SequenceGenerator(
            initialValue = 100,
            name = "item_id_sequence",
            sequenceName = "item_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_id_sequence"
    )
    private Long item_id;

    @NonNull
    private String name;

    @NonNull
    private Integer points;


    @OneToMany(mappedBy="item")
    @ToString.Exclude
    @JsonIgnore
    private Set<InventoryItemRecord> inventory_item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return Objects.equals(item_id, item.item_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
