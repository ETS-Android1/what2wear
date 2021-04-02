package com.example.what2wear.models.clothing;

import com.example.what2wear.constant.GenderEnum;

public class Top extends Wearable {
  public Top(String name, String fileName, double minTemp, double maxTemp, GenderEnum gender) {
    super(name, fileName, minTemp, maxTemp, gender);
  }
}
