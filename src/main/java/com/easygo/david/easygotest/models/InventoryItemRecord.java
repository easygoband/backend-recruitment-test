package com.easygo.david.easygotest.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "inventory_item")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class InventoryItemRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NonNull
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    @NonNull
    private Item item;

    @ManyToOne
    @JoinColumn(name="survivor_id", nullable=false)
    @NonNull
    private SurvivorInventory survivorInventory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InventoryItemRecord that = (InventoryItemRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
