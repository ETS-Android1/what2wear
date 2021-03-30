
package com.example.what2wear.models.weather;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Clouds {

    @SerializedName("all")
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }


}
