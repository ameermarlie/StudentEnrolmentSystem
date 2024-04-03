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
public class Course implements Serializable{
    
    private String courseCode;
    private String title;

    public Course() {
    }

    public Course(String courseCode, String title) {
        this.courseCode = courseCode;
        this.title = title;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" + "courseCode=" + courseCode + ", title=" + title + '}';
    }
    
    
    
}
