package com.hertzbit.restapplication.service;

import com.hertzbit.restapplication.exceptions.NoStudentFoundException;
import com.hertzbit.restapplication.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private Map<String, Student> studentMap;
    private static final Logger LOGGER = LogManager.getLogger(StudentService.class);

    public StudentService() {

        studentMap = new HashMap<>();

        Student studentOne = new Student("123456", "Stephen Hawking", 95);
        Student studentTwo = new Student("987650", "Einstein", 89);

        studentMap.put(studentOne.getStudentId(), studentOne);
        studentMap.put(studentTwo.getStudentId(), studentTwo);
    }

    public List<Student> getAllStudents() {

        LOGGER.debug(("START : In method getAllStudents"));
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<String, Student> entry : this.studentMap.entrySet()) {
                studentList.add(entry.getValue());
        }
        if (studentList.size() == 0) {
            throw new NoStudentFoundException("There are currently no students");
        }
        LOGGER.debug(("END : In method getAllStudents"));
        return studentList;
    }

    public Student getStudentsByID(String studentId) {

        if (this.studentMap.get(studentId) != null) {
            return this.studentMap.get(studentId);
        } else {
            throw new NoStudentFoundException("There are no student ID : " + studentId);
        }
    }

    public Student addNewStudent(Student student) {

        int currentSizeOfMap = this.studentMap.size();
        student.setStudentId(String.valueOf(currentSizeOfMap + 1));
        this.studentMap.put(student.getStudentId(), student);
        return student;
    }

    public Student updateExistingStudent (String studentId, Student studentFromRequest) {

        if (this.studentMap.get(studentId) != null) {
            Student studentFromDB = this.studentMap.get(studentId);
            if (studentFromRequest.getStudentName() != null && !studentFromRequest.getStudentName().isEmpty()) {
                studentFromDB.setStudentName(studentFromRequest.getStudentName());
            }
            if (studentFromRequest.getMarks() != null) {
                studentFromDB.setMarks(studentFromRequest.getMarks());
            }
            this.studentMap.put(studentFromDB.getStudentId(), studentFromDB);
            return studentFromDB;
        } else {
            throw new NoStudentFoundException("There are no students ID : " + studentId);
        }
    }

    public void deleteExistingStudent (String studentId) {

        if (this.studentMap.get(studentId) != null) {
            this.studentMap.remove(studentId);
        } else {
            throw new NoStudentFoundException("There are no students ID : " + studentId);
        }
    }
}
