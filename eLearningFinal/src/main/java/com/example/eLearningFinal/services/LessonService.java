package com.example.eLearningFinal.services;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.Lesson;
import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.repositories.CourseRepository;
import com.example.eLearningFinal.repositories.LessonRepository;
import com.example.eLearningFinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Lesson> getAllLessons(Long course_id) {
        List<Lesson> lessons = lessonRepository.findByCourseId(course_id);


        if (lessons == null) {
            return null;
        }

        return lessons;//course.getLessons();

    }

    public Lesson getLessonById(Long course_id, Long lessonId) {
        Course course = courseRepository.findById(course_id)
                .orElse(null);

        if (course != null) {
            return lessonRepository.findById(lessonId)
                    .orElse(null);
        }

        return null;
    }

    public Lesson createLesson(Long course_id, Lesson lesson, String username) {
        Course course = courseRepository.findById(course_id)
                .orElse(null);
        User user = userRepository.findByUsername(username);


        if (course != null && user != null && user.getRole().equals("Instructor")) {
            //lesson.setCourse(course);
            return lessonRepository.save(lesson);
        }

        return null;
    }

    public Lesson updateLesson(Long course_id, Long lessonId, Lesson updatedLesson, String username) {
        Course course = courseRepository.findById(course_id)
                .orElse(null);
        User user = userRepository.findByUsername(username);


        if (course != null && user != null && user.getRole().equals("Instructor")){
            return lessonRepository.findById(lessonId)
                    .map(lesson -> {
                        lesson.setTitle(updatedLesson.getTitle());
                        lesson.setContent(updatedLesson.getContent());
                        lesson.setOrderInCourse(updatedLesson.getOrderInCourse());

                        return lessonRepository.save(lesson);
                    })
                    .orElse(null);
        }

        return null;
    }

    public void deleteLesson(Long course_id, Long lessonId, String username) {
        Course course = courseRepository.findById(course_id)
                .orElse(null);
        User user = userRepository.findByUsername(username);


        if (course != null && user != null && user.getRole().equals("Instructor")){
            Lesson lesson = lessonRepository.findById(lessonId)
                    .orElse(null);

            if (lesson == null) {
                return;
            }

            //course.getLessons().remove(lesson);
            lessonRepository.deleteById(lessonId);
            courseRepository.save(course);
        }
    }

}
