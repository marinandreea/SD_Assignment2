package com.example.Assignment_A2.controller;

import com.example.Assignment_A2.model.User;
import com.example.Assignment_A2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/useeer")
    public String testare(){
        return "Merge";
    }

    @RequestMapping("/student")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping("/student/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Optional<User> getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/student/create")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/student/update/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/student/delete/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/student/registerStudent")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String registerStudent(@RequestBody User user){
        return userService.registerStudent(user);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/student/setToken/{idUser}/{token}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String setTokenToStudent(@PathVariable int idUser, @PathVariable String token){
        return userService.setTokenToStudent(idUser,token);
    }
}
