package com.easygo.david.easygotest.controllers.request;

import com.easygo.david.easygotest.models.Gender;
import lombok.Data;
import lombok.NonNull;

@Data
public class NewSurvivorRequest {
    @NonNull
    private String first_name;
    @NonNull
    private String second_name;
    @NonNull
    private Integer age;
    @NonNull
    private Gender gender;

    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
}
