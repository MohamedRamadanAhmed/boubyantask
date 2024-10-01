package com.boubyan.boubyantask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_COURSES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCourses {
    @EmbeddedId
    private UserCoursesID userCoursesID;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "COURSE_ID")
    private Course course;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;

}
