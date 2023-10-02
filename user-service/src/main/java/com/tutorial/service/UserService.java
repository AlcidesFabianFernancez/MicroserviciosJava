package com.tutorial.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tutorial.entity.User;
import com.tutorial.feignclients.BikeFeignClient;
import com.tutorial.feignclients.CarFeignClient;
import com.tutorial.models.Bike;
import com.tutorial.models.Car;
import com.tutorial.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    /*
     * Listar Usuarios
     */
    public List<User> getAll(){
        return userRepository.findAll();
    }

    /*
     * Extraer usuario por id
     */
    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    /*
     * Guardar usuario
     */
    public User save(User user){
        User userNew = userRepository.save(user);
        return userNew;
    }

    /*
     * Obtenemos todos los carros por id usuario
     */
    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://localhost:8092/car/byuser/" +userId, List.class);
        return cars;
    }

     /*
     * Obtenemos todos los bikes por id usuario
     */
    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8093/bike/byuser/" +userId, List.class);
        return bikes;
    }

    /*
     * Cargamos nuevo Car por usuario
     */
    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    }

    /*
     * Cargamos nuevo bike por usuario
     */
    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike newBike = bikeFeignClient.save(bike);
        return newBike;
    }


    /*
     * Extraemos Car por usuario
     */
    public Map<String, Object> getUserAndMovil(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars= carFeignClient.getCars(userId);
        if(cars.isEmpty()){
            result.put("Cars", "Ese user no tiene cars");
        }
        else{
            result.put("Cars", cars);
        }

        List<Bike> bikes= bikeFeignClient.getBikes(userId);
        if(bikes.isEmpty()){
            result.put("BiKes", "Ese user no tiene Bikes");
        }
        else{
            result.put("Bikes", bikes);
        }
        return result;
    }


    
}
