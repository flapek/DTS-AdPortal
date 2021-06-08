package pl.edu.wat.portal_gloszeniowy.entities;


import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Car extends Offer{

    private String brand;
    private String model;
    private int year;
    private String version;
    private int mileage;
    private int capacity;
    private int hp;
    private String fuelType;
    private String transmission;
    private String color;
    private Boolean firstOwner;
    private Boolean no_accidents;
    private Boolean aso;
    private String condition;
}
