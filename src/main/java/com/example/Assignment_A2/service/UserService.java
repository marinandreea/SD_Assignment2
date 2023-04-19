package com.example.Assignment_A2.service;

import com.example.Assignment_A2.model.Role;
import com.example.Assignment_A2.model.User;
import com.example.Assignment_A2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkPresence(int idUser){

        boolean exists = false;

        List<User> users = (List<User>) userRepository.findAll();
        for(User u:users){
            if(u.getIdUser() == idUser){
                exists = true;
            }
        }
        return exists;

    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(u-> {
            if(u.getRole().equals(Role.STUDENT)){
                users.add(u);
            }
        });
        return users;
    }

    public Optional<User> getUserById(int id){
        var u = userRepository.findById(id);
        if(u.isPresent()){
            return userRepository.findById(id);
        }else{
            return null;
        }

    }

    public String addUser(User user){
        if(!checkPresence(user.getIdUser())){
            String encP = user.encryptedPassword(user.getPassword());
            user.setPassword(encP);
            user.setRole(Role.STUDENT);
            userRepository.save(user);
            return "A new user with id "+user.getIdUser()+" was created!";

        }else{
            return "A user with id " + user.getIdUser()+" was already created";
        }

    }

    public String updateUser(User user){

        var u = userRepository.findById(user.getIdUser());
        if(u.isPresent()){
            userRepository.save(user);
            return "User with id " + user.getIdUser() +" was updated successfully!";
        }else{
            return "User not found!";
        }
    }

    public String deleteUser(int id){
        var u = userRepository.findById(id);
        if(u.isPresent()){
            userRepository.deleteById(id);
            return "User with id " + id + " was successfully deleted!";
        }else{
            return "User not found!";
        }
    }

    public Optional<User> findByUsername(String username){

        List<User> users = getAllUsers();
        for(User u:users){
            if(u.getUsername().equals(username)){

                return Optional.of(u);

            }
        }

        return null;
    }

    public String registerStudent(User user){
        System.out.println(user.getUsername());
        var u = userRepository.findByUsername(user.getUsername());

        if(u.isPresent()){
            if(u.get().getToken().equals(user.getToken()) && u.get().getUsername().equals(user.getUsername())){
                u.get().setPassword(user.encryptedPassword(user.getPassword()));
                userRepository.save(u.get());
                return "Student registered successfully!";
            }else{
                return "Incorrect token-username combination!";
            }
        }else{
           return "Student with id " + user.getIdUser() + " was not found!";
        }
    }

    public String setTokenToStudent(int idUser, String token){
        var u = userRepository.findById(idUser);
        if(u.isPresent()){
            u.get().setToken(token);
            userRepository.save(u.get());
            return "Token introduced successfully!";
        }else{
            return "Student with id " + u.get().getIdUser() + " was not found!";
        }
    }

}
