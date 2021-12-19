/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retocuatro.servicio;

import com.retocuatro.modelo.Vegetarian;
import com.retocuatro.repositorio.VegetarianRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maria Ligia Huertas moreno
 */
@Service
public class VegetarianService {
    @Autowired

     private VegetarianRepository repositorio1;
    
    @Autowired
    
    public List<Vegetarian> getAll(){
        return repositorio1.getAll();       
    }
    
    public Optional<Vegetarian> getVegetarian(String reference){
        return repositorio1.getVegetarian(reference);
    }
    
    public Vegetarian save(Vegetarian vegetarian){
        if(vegetarian.getReference()== null){
            return vegetarian;
        }
        return repositorio1.save(vegetarian);
    }
    
    public Vegetarian update(Vegetarian vegetarian){
        if(vegetarian.getReference()!= null){
            Optional<Vegetarian> dbVegetarian = repositorio1.getVegetarian(vegetarian.getReference());
            if(!dbVegetarian.isEmpty()){
                if(vegetarian.getBrand()!= null){
                    dbVegetarian.get().setBrand(vegetarian.getBrand());
                }
                if(vegetarian.getCategory()!= null){
                    dbVegetarian.get().setCategory(vegetarian.getCategory());
                }
                if(vegetarian.getDescription()!= null){
                    dbVegetarian.get().setDescription(vegetarian.getDescription());
                }
                dbVegetarian.get().setAvailability(vegetarian.isAvailability());
                
                if(vegetarian.getPrice()!=0.0){
                    dbVegetarian.get().setPrice(vegetarian.getPrice());
                }
                if(vegetarian.getQuantity()!= 0){
                    dbVegetarian.get().setQuantity(vegetarian.getQuantity());
                }
                if(vegetarian.getPhotography()!= null){
                    dbVegetarian.get().setPhotography(vegetarian.getPhotography());
                }
                repositorio1.update(dbVegetarian.get());
                return dbVegetarian.get();
                
            }else{
                return vegetarian;
            }
        }      
        return vegetarian;
        
    }   
    
    public boolean delete(String reference){
        return getVegetarian(reference).map(vegetarian ->{
            repositorio1.delete(vegetarian);
            return true;
        }).orElse(false);
    }
    public List<Vegetarian> vegetariansByPrice(double price) {
        return repositorio1.vegetariansByPrice(price);
    }
}
