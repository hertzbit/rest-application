package com.hertzbit.restapplication.controller;

import com.hertzbit.restapplication.model.Student;
import com.hertzbit.restapplication.model.User;
import com.hertzbit.restapplication.service.StudentService;
import com.hertzbit.restapplication.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping ("/api/students")
public class StudentController {

    private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping (produces = "application/json")
    public ResponseEntity<List<Student>> getAllStudents () {

        List<Student> studentList = this.studentService.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping (value = "/{studentId}", produces = "application/json")
    public ResponseEntity<Student> getRequestedStudent(@PathVariable("studentId") String studentId) {

        Student studentFromDatabase = this.studentService.getStudentsByID(studentId);
        return new ResponseEntity<>(studentFromDatabase, HttpStatus.OK);
    }

    @PostMapping (produces = "application/json")
    public ResponseEntity<Student> addStudentToDatabase (@RequestBody Student student) {
        Student savedStudentWithStudentID = this.studentService.addNewStudent(student);
        return new ResponseEntity<>(savedStudentWithStudentID, HttpStatus.CREATED);
    }

    @PutMapping (value = "/{studentId}", produces = "application/json")
    public ResponseEntity<Student> updateExistingStudent (@PathVariable("studentId") String studentId, @RequestBody Student student) {

        Student updatedStudent = this.studentService.updateExistingStudent(studentId, student);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping (value = "/{studentId}")
    public ResponseEntity<?> deleteStudentFromDatabase (@PathVariable("studentId") String studentId) {

        this.studentService.deleteExistingStudent(studentId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
