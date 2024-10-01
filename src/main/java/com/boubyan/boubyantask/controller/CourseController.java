package com.boubyan.boubyantask.controller;

import com.boubyan.boubyantask.model.dto.CourseDTO;
import com.boubyan.boubyantask.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/registerUserToCourse")
    public ResponseEntity<?> registerUserToCourse(@RequestParam(value = "courseId") Long courseId, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(courseService.registerUserToCourse(token, courseId));
    }

    @GetMapping("/cancelCourseRegistration")
    public ResponseEntity<?> cancelUserRegistrationToCourse(@RequestParam(value = "courseId") Long courseId, @RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(courseService.cancelUserRegistrationToCourse(token, courseId));
    }

    @GetMapping("/downloadPdfSchedule")
    public ResponseEntity<?> downloadSchedule(@RequestParam(value = "courseId") Long courseId) {

        ByteArrayOutputStream file = courseService.getCourseSchedule(courseId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Course_Schedule.pdf");
        headers.setContentLength(file.size());
        return new ResponseEntity<>(file.toByteArray(), headers, HttpStatus.OK);
    }
}
