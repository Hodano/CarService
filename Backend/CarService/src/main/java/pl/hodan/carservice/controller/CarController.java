package pl.hodan.carservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.entity.Car;
import pl.hodan.carservice.service.CarsService;

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

    @PostMapping("/add-car")
    public ResponseEntity addCar(@RequestParam Long clientId, @RequestBody Car car) {
        if (carsService.addCar(clientId, car))
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/modify-car")
    public ResponseEntity modifyCar(@RequestParam Long carId, @RequestBody Car car) {
        if (carsService.modifyCarWithClientIdByCarId(carId, car))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-car")
    public ResponseEntity deleteCar(@RequestParam Long carId) {
        if (carsService.deleteCarWithClientIdByCarId(carId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
