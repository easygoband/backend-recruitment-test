package com.easygo.david.easygotest.configuration;

import com.easygo.david.easygotest.models.Item;
import com.easygo.david.easygotest.repositories.ItemsRepository;
import com.easygo.david.easygotest.services.ItemsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
@AllArgsConstructor
public class Configuration {

    @Autowired
    private final ItemsRepository itemsRepository;

    @Bean
    public void CreateInitialItemsData(){
        itemsRepository.save(new Item("Water",4));
        itemsRepository.save(new Item("Food",3));
        itemsRepository.save(new Item("Medication",2));
        itemsRepository.save(new Item("Ammunition",1));
    }
}
