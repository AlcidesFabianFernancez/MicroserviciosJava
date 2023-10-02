package com.tutorial.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.entity.User;
import com.tutorial.models.Bike;
import com.tutorial.models.Car;
import com.tutorial.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    /*
     * Listar usuarios
     */
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    /*
     * Extraer usuario por id
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /*
     * Cargar usuario
     */
    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    /*
     * Extraer todos los carros por usuario
     */
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    /*
     * Extraer todos los bikes por usuario
     */
    @GetMapping("/bike/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Bike> bike = userService.getBikes(userId);
        return ResponseEntity.ok(bike);
    }

    /*
     * Guardar nuevo car por usuario
     */
    @PostMapping("/savecar/{userid}")
    public ResponseEntity<Car> saveCar(@PathVariable("userid") int userid, @RequestBody Car car){
        if(userService.getUserById(userid) == null){
            return ResponseEntity.notFound().build();
        }
        Car carNew = userService.saveCar(userid, car);
        return ResponseEntity.ok(carNew);
    }

    /*
     * Guardar nuevo bike por usuario
     */
    @PostMapping("/savebike/{userid}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userid") int userid, @RequestBody Bike bike){
         if(userService.getUserById(userid) == null){
            return ResponseEntity.notFound().build();
        }
        Bike bikeNew = userService.saveBike(userid, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getall/{userid}")
    public ResponseEntity<Map<String, Object>> getAllMovil(@PathVariable("userid") int userid){
        Map<String, Object> result = userService.getUserAndMovil(userid); 
        return ResponseEntity.ok(result);
    }
}
