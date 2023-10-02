package com.tutorial.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tutorial.models.Bike;

@FeignClient(name = "bike-service", url= "http://localhost:8093")
public interface BikeFeignClient {
    
    /*
     * Llamamos al mentodo save de bike para guardar 
     * un nuevo bike por usuario
     */
    @RequestMapping(method = RequestMethod.POST, value = "/bike" , consumes = "application/json")
    Bike save(@RequestBody Bike bike);

    /*
     * Extraemos los bike por id_usuario
     */
    @RequestMapping(method = RequestMethod.GET, value ="/byuser/{userid}")
    List<Bike> getBikes(@PathVariable("userid") int userid);
}
