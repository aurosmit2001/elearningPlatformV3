package com.example.eLearningFinal.services;

import com.example.eLearningFinal.model.*;
import com.example.eLearningFinal.repositories.CourseRepository;
import com.example.eLearningFinal.repositories.LessonRepository;
import com.example.eLearningFinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;
    public List<CourseDetail> getALLCourse() {
        //return courseRepository.findAll();
        List<CourseDetail> courseDetails = new ArrayList<>();

        List<Course> courseList = courseRepository.findAll();
        if(courseList!=null){
            for(Course course: courseList){
                List<Lesson> lessonList = lessonRepository.findByCourseId(course.getCourse_id());
                List<LessonDetail> lessonDetails = new ArrayList<>();
                if(lessonList!=null){
                    for(Lesson lesson:lessonList) {
                        LessonDetail lessonDetail = new LessonDetail();
                        lessonDetail.setId(lesson.getId());
                        lessonDetail.setOrderInCourse(lesson.getOrderInCourse());
                        lessonDetail.setContent(lesson.getContent());
                        lessonDetail.setTitle(lesson.getTitle());
                        lessonDetails.add(lessonDetail);
                    }
                }

                CourseDetail courseDetail = new CourseDetail();
                courseDetail.setCourse_id(course.getCourse_id());
                courseDetail.setDescription(course.getDescription());
                courseDetail.setTitle(course.getTitle());
                courseDetail.setStatus(course.getStatus());
                courseDetail.setStatus_percent(course.getStatus_percent());
                courseDetail.setEnroll_date(course.getEnroll_date());
                courseDetail.setLessons(lessonDetails);

                courseDetails.add(courseDetail);
            }
        }
        return courseDetails;
    }


    public CourseDetail getCourseById(Long course_id) {
        CourseDetail courseDetail = new CourseDetail();
        Optional<Course> courseOptional = courseRepository.findById(course_id);
        if(courseOptional.isPresent()){

            Course course=courseOptional.get();
                List<Lesson> lessonList = lessonRepository.findByCourseId(course.getCourse_id());
                List<LessonDetail> lessonDetails = new ArrayList<>();
                if(lessonList!=null){
                    for(Lesson lesson:lessonList) {
                        LessonDetail lessonDetail = new LessonDetail();
                        lessonDetail.setId(lesson.getId());
                        lessonDetail.setOrderInCourse(lesson.getOrderInCourse());
                        lessonDetail.setContent(lesson.getContent());
                        lessonDetail.setTitle(lesson.getTitle());
                        lessonDetails.add(lessonDetail);
                    }
                }
            courseDetail.getCourse_id(course.getCourse_id());
            courseDetail.setDescription(course.getDescription());
            courseDetail.setTitle(course.getTitle());
            courseDetail.setStatus(course.getStatus());
            courseDetail.setStatus_percent(course.getStatus_percent());
            courseDetail.setEnroll_date(course.getEnroll_date());
            courseDetail.setLessons(lessonDetails);


            }

        return courseDetail;
    }

    public Course createCourse(Course course, String username) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getRole().equals("Instructor"))
            return courseRepository.save(course);
        return null;
    }


    public Course updateCourse(Long course_id, String username, Course updatedCourse) {
        Optional<Course> courseOptional = courseRepository.findById(course_id);
        User user = userRepository.findByUsername(username);
        if (courseOptional.isPresent() && user != null && user.getRole().equals("Instructor")) {
            Course course = courseOptional.get();
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            return courseRepository.save(course);
        } else {
            return null;
        }
    }

    public void deleteCourse(Long courseId, String username) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getRole().equals("Instructor")) {
            courseRepository.deleteById(courseId);
        }
    }

    public List<Course> searchCourses(
            String keyword,
            String description,
            String sortField) {

        Specification<Course> specification = Specification.where(null);

        // Add filtering conditions based on keyword and categoryId
        if (keyword != null && !keyword.isEmpty()) {
            specification = specification.and((root, query, builder) ->
                    builder.or(
                            builder.like(root.get("title"), "%" + keyword + "%")
                    )
            );
        }

        if (description != null) {
            specification = specification.and((root, query, builder) ->
                    builder.like(root.get("description"),"%" + description + "%")
            );
        }

        // Add sorting logic
        Sort courseSort = Sort.unsorted();
        if (sortField != null && !sortField.isEmpty()) {
            if ("title".equalsIgnoreCase(sortField)) {
                courseSort = Sort.by("title");
            } else if ("description".equalsIgnoreCase(sortField)) {
                courseSort = Sort.by("description");
            }
        }

        // Execute the query with specifications and sorting
        return courseRepository.findAll(specification, courseSort);
    }


    public Course createCourseForUser(Long user_id, Course course) {
        User user = userRepository.findById(user_id).orElse(null);
        return courseRepository.save(course);
    }

    public List<Course> getALLCourseForUser(Long user_id) {
        List<Course> courses = courseRepository.findAllByUserId(user_id);

        if (courses== null){
            return null;
        }
        return courses;
    }

    public Course updateCourseForUser(Long user_id, Long course_id, Course updateCourseForUser) {
     User user = userRepository.findById(user_id).orElse(null);
        Optional<Course> courseOptional = courseRepository.findById(user_id);

        if (courseOptional.isPresent() ) {
            Course course = courseOptional.get();
            //course.setTitle(updateCourseForUser.getTitle());
            //course.setDescription(updateCourseForUser.getDescription());
            course.setStatus(updateCourseForUser.getStatus());
            course.setStatus_percent(updateCourseForUser.getStatus_percent());
            //course.setEnroll_date(course.getEnroll_date());
            return courseRepository.save(course);
        } else {
            return null;
        }
    }

}
