package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item,Long> {
    List<Item> findByName(String name);
}
