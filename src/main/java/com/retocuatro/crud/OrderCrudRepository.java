/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.retocuatro.crud;

import com.retocuatro.modelo.Order;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author Maria ligia Huertas Moreno
 */
public interface OrderCrudRepository extends MongoRepository<Order, Integer>{
    @Query("{'salesMan.zone': ?=}")
    List<Order> findByZone(final String zone);
    //Retorna las ordenes x status
    @Query("{Status: ?0}")
    List<Order> findByStatus(final String status);
    
    @Query("{Date: ?0}")
    List<Order> findByDate(final Date date);
    
    
     //Para seleccionar la orden con el id maximo
    Optional<Order> findTopByOrderByIdDesc();
    
    
    
}
