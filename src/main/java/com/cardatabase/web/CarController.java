package com.cardatabase.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cardatabase.domain.Car;
import com.cardatabase.domain.CarRepository;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController

public class CarController {
    private final CarRepository repository;

    public CarController(CarRepository repository){
        this.repository = repository;
    }

    // 1.Read, get all cars list
    @GetMapping("/cars")
    public Iterable<Car> getCars(){
        return repository.findAll();
    }


    // 2. Add a new car
    /*@PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return repository.save(car);  //Saves the car and returns the saved entity
    }*/
    @PostMapping("/cars")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        // 检查注册号是否已经存在
        if (repository.findByRegistrationNumber(car.getRegistrationNumber()).isPresent()) {
            // 如果存在，返回 409 Conflict
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Registration number already exists.");
        }
        // 否则，保存汽车并返回
        Car savedCar = repository.save(car);
        return ResponseEntity.ok(savedCar);
    }



    // 3. READ - 根据 ID 获取特定的汽车
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = repository.findById(id);
        return car.map(ResponseEntity::ok)  // 如果找到汽车，返回 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());  // 如果没找到，返回 404 Not Found
    }

    // 4. UPDATE - 更新汽车信息
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return repository.findById(id)
                .map(car -> {
                    car.setBrand(updatedCar.getBrand());  // 更新汽车信息
                    car.setModel(updatedCar.getModel());
                    car.setColor(updatedCar.getColor());
                    car.setModelYear(updatedCar.getModelYear());
                    car.setPrice(updatedCar.getPrice());
                    car.setRegistrationNumber(updatedCar.getRegistrationNumber());
                    repository.save(car);  // 保存更新后的汽车
                    return ResponseEntity.ok(car);  // 返回 200 OK
                })
                .orElseGet(() -> ResponseEntity.notFound().build());  // 如果汽车不存在，返回 404
    }

    // Add processing logic for DELETE requests
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        // Check if the car exists in the database
        if (repository.existsById(id)) {
            repository.deleteById(id);  // Delete the car
            return ResponseEntity.noContent().build();  // Returns 204 No Content status
        } else {
            return ResponseEntity.notFound().build();  // If the car does not exist, return a 404 error
        }
    }
}
