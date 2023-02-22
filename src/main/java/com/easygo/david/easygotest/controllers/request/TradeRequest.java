package com.easygo.david.easygotest.controllers.request;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TradeRequest {
    Map<String,Integer> sendItems = new HashMap<>();
    Map<String,Integer> getItems = new HashMap<>();
}
