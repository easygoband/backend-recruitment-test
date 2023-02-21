package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.models.Item;
import com.easygo.david.easygotest.repositories.ItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemsService {
    @Autowired
    private final ItemsRepository itemsRepository;

    public List<Item> getAllItems() {
        return itemsRepository.findAll();
    }

    public Item getItemById(Long id) {
        if (id == null) throw new IllegalStateException("ID can't be null");

        var svr = itemsRepository.findById(id);
        if (svr.isPresent()) return svr.get();
        else throw new IllegalStateException("Item with ID " + id + "not exits");
    }
}
