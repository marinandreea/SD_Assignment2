package com.example.Assignment_A2.service;

import com.example.Assignment_A2.model.Laboratory;
import com.example.Assignment_A2.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public boolean checkPresence(int nrLab){

        boolean exists = false;

       List<Laboratory> laboratories = (List<Laboratory>) laboratoryRepository.findAll();
       for(Laboratory l:laboratories){
           if(l.getNrLab() == nrLab){
               exists = true;
           }
       }
       return exists;

    }

    public List<Laboratory> getAllLaboratories(){
        List<Laboratory> laboratories = new ArrayList<>();
        laboratoryRepository.findAll().forEach(u-> laboratories.add(u));
        return laboratories;
    }

    public Optional<Laboratory> getLabById(int id){
        var lab = laboratoryRepository.findById(id);
        if(lab.isPresent()){
            return laboratoryRepository.findById(id);
        }
       return null;
    }

    public Optional<Laboratory> getLabByNr(int nr){
        var lab = laboratoryRepository.findById(nr);
        if(lab.isPresent()){
            return laboratoryRepository.findById(nr);
        }
        return null;
    }

    public String addLab(Laboratory laboratory){

        if(!checkPresence(laboratory.getNrLab())){
            laboratoryRepository.save(laboratory);
            return "Laboratory created successfully!";
        }else{
            return "A laboratory with the same number was previously created!";
        }

    }

    public String updateLab(Laboratory laboratory){

        var lab = laboratoryRepository.findById(laboratory.getIdLab());
        if(lab.isPresent()){
            laboratoryRepository.save(laboratory);
            return "Laboratory with id " + laboratory.getIdLab() +" was updated successfully!";
        }else{
            return "Laboratory not found!";
        }

    }

    public String deleteLab(int id){
        var lab = laboratoryRepository.findById(id);
        if(lab.isPresent()){
            laboratoryRepository.deleteById(id);
            return "Laboratory with id " + id + " was successfully deleted!";
        }else{
            return "Laboratory not found!";
        }

    }
}
