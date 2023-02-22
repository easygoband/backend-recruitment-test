package com.easygo.david.easygotest.controllers.request;

import lombok.Data;

@Data
public class UpdateSurvivorRequest {
    private String first_name;
    private String second_name;
    private Integer age;
}
