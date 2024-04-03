/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author ameer
 */
public class Enrolment implements Serializable{
    
    private String studentNumber;
    private String courseCode;

    public Enrolment() {
    }

    public Enrolment(String studentNumber, String courseCode) {
        this.studentNumber = studentNumber;
        this.courseCode = courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "Enrolment{" + "studentNumber=" + studentNumber + ", courseCode=" + courseCode + '}';
    }
    
    
    
}
