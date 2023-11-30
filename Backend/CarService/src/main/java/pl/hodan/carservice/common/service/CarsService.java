package pl.hodan.carservice.common.service;

import org.springframework.stereotype.Service;
import pl.hodan.carservice.common.entity.Car;
import pl.hodan.carservice.common.exception.CarNotFoundException;
import pl.hodan.carservice.common.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarsService {
    private final CarRepository carRepository;
    private final ClientsService clientsService;


    public CarsService(CarRepository carRepository, ClientsService clientsService) {
        this.carRepository = carRepository;
        this.clientsService = clientsService;
    }

    public List<Car> getCarsByClientId(Long clientId) {
        clientsService.checkIfClientIdExist(clientId);

        return carRepository.findCarByClientId(clientId);
    }

    public boolean addCar(Long clientId, Car car) {
        setClientForCar(clientId, car);
        if (car != null) {
            carRepository.save(car);
            return true;
        }
        return false;
    }

    public boolean modifyCarWithClientIdByCarId(Long carId, Car newCar){//

        Optional<Car> car = Optional.ofNullable(checkIfCarIdExist(carId));
        if(car.isPresent()){
            car.get().setCarModel(newCar.getCarModel());
            car.get().setBodyType(newCar.getBodyType());
            car.get().setYearOfProduction(newCar.getYearOfProduction());
            car.get().setCarMileage(newCar.getCarMileage());
            car.get().setNumberVin(newCar.getNumberVin());
            car.get().setColor(newCar.getColor());

            carRepository.save(car.get());
            return true;
        }
        return false;
    }

    public boolean deleteCarWithClientIdByCarId(Long carId) {

        Optional<Car> car = Optional.ofNullable(checkIfCarIdExist(carId));
        if (car.isPresent()) {
            carRepository.deleteById(carId);
            return true;
        }
        return false;
    }
    public Car checkIfCarIdExist(Long carId){
        Optional<Car> car = carRepository.findById(carId);
        if(car.isPresent())
            return car.get();
        throw new CarNotFoundException("Client with id " + carId + "notExist");
    }

    private void setClientForCar(Long clientId, Car car) {
        car.setClient(clientsService.checkIfClientIdExist(clientId));
    }

//    private void checkIfCarIdExistByClientId(Long clientId, Long carId) {
//        clientsService.checkIfClientIdExist(clientId);
//        if (!carRepository.existsCarById(carId)) {
//            throw new CarNotFoundException("Car with id" + carId + "notExist");
//        }
//    }

}
