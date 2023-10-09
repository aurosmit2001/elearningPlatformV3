package com.example.eLearningFinal.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private Long course_id;
    @Column(name = "title")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<UserCourse> enrolledUsers;
    @Column(columnDefinition = "user_id")
    private Long userId;

    private String status;
    private double status_percent;
    private String Enroll_date;

    public Course() {
    }

    public Course(Long course_id, String title, String description, Long userId, String status, double status_percent, String enroll_date) {
        this.course_id = course_id;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.status = status;
        this.status_percent = status_percent;
        Enroll_date = enroll_date;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getStatus_percent() {
        return status_percent;
    }

    public void setStatus_percent(double status_percent) {
        this.status_percent = status_percent;
    }

    public String getEnroll_date() {
        return Enroll_date;
    }

    public void setEnroll_date(String enroll_date) {
        Enroll_date = enroll_date;
    }
}