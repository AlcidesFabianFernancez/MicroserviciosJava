package com.tutorial.bikeservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.servicio.BikeService;

@RestController
@RequestMapping("/bike")
public class BikeControllers {
    
    @Autowired
    BikeService bikeService;

    /*
     * Lista bike
     */
    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bikes);
    }

    /*
     * obtener bike por id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int idbike){
        Bike bike = bikeService.getBikeById(idbike);
        if(bike == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    /*
     * Cargar bike
     */
    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike){
        Bike newBike = bikeService.save(bike);
        return ResponseEntity.ok(newBike);
    }

    /*
     * extraer bike por id usuario
     */
    @GetMapping("/byuser/{id}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("id") int idUser){
        List<Bike> bikes = bikeService.byUserId(idUser);
        // if(bikes.isEmpty()){
        //     return ResponseEntity.noContent().build();
        // }
        return ResponseEntity.ok(bikes);
    }
}
