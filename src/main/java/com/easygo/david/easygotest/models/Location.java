package com.easygo.david.easygotest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "survivors_location")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @Column(name = "survivor_id", unique = true, updatable = false, nullable = false)
    @NonNull
    UUID survivor_id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    private Survivor survivor;

    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified")
    @NonNull
    private Date last_modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Location location = (Location) o;
        return Objects.equals(survivor_id, location.survivor_id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

