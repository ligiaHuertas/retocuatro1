/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.retocuatro.crud;

import com.retocuatro.modelo.Vegetarian;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Maria Ligia Huertas Moreno
 */
public interface VegetarianCrudRepository extends MongoRepository<Vegetarian, String> {
    
     public List<Vegetarian> findByPriceLessThanEqual(double precio);
     
     
    
}
