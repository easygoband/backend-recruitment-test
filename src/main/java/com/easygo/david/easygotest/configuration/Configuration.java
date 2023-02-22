package com.easygo.david.easygotest.configuration;

import com.easygo.david.easygotest.models.Item;
import com.easygo.david.easygotest.repositories.ItemsRepository;
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
        itemsRepository.save(new Item("water",4));
        itemsRepository.save(new Item("food",3));
        itemsRepository.save(new Item("medication",2));
        itemsRepository.save(new Item("ammunition",1));
    }
}
