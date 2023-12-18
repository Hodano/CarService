package pl.hodan.carservice.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.common.entity.Car;
import pl.hodan.carservice.common.service.CarsService;

import java.util.List;

@RestController
@RequestMapping("/cars-service")
public class CarController {
    private final CarsService carsService;

    public CarController(CarsService carsService) {
        this.carsService = carsService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars(@RequestParam Long clientId) {
        List<Car> cars = carsService.getCarsByClientId(clientId);
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/car")
    public ResponseEntity<Car> getCarByCarId(@RequestParam Long carId){
        Car car = carsService.getCarByCarId(carId);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/add-car")
    public ResponseEntity<String> addCar(@RequestParam Long clientId, @RequestBody Car car) {
        if (carsService.addCar(clientId, car))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>("Car could not be added",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/modify-car")
    public ResponseEntity<String> modifyCarByCarId(@RequestParam Long carId, @RequestBody Car car) {
        if (carsService.modifyCarByCarId(carId, car))
            return new ResponseEntity<>("Car modified",HttpStatus.OK);
        return new ResponseEntity<>("Car not found or could not be modified",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-car")
    public ResponseEntity<String> deleteCar(@RequestParam Long carId) {
        if (carsService.deleteCarWithClientIdByCarId(carId))
            return new ResponseEntity<>("Car deleted",HttpStatus.OK);
        return new ResponseEntity<>("Car not found or could not be deleted",HttpStatus.NOT_FOUND);
    }
}
