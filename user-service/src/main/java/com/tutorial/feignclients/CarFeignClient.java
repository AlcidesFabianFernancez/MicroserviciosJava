package com.tutorial.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tutorial.models.Car;

@FeignClient(name = "car-service", url="http://localhost:8092")
//@RequestMapping("/car")
public interface CarFeignClient {
    
    /*
     * Llamamos al metodo guardar de Car
     * para guardar un nuevo car por usuario
     */
    @RequestMapping(method = RequestMethod.POST, value = "/car", consumes = "application/json")
    Car save(@RequestBody Car car);

    /*
     * Extraer bikes por id_usuario
     */
    @RequestMapping(method = RequestMethod.GET, value = "/car/byuser/{userid}")
    List<Car> getCars(@PathVariable("userid") int userid);
}
