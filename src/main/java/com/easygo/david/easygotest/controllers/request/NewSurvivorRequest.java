package com.easygo.david.easygotest.controllers.request;

import com.easygo.david.easygotest.models.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
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

    @NonNull
    private Integer water;
    @NonNull
    private Integer food;
    @NonNull
    private Integer medication;
    @NonNull
    private Integer ammunition;
}
