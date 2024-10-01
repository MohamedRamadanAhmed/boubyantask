package com.boubyan.boubyantask.repositories;

import com.boubyan.boubyantask.entity.Course;
import com.boubyan.boubyantask.entity.User;
import com.boubyan.boubyantask.entity.UserCourses;
import com.boubyan.boubyantask.entity.UserCoursesID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCourseRepository extends JpaRepository<UserCourses, UserCoursesID> {
    Optional<UserCourses> findByCourseAndUser(Course course, User user);
}
