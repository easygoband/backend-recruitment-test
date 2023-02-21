package com.easygo.david.easygotest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

//@Table
@Entity(name = "survivors")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Survivor {
    @Id
    @Column(name = "id", unique = true, updatable = false)
    private UUID id;
    private String first_name;
    private String second_name;
    private Integer age;
    private Gender gender;

    public Survivor(String first_name, String second_name, Integer age, Gender gender) {
        this.id = UUID.randomUUID();
        this.first_name = first_name;
        this.second_name = second_name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Survivor survivor = (Survivor) o;
        return id != null && Objects.equals(id, survivor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
