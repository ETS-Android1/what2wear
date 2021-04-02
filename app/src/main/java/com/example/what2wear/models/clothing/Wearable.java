package com.example.what2wear.models.clothing;

import com.example.what2wear.constant.GenderEnum;

public class Wearable {
  private String name;
  private String fileName;
  private double minTemp;
  private double maxTemp;
  private GenderEnum gender;

  public Wearable(String name, String fileName, double minTemp, double maxTemp, GenderEnum gender) {
    this.name = name;
    this.fileName = fileName;
    this.minTemp = minTemp;
    this.maxTemp = maxTemp;
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public double getMinTemp() {
    return minTemp;
  }

  public void setMinTemp(double minTemp) {
    this.minTemp = minTemp;
  }

  public double getMaxTemp() {
    return maxTemp;
  }

  public void setMaxTemp(double maxTemp) {
    this.maxTemp = maxTemp;
  }

  public GenderEnum getGender() {
    return gender;
  }

  public void setGender(GenderEnum gender) {
    this.gender = gender;
  }
}
