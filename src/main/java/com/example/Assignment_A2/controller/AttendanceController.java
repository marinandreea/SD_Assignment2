package com.example.Assignment_A2.controller;

import com.example.Assignment_A2.model.Attendance;
import com.example.Assignment_A2.model.dto.AttendanceDTO;
import com.example.Assignment_A2.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping("/attendance/{idLab}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<Attendance> getAttendanceForALab(@PathVariable int idLab){
        return attendanceService.displayAttendanceForALab(idLab);
    }

    @RequestMapping("/attendance")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<Attendance> getAllAttendances(){
        return attendanceService.getAllAttendances();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/attendance/create")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String addAttendance(@RequestBody AttendanceDTO attendanceDTO){
       return attendanceService.addAttendance(attendanceDTO);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/attendance/update")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String updateUser(@RequestBody AttendanceDTO attendanceDTO){
       return attendanceService.updateAttendanceForALab(attendanceDTO);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/attendance/delete/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteUser(@PathVariable int id){
        return attendanceService.deleteAttendance(id);
    }

}
