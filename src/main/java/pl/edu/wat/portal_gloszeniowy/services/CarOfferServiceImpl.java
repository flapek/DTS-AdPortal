package pl.edu.wat.portal_gloszeniowy.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.portal_gloszeniowy.dtos.CarOfferRequestDto;
import pl.edu.wat.portal_gloszeniowy.dtos.CarOfferResponseDto;
import pl.edu.wat.portal_gloszeniowy.dtos.mappers.CarOfferMapper;
import pl.edu.wat.portal_gloszeniowy.entities.Car;
import pl.edu.wat.portal_gloszeniowy.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarOfferServiceImpl implements CarOfferService{

    private final CarRepository carRepository;
    private final CarOfferMapper carOfferMapper;


    @Override
    public CarOfferResponseDto getCarOffer(Long carOfferId) {
        Optional<Car> car = carRepository.findById(carOfferId);
        if(car.isPresent())
        {
            Car carEntity = car.get();
//            return carOfferMapper.toCarOfferResponseDto(carEntity);
            return null;
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public List<CarOfferResponseDto> getAllCarOffers() {
        return null;
    }

    @Override
    public void addCarOffer(CarOfferRequestDto carOfferRequestDto) {
        Car car = new Car();
        car.setTitle(carOfferRequestDto.getTitle());
        car.setPrice(carOfferRequestDto.getPrice());
        car.setBrand(carOfferRequestDto.getBrand());
        car.setModel(carOfferRequestDto.getModel());
        car.setYear(carOfferRequestDto.getYear());
        car.setVersion(carOfferRequestDto.getVersion());
        car.setMileage(carOfferRequestDto.getMileage());
        car.setCapacity(carOfferRequestDto.getCapacity());
        car.setHp(carOfferRequestDto.getHp());
        car.setFuelType(carOfferRequestDto.getFuelType());
        car.setTransmission(carOfferRequestDto.getTransmission());
        car.setColor(carOfferRequestDto.getColor());
        car.setFirstOwner(carOfferRequestDto.getFirstOwner());
        car.setNo_accidents(carOfferRequestDto.getNo_accidents());
        car.setAso(carOfferRequestDto.getAso());
        car.setCondition(carOfferRequestDto.getCondition());
        carRepository.save(car);
    }

    @Override
    public void updateCarOffer(CarOfferRequestDto carOfferRequestDto, Long carId) {

    }
}
