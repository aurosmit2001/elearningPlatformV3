package com.example.eLearningFinal.model;

import java.util.List;

public class UserDetail {
    private Long user_id;
    private String username;
    private String password;
    private String role;

    private List<CourseDetail> courses;

    public Long getUser_id(Long userId) {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CourseDetail> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDetail> courses) {
        this.courses = courses;
    }
}
