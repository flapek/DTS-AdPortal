package pl.edu.wat.portal_gloszeniowy.dtos.mappers;

import lombok.NoArgsConstructor;
import pl.edu.wat.portal_gloszeniowy.dtos.CarOfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.entities.Car;

public class CarOfferMapper {

    public CarOfferMapper() {
    }

    public CarOfferResponseDto toCarOfferResponseDto(Car car)
    {
        CarOfferResponseDto carOfferResponseDto = new CarOfferResponseDto();
        carOfferResponseDto.setId(car.getId());
        carOfferResponseDto.setTitle(car.getTitle());
        carOfferResponseDto.setPrice(car.getPrice());
        carOfferResponseDto.setBrand(car.getBrand());
        carOfferResponseDto.setModel(car.getModel());
        carOfferResponseDto.setYear(car.getYear());
        carOfferResponseDto.setVersion(car.getVersion());
        carOfferResponseDto.setMileage(car.getMileage());
        carOfferResponseDto.setCapacity(car.getCapacity());
        carOfferResponseDto.setHp(car.getHp());
        carOfferResponseDto.setFuelType(car.getFuelType());
        carOfferResponseDto.setTransmission(car.getTransmission());
        carOfferResponseDto.setColor(car.getColor());
        carOfferResponseDto.setFirstOwner(car.getFirstOwner());
        carOfferResponseDto.setNo_accidents(car.getNo_accidents());
        carOfferResponseDto.setAso(car.getAso());
        carOfferResponseDto.setCondition(car.getCondition());
        return carOfferResponseDto;
    }
}
