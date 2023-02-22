package com.easygo.david.easygotest.controllers.responses;

import com.easygo.david.easygotest.models.SurvivorInventory;

import java.util.ArrayList;
import java.util.List;


public class ExchangeResponse extends ResponseAction{
    public ExchangeResponse(String action, String id,  List<SurvivorInventory> exchange) {
        super(action, id);
        this.exchange.addAll(exchange);
    }

    List<SurvivorInventory> exchange = new ArrayList<>();
}
