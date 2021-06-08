package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarOfferRequestDto extends OfferRequestDto{
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
