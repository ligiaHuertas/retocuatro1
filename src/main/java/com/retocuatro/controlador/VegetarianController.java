/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retocuatro.controlador;

import com.retocuatro.modelo.Vegetarian;
import com.retocuatro.servicio.VegetarianService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kika
 */
@RestController
@RequestMapping("/api/vegetarian")
@CrossOrigin(origins="*",methods={ RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class VegetarianController {
     @Autowired
    private VegetarianService service1;
    
    @GetMapping("/all")
    public List<Vegetarian> getAll(){
       return service1.getAll(); 
    }   

    @GetMapping("/{reference}")
    public Optional<Vegetarian> getVegetarian(@PathVariable("reference")String reference){
        return service1.getVegetarian(reference);
        
    }
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Vegetarian save(@RequestBody Vegetarian vegetarian){
        return service1.save(vegetarian);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Vegetarian update(@RequestBody Vegetarian vegetarian){
        return service1.update(vegetarian);
    }    
    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("reference") String reference){
        return service1.delete(reference);
    }
     @GetMapping("/price/{price}")
    public List<Vegetarian> VegetariansByPrice(@PathVariable("price") double precio) {
        return service1.vegetariansByPrice(precio);
    }
}
