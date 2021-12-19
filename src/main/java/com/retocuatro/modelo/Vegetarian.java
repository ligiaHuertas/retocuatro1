/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retocuatro.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Maria ligia Huertas Moreno
 */
@Document(collection ="vegetarians")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vegetarian {
    @Id
    private String reference;
    private String brand;
    private String category;
    private String description;
    private boolean availability ;
    private double price;
    private int quantity;
    private String photography;
    
}
