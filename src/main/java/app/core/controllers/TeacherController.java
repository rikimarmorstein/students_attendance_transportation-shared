package app.core.controllers;

import app.core.auth.UserCredentials;
import app.core.entities.School;
import app.core.entities.Student;
import app.core.entities.Teacher;
import app.core.exception.SystemException;
import app.core.repositories.TeacherRepo;
import app.core.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {

//    @Autowired
//    private TeacherRepo teacherRepo;
    @Autowired
    private TeacherService teacherService;
    @PutMapping(headers = {HttpHeaders.AUTHORIZATION}, path = "update-teacher")
    public void updateTeacher(@RequestBody Teacher teacher, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        teacherService.updateTeacher(teacher, user.getId());
    }
    @GetMapping(headers = { HttpHeaders.AUTHORIZATION }, path = "teacher")
    public Teacher getTeacherDetails(HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
        return teacherService.getTeacherDetails(user.getId());
    }

    @GetMapping(path = "all-students",headers = { HttpHeaders.AUTHORIZATION } )
    public List<Student> getAllStudents (HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
//        Teacher teacher = teacherRepo.findById(user.getId()).orElseThrow(() -> new SystemException("המורה לא קיים/ת במערכת"));
//      School school=  teacher.getSchool();
    return this.teacherService.getAllStudents(user.getId());
    }
    @GetMapping(path = "all-students/{numClass}",headers = { HttpHeaders.AUTHORIZATION } )
    public List<Student> getAllStudentsByClass (@PathVariable int numClass, HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
//        Teacher teacher = teacherRepo.findById(user.getId()).orElseThrow(() -> new SystemException("המורה לא קיים/ת במערכת"));
//        School school=  teacher.getSchool();
        return this.teacherService.getAllStudentsByClass(numClass, user.getId());
    }


    @GetMapping(path = "all-students/isTravel",headers = { HttpHeaders.AUTHORIZATION } )
    public List<Student> getAllStudentsToTravel(HttpServletRequest req) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
//        Teacher teacher = teacherRepo.findById(user.getId()).orElseThrow(() -> new SystemException("המורה לא קיים/ת במערכת"));
//        School school=  teacher.getSchool();
        return this.teacherService.getAllStudentsToTravel(user.getId());
    }
    @GetMapping(path = "all-students/isTravel/{numBus}",headers = { HttpHeaders.AUTHORIZATION } )
    public List<Student> getAllStudentsToTravelByBus(HttpServletRequest req,@PathVariable int numBus) throws SystemException {
        UserCredentials user = (UserCredentials) req.getAttribute("user");
//        Teacher teacher = teacherRepo.findById(user.getId()).orElseThrow(() -> new SystemException("המורה לא קיים/ת במערכת"));
//        School school=  teacher.getSchool();
        return this.teacherService.getAllStudentsToTravelByBus(user.getId(),numBus);
    }
    @GetMapping(path = "one-student/{studentId}",headers = { HttpHeaders.AUTHORIZATION } )
    public Student getOneStudent (@PathVariable int studentId) throws SystemException{
        return this.teacherService.getOneStudent(studentId);
    }
    @GetMapping(path = "isStudentTravel/{studentId}",headers = { HttpHeaders.AUTHORIZATION } )
    public boolean isStudentTravel (@PathVariable int studentId) throws SystemException {
        return this.teacherService.isStudentTravel(studentId);
    }
}















