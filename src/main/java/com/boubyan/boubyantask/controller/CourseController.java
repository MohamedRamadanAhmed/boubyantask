package com.boubyan.boubyantask.controller;

import com.boubyan.boubyantask.model.dto.CourseDTO;
import com.boubyan.boubyantask.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@Validated
public class CourseController {

    private final CourseService courseService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(courseService.createCourse(courseDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/registerUser")
    public ResponseEntity<?> registerUserToCourse(@RequestParam(value = "courseId") Long courseId, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(courseService.registerUserToCourse(token, courseId));
    }

    @GetMapping("/cancelRegistration")
    public ResponseEntity<?> cancelUserRegistrationToCourse(@RequestParam(value = "courseId") Long courseId, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(courseService.cancelUserRegistrationToCourse(token, courseId));
    }

    @GetMapping("/downloadPdfSchedule")
    public ResponseEntity<?> download(@RequestParam(value = "courseId") Long courseId) {

        ByteArrayOutputStream file = courseService.getCourseScheduleAsPdf(courseId);
        return ResponseEntity.ok().contentLength(file.size())
                .contentType(MediaType.APPLICATION_PDF)
                .body(file.toByteArray());
    }
}
