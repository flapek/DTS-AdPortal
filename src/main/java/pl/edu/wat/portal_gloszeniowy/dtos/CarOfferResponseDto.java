package pl.edu.wat.portal_gloszeniowy.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CarOfferResponseDto extends OfferResponseDto{
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


    public CarOfferResponseDto(Long id, String title, float price, List<TagResponseDto> tags, String brand, String model, int year, String version, int mileage, int capacity, int hp, String fuelType, String transmission, String color, Boolean firstOwner, Boolean no_accidents, Boolean aso, String condition) {
        super(id, title, price, tags);
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.version = version;
        this.mileage = mileage;
        this.capacity = capacity;
        this.hp = hp;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.color = color;
        this.firstOwner = firstOwner;
        this.no_accidents = no_accidents;
        this.aso = aso;
        this.condition = condition;
    }
}
