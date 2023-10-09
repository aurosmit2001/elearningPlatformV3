package com.example.eLearningFinal.model;

import java.util.List;

public class CourseDetail {
    private Long course_id;

    private String title;

    private String description;

    private List<LessonDetail> lessons;
    private String status;
    private double status_percent;
    private String Enroll_date;

    public Long getCourse_id(Long courseId) {
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

    public List<LessonDetail> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDetail> lessons) {
        this.lessons = lessons;
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