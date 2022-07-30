package com.hertzbit.restapplication.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student implements Serializable {

    private String studentId;
    private String studentName;
    private Integer marks;

    public Student () {};

    public Student(String studentId, String studentName, Integer marks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", marks=" + marks +
                '}';
    }
}
