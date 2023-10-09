package com.example.eLearningFinal.repositories;

import com.example.eLearningFinal.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourseId(long courseId);

}
