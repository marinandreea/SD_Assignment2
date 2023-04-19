package com.example.Assignment_A2.service;

import com.example.Assignment_A2.model.Attendance;
import com.example.Assignment_A2.model.Laboratory;
import com.example.Assignment_A2.model.User;
import com.example.Assignment_A2.model.dto.AttendanceDTO;
import com.example.Assignment_A2.repository.AttendanceRepository;
import com.example.Assignment_A2.repository.LaboratoryRepository;
import com.example.Assignment_A2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private LaboratoryRepository laboratoryRepository;


    public boolean checkPresence(int idLab, int idUser){
        boolean exists = false;
        List<Attendance> att = (List<Attendance>) attendanceRepository.findAll();
        for (Attendance attendance: att){
            if(attendance.getUserId().getIdUser() == idUser && attendance.getLabId().getIdLab() == idLab){
                exists = true;
            }
        }
        return  exists;
    }

    public static AttendanceDTO fromEntity(Attendance attendance) {

        int studId = attendance.getUserId().getIdUser();
        int labId = attendance.getLabId().getIdLab();

        return AttendanceDTO.builder()
                .idAttendance(attendance.getIdAttendance())
                .idStudent(studId)
                .idLab(labId)
                .present(attendance.getPresent())
                .build();
    }

    public Attendance toEntity(AttendanceDTO attendanceDTO) {

        Optional<User> student = userRepository.findById(attendanceDTO.getIdStudent());
        Optional<Laboratory> lab = laboratoryRepository.findById(attendanceDTO.getIdLab());

        return Attendance.builder()
                .userId(student.get())
                .labId(lab.get())
                .present(attendanceDTO.getPresent())
                .build();
    }

    public String addAttendance(AttendanceDTO attendanceDTO){

        Attendance attendance = toEntity(attendanceDTO);
        Optional<Laboratory> laboratory = laboratoryRepository.findById(attendance.getLabId().getIdLab());
        Optional<User> student = userRepository.findById(attendance.getUserId().getIdUser());
        if(student != null){
            if(laboratory != null){
                if(!checkPresence(attendance.getLabId().getIdLab(), attendance.getUserId().getIdUser())){
                    attendanceRepository.save(attendance);
                    return "Attendance was created successfully!";
                }else{
                    return "Attendance was already created!";
                }
            }else{
                return "No laboratory with id " + attendance.getLabId().getIdLab() + " was found!";
            }
        }else{
            return "No student with id " + attendance.getUserId().getIdUser() + " was found!";
       }
    }

    public List<Attendance> getAllAttendances(){
        return (List<Attendance>) attendanceRepository.findAll();
    }


    public List<Attendance> displayAttendanceForALab(int idLab){
        var lab = laboratoryRepository.findById(idLab);
        List<Attendance> aL = new ArrayList<>();
        if(lab.isPresent()){
            List<Attendance> attendanceList = new ArrayList<>();
           attendanceList= (List<Attendance>) attendanceRepository.findAll();
           for (Attendance a:attendanceList){
               if(a.getLabId().getNrLab()==idLab){
                   aL.add(a);
               }
           }
           return aL;
        }
        return null;
    }

    public String updateAttendanceForALab(AttendanceDTO attendanceDTO){

        var att = attendanceRepository.findById(attendanceDTO.getIdAttendance());
        var lab = laboratoryRepository.findById(attendanceDTO.getIdLab());
        var stud = userRepository.findById(attendanceDTO.getIdStudent());
        if(att.isPresent()){
            if(lab.isPresent()){
                if(checkPresence(lab.get().getIdLab(),attendanceDTO.getIdStudent())){
                    att.get().setLabId(lab.get());
                    att.get().setUserId(stud.get());
                    att.get().setPresent(attendanceDTO.getPresent());
                    attendanceRepository.save(att.get());
                    return "Attendance was updated successfully!";

                }else{
                    return "There is no attendance created for this user!";
                }
            }else{
                return "The laboratory was not found!";
            }
        }else{
            return "No attendance was found!";
        }

    }

    public String deleteAttendance(int idAttendance){
        var att = attendanceRepository.findById(idAttendance);
        if(att.isPresent()){
            attendanceRepository.deleteById(idAttendance);
            return "Attendance with id " + idAttendance + " was deleted successfully!";
        }else{
            return "There is no attendance with id "+idAttendance;
        }
    }
}


