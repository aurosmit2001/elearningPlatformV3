package com.example.eLearningFinal.controllers;

import com.example.eLearningFinal.model.Lesson;
import com.example.eLearningFinal.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/{course_id}")

    public List<Lesson> getAllLessons(@PathVariable Long course_id){
        List<Lesson> lessons = lessonService.getAllLessons(course_id);
        return lessons;
    }
    @GetMapping("/{course_id}/{lessonId}")
    public Lesson getLessonById(@PathVariable Long course_id, @PathVariable Long lessonId) {
        return lessonService.getLessonById(course_id, lessonId);
    }
    @PostMapping("/{username}/{course_id}")
    public Lesson createLesson(@PathVariable String username, @PathVariable Long course_id, @RequestBody Lesson lesson ) {

        return lessonService.createLesson(course_id, lesson, username);
    }
    @PutMapping("/{username}/{course_id}/{lessonId}")
    public Lesson updateLesson(
            @PathVariable String username,
            @PathVariable Long course_id,
            @PathVariable Long lessonId,
            @RequestBody Lesson updatedLesson
    ) {

        return lessonService.updateLesson(course_id, lessonId, updatedLesson, username);
    }
    @DeleteMapping("/{username}/{course_id}/{lessonId}")
    public void deleteLesson(@PathVariable String username, @PathVariable Long course_id, @PathVariable Long lessonId) {

        lessonService.deleteLesson(course_id, lessonId, username);
    }

}
