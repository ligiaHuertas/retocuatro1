/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retocuatro.controlador;

import com.retocuatro.modelo.User;
import com.retocuatro.servicio.UserService;
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
@RequestMapping("/api/user")
@CrossOrigin(origins="*",methods={ RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
    @Autowired
    private UserService service;
    
     @GetMapping("/all")
    public List<User> getAll(){
       return service.getAll();
        
    }
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id")int id){
        return service.getUser(id);
    }
    
    @GetMapping("emailexist/{email}")
    public boolean emailExit(@PathVariable("email") String email){
        return service.emailExist(email);
        
    }
    @GetMapping("/{email}/{password}")
    public User auternticateUser(@PathVariable("email")String email, @PathVariable("password") String password){
        return service.autenticateUser(email,password);
    }
    
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user){
        return service.save(user);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user){
        return service.update(user);
    }    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id")int id){
        return service.delete(id);
    }
    
}
