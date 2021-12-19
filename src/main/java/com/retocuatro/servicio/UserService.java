/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.retocuatro.servicio;

import com.retocuatro.modelo.User;
import com.retocuatro.repositorio.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maria Ligia Huertas Moreno
 */
@Service
public class UserService {
    @Autowired
    private UserRepository repositorio;
    
    public List<User> getAll(){
        return repositorio.getAll();       
    }
    
    public Optional<User> getUser(int id){
        return repositorio.getUser(id);
    }
     public User save(User user) {
        
        //obtiene el maximo id existente en la coleccion
        Optional<User> userIdMaximo = repositorio.lastUserId();
        
        //si el id del Usaurio que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (user.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (userIdMaximo.isEmpty())
                user.setId(1);
            //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo del usuario
            else
                user.setId(userIdMaximo.get().getId() + 1);
        }
        
        Optional<User> dbUser =repositorio.getUser(user.getId());
        if (dbUser.isEmpty()) {
            if (emailExist(user.getEmail())==false){
                return repositorio.save(user);
            }else{
                return user;
            }
        }else{
            return user;
        }
        
    }
    
    public User update(User user){
        if(user.getId() != null){
            Optional<User> dbUser =repositorio.getUser(user.getId());
            if(!dbUser.isEmpty()){
                if(user.getIdentification()!=null){
                    dbUser.get().setIdentification(user.getIdentification());
                }
                if(user.getName()!=null){
                    dbUser.get().setName(user.getName());
                }
                if(user.getAddress()!=null){
                    dbUser.get().setAddress(user.getAddress());
                }
                if(user.getCellPhone()!=null){
                    dbUser.get().setCellPhone(user.getCellPhone());   
                }
                if(user.getEmail()!=null){
                    dbUser.get().setEmail(user.getEmail());
                }
                if(user.getPassword()!=null){
                    dbUser.get().setPassword(user.getPassword());
                }
                if(user.getZone()!=null){
                    dbUser.get().setZone(user.getZone());
                }
                if(user.getType()!=null){
                    dbUser.get().setType(user.getType());
                }
                repositorio.update(dbUser.get());
                return dbUser.get();
            }else{
                return user;
            }
            
        }
        return user;
    }
    
    public boolean emailExist(String email){
        return repositorio.emailExist(email);
    }
    
    public boolean delete(int userId){
        Boolean userBoolean = getUser(userId).map(user ->{
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return userBoolean; 
    }
    
    
    public User autenticateUser(String email,String password){
        Optional<User> user = repositorio.autenticateUser(email,password);
        if(user.isEmpty()){
            return new User();
        }else{
            return user.get();
        }
    }
}
