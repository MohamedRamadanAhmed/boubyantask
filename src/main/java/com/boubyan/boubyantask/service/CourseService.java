package com.boubyan.boubyantask.service;


import com.boubyan.boubyantask.config.security.JwtTokenProvider;
import com.boubyan.boubyantask.entity.Course;
import com.boubyan.boubyantask.entity.User;
import com.boubyan.boubyantask.entity.UserCourses;
import com.boubyan.boubyantask.entity.UserCoursesID;
import com.boubyan.boubyantask.enums.ResponseStatus;
import com.boubyan.boubyantask.exception.BadHttpException;
import com.boubyan.boubyantask.exception.NotFoundHttpException;
import com.boubyan.boubyantask.exception.model.HttpError;
import com.boubyan.boubyantask.mapper.CourseMapper;
import com.boubyan.boubyantask.model.dto.CourseDTO;
import com.boubyan.boubyantask.repositories.CourseRepository;
import com.boubyan.boubyantask.repositories.UserCourseRepository;
import com.boubyan.boubyantask.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserCourseRepository userCourseRepository;
    private final CourseMapper courseMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PDFService pdfService;
    private final String CACHE_NAME_COURSE = "courses";


    @Cacheable(cacheNames = CACHE_NAME_COURSE)
    public List<CourseDTO> getAllCourses() {
        List<Course> coursesList = courseRepository.findAll();
        return courseMapper.mapCourseToCourseDto(coursesList);
    }

    public String registerUserToCourse(String token, Long stringCourseId) {
        Optional<User> optionalUser = userRepository.findUserByEmail(jwtTokenProvider.getClaimValue(jwtTokenProvider.convertBearerToken(token), "email"));
        Optional<Course> optionalCourse = courseRepository.findById(stringCourseId);
        User user = validateOptionalUser(optionalUser);
        Course course = validateOptionalCourse(optionalCourse);
        if (isCourseAlreadyReserved(user, course)) {
            throw new BadHttpException(HttpError.builder().code("BAD_REQUEST").message("Course is already reserved").build());
        }

        userCourseRepository.findByCourseAndUser(course, user);

        UserCourses userCourses = new UserCourses();
        UserCoursesID userCoursesID = new UserCoursesID(user.getId(), course.getId());
        userCourses.setUserCoursesID(userCoursesID);
        userCourses.setCourse(course);
        userCourses.setUser(user);
        userCourses.setRegistrationDate(new Date());
        userCourseRepository.saveAndFlush(userCourses);
        return ResponseStatus.RESERVED.getValue();
    }

    public String cancelUserRegistrationToCourse(String token, Long courseId) {
        Optional<User> optionalUser = userRepository.findUserByEmail(jwtTokenProvider.getClaimValue(jwtTokenProvider.convertBearerToken(token), "email"));
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        User user = validateOptionalUser(optionalUser);
        Course course = validateOptionalCourse(optionalCourse);
        if (!isCourseAlreadyReserved(user, course)) {
            throw new BadHttpException(HttpError.builder().code("BAD_REQUEST").message("Course is already cancelled.").build());
        }
        Optional<UserCourses> userCoursesOptional = userCourseRepository.findByCourseAndUser(optionalCourse.get(), optionalUser.get());
        if (userCoursesOptional.isPresent()) {
            UserCourses userCourses = userCoursesOptional.get();
            userCourseRepository.delete(userCourses);

        }
        return ResponseStatus.CANCELLED.getValue();
    }

    private boolean isCourseAlreadyReserved(User user, Course course) {
        if (Objects.nonNull(user.getUserCoursesList())) {
            for (UserCourses userCourses : user.getUserCoursesList()) {
                if (userCourses.getCourse().getId().equals(course.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Cacheable(cacheNames = CACHE_NAME_COURSE,key = "#courseId")
    public ByteArrayOutputStream getCourseScheduleAsPdf(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        Course course = validateOptionalCourse(optionalCourse);
        List<String> cells = new ArrayList<>();
        cells.add(course.getName());
        cells.add(course.getCourseStartDate().toString());
        cells.add(course.getCourseEndDate().toString());
        try {
            return pdfService.createPDFTable(3, cells);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    User validateOptionalUser(Optional<User> optionalUser) {
        if (!optionalUser.isPresent()) {
            throw new NotFoundHttpException(HttpError.builder().code("Not_Found").message("user Not found.").build());

        }
        return optionalUser.get();
    }

    Course validateOptionalCourse(Optional<Course> optionalCourse) {
        if (!optionalCourse.isPresent()) {
            throw new NotFoundHttpException(HttpError.builder().code("Not_Found").message("course not found.").build());

        }
        return optionalCourse.get();
    }

    public CourseDTO createCourse(CourseDTO courseDTO) {
        Optional<Course> courseOptional = courseRepository.findByName(courseDTO.getName());
        if (courseOptional.isPresent()) {
            throw new BadHttpException(HttpError.builder().code("BAD_REQUEST").message("course name already created.").build());
        }
        return courseMapper.mapCourseToCourseDto(courseRepository.save(courseMapper.mapCourseDTOToCourse(courseDTO)));
    }
}
