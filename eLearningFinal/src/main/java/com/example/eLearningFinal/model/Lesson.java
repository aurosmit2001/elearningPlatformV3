package com.example.eLearningFinal.model;

import jakarta.persistence.*;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private int orderInCourse;

   //ManyToOne
   //JoinColumn(name = "course_id")
   @Column(columnDefinition = "course_id")
    private Long courseId;

    public Lesson() {
    }

    public Lesson(Long id, String title, String content, int orderInCourse, Long courseId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.orderInCourse = orderInCourse;
        this.courseId = courseId;
    }
    /*public Lesson(Long id, String title, String content, int orderInCourse*//*, Course course*//*) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.orderInCourse = orderInCourse;
       // this.course = course;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrderInCourse() {
        return orderInCourse;
    }

    public void setOrderInCourse(int orderInCourse) {
        this.orderInCourse = orderInCourse;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
/*public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }*/
}
