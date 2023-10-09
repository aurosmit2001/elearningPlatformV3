package com.example.eLearningFinal.controllers;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.CourseDetail;
import com.example.eLearningFinal.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDetail> getALLCourse() {
        return courseService.getALLCourse();
    }
    @GetMapping("/{course_id}")
    public CourseDetail getCourseById(@PathVariable Long course_id){
        return courseService.getCourseById(course_id);
    }
    @PostMapping("/{username}")
    public Course createCourse(@PathVariable String username, @RequestBody Course course ) {
        return courseService.createCourse(course, username);
    }
    @PutMapping("/{username}/{course_id}")
    public Course updateCourse(@PathVariable String username , @PathVariable Long course_id, @RequestBody Course updatedCourse) {

        Course updated = courseService.updateCourse(course_id, username, updatedCourse);
        return courseService.updateCourse(course_id, username, updatedCourse);
    }

    @DeleteMapping("/{username}/{course_id}")
    public String deleteCourse( @PathVariable String username, @PathVariable Long course_id) {

        courseService.deleteCourse(course_id, username);
        return "Course deleted successfully";
    }
    @GetMapping("/courses")
    public List<Course> searchCourses(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String sortField) {
        return courseService.searchCourses(keyword, description, sortField);
    }
    @PostMapping("/user/{user_id}")
    public Course createCourseForUser(@PathVariable Long user_id, @RequestBody Course course){
        return courseService.createCourseForUser(user_id, course);
    }
    @GetMapping("/user/{user_id}")
    public List<Course> getAllCouresForUser(@PathVariable Long user_id){
        List<Course> courses = courseService.getALLCourseForUser(user_id);
        return courses;
    }
    @PutMapping("/user/{user_id}/{course_id}")
    public Course updateCourseForUser( @PathVariable Long user_id, @PathVariable Long course_id, @RequestBody Course updateCourseForUser) {



        return courseService.updateCourseForUser(  user_id,course_id, updateCourseForUser);
    }



}
