package com.tutorial.bikeservice.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.repository.BikeRepository;

@Service
public class BikeService {
    
    @Autowired
    BikeRepository bikeRepository;

    /*
     * Listar bike
     */
    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    /*
     * Extraer bike por id
     */
    public Bike getBikeById(int id){
        return bikeRepository.findById(id).orElse(null);
    }

    /*
     * Guardar bike
     */
    public Bike save(Bike bike){
        Bike bikeNew= bikeRepository.save(bike);
        return bikeNew;
    }

    /*
     * Obtener bike por id usuario
     */
    public List<Bike> byUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }
}
