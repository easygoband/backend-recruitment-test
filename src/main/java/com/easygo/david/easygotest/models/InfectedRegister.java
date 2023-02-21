package com.easygo.david.easygotest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "infected_register")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class InfectedRegister {
    @Id
    @Column(name = "survivor_id", nullable = false)
    @NonNull
    private UUID survivor_id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    private Survivor survivor;

    @NonNull
    private Boolean infected;

    @NonNull
    private Integer infected_alerts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InfectedRegister that = (InfectedRegister) o;
        return Objects.equals(survivor_id, that.survivor_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
