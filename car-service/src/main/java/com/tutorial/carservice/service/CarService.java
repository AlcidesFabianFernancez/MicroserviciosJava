package com.tutorial.carservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.carservice.repository.CarRepository;

import com.tutorial.carservice.entity.Car;

@Service
public class CarService {
    
    @Autowired
    CarRepository carRepository;

    /*
     * Listar los Carros
     */
    public List<Car> getAll(){
        return carRepository.findAll();
    }

    /*
     * Obtener carro por id
     */
    public Car getCarById(int id){
        return carRepository.findById(id).orElse(null);
    }

    /*
     * Guardar carro
     */
    public Car save(Car car){
        Car carNew = carRepository.save(car);
        return carNew;
    }

    /*
     * Obtener carros por id usario
     */
    public List<Car> byUserId(int userId){
        return carRepository.findByUserId(userId);
    }
}
