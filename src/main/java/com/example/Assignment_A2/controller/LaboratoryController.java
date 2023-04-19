package com.example.Assignment_A2.controller;

import com.example.Assignment_A2.model.Laboratory;
import com.example.Assignment_A2.model.User;
import com.example.Assignment_A2.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @RequestMapping("/lab")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<Laboratory> getAllLaboratories(){
        return laboratoryService.getAllLaboratories();
    }

    @RequestMapping("/lab/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Optional<Laboratory> getLab(@PathVariable int id){
        return laboratoryService.getLabById(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/lab/create")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String addLab(@RequestBody Laboratory laboratory){
        return laboratoryService.addLab(laboratory);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/lab/update")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String updateLab(@RequestBody Laboratory laboratory){
        return laboratoryService.updateLab(laboratory);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/lab/delete/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteLab(@PathVariable int id){
        return laboratoryService.deleteLab(id);
    }
}
