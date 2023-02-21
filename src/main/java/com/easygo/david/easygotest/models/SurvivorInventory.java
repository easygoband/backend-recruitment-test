package com.easygo.david.easygotest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "survivor_inventory")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SurvivorInventory {
    @Id
    @Column(name = "survivor_id", unique = true, updatable = false, nullable = false)
    @NonNull
    UUID survivor_id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    private Survivor survivor;

    @NonNull
    private Integer total;

    @OneToMany(mappedBy="survivorInventory")
    @ToString.Exclude
    @JsonIgnore
    private Set<InventoryItemRecord> inventory_item;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SurvivorInventory that = (SurvivorInventory) o;
        return Objects.equals(survivor_id, that.survivor_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
