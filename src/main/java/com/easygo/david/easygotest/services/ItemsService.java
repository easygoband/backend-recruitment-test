package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.exceptions.NotFoundException;
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
        var found = itemsRepository.findById(id);
        return found.orElseThrow(() ->
                new NotFoundException("Item with id: " + id + "NOT found!"));
    }

    public List<Item> findByName(String name) {
        var res = itemsRepository.findByName(name);
        if (res.isEmpty()) throw new NotFoundException("Item with nane: " + name + "NOT found!");
        return res;
    }
}
