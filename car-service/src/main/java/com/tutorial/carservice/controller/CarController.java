package com.tutorial.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
    
    @Autowired
    CarService carService;

    /*
     * Listar carros
     */
    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.getAll();
        if(cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    /*
     * Extraer carro por id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id){
        Car car = carService.getCarById(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    /*
     * Cargar Car
     */
    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car carNew = carService.save(car);
        return ResponseEntity.ok(carNew);
    }

    /*
     * extraer car por id usuario
     */
    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int idUser){
        List<Car> cars = carService.byUserId(idUser);
        // if(cars.isEmpty()){
        //     return ResponseEntity.noContent().build();
        // }
        return ResponseEntity.ok(cars);

    }
}
