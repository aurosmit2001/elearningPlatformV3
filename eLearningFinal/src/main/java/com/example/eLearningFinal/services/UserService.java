package com.example.eLearningFinal.services;

import com.example.eLearningFinal.model.Course;
import com.example.eLearningFinal.model.CourseDetail;
import com.example.eLearningFinal.model.User;
import com.example.eLearningFinal.model.UserDetail;
import com.example.eLearningFinal.repositories.CourseRepository;
import com.example.eLearningFinal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    public List<UserDetail> getALLUser() {
//        return userRepository.findAll();
//    }
        List<UserDetail> userDetails = new ArrayList<>();

        List<User> userList = userRepository.findAll();
        if(userList!= null){
            for (User user : userList){
                List<Course> courseList = courseRepository.findAllByUserId(user.getUser_id());
                List<CourseDetail> courseDetails = new ArrayList<>();
                if (courseList!=null){
                    for (Course course: courseList){
                        CourseDetail courseDetail = new CourseDetail();
                        courseDetail.setCourse_id(course.getCourse_id());
                        courseDetail.setDescription(course.getDescription());
                        courseDetail.setTitle(course.getTitle());
                        courseDetail.setStatus(course.getStatus());
                        courseDetail.setStatus_percent(course.getStatus_percent());
                        courseDetail.setEnroll_date(course.getEnroll_date());
                        courseDetails.add(courseDetail);

                    }
                }
                UserDetail userDetail = new UserDetail();
                userDetail.setUser_id(user.getUser_id());
                userDetail.setUsername(user.getUsername());
                userDetail.setPassword(user.getPassword());
                userDetail.setRole(user.getRole());
                userDetail.setCourses(courseDetails);
                userDetails.add(userDetail);
            }
        }
        return userDetails;

    }


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public User getUserByusernameAndpassword(String username, String password) {
        User user =userRepository.findByusername(username);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
    public UserDetail getUserById(Long user_id) {
        UserDetail userDetail = new UserDetail();
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()){
            User user= userOptional.get();
            List<Course> courseList= courseRepository.findAllByUserId(user.getUser_id());
            List<CourseDetail> courseDetails= new ArrayList<>();
            if (courseList!=null){
                for (Course course: courseList){
                    CourseDetail courseDetail =  new CourseDetail();
                    courseDetail.setCourse_id(course.getCourse_id());
                    courseDetail.setTitle(course.getTitle());
                    courseDetail.setDescription(course.getDescription());
                    courseDetail.setStatus(course.getStatus());
                    courseDetail.setStatus_percent(course.getStatus_percent());
                    courseDetail.setEnroll_date(course.getEnroll_date());
                    courseDetails.add(courseDetail);

                }
            }

            userDetail.getUser_id(user.getUser_id());
            userDetail.setUsername(user.getUsername());
            userDetail.setPassword(user.getPassword());
            userDetail.setRole(user.getRole());
            userDetail.setCourses(courseDetails);
        }

        return userDetail;

    }
}
