/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retocuatro.repositorio;

import com.retocuatro.crud.OrderCrudRepository;
import com.retocuatro.modelo.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Maria ligia Huertas moreno
 */
@Repository
public class OrderRepository {
    @Autowired
    private OrderCrudRepository orderCrudRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Order> getAll() {
        return (List<Order>) orderCrudRepository.findAll();
    }

    public Optional<Order> getOrder(int id) {
        return orderCrudRepository.findById(id);
    }

    public Order save(Order order) {
        return orderCrudRepository.save(order);
    }

    public void update(Order order) {
        orderCrudRepository.save(order);
    }

    public void delete(Order order) {
        orderCrudRepository.delete(order);
    }
    public List<Order> findByZone(String zone) {
        return orderCrudRepository.findByZone(zone);
    }
    
    public Optional<Order> lastOrderId(){
        return orderCrudRepository.findTopByOrderByIdDesc();
     }
    
    public List<Order> ordersSalesManById(int id){
        Query query = new Query();
        Criteria criterio =Criteria.where("salesMan.id").is(id);
        query.addCriteria(criterio);
        
        List<Order> orders = mongoTemplate.find(query, Order.class);
        
        return orders;
    }
     //ordenes por asesor y estado
    public List<Order> ordersSalesManByState(String state, int id){
        Query query = new Query();
        Criteria criterio =Criteria.where("salesMan.id").is(id)
                .and("status").is(state);
        query.addCriteria(criterio);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        
        return orders;
    }
    
    //ordenes de asesor por fecha
    public List<Order> ordersSalesManByDate(String dateStr, int id){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy-MM-dd");
        Query query = new Query();
        Criteria criterio =Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr,dtf).plusDays(2).atStartOfDay())
                .and("salesMan.id").is(id);
        query.addCriteria(criterio);
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
                
    }
   }
