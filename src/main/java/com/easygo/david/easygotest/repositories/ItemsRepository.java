package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item,Long> {
}
