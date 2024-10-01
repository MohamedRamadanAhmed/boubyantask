package com.boubyan.boubyantask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCoursesID implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "COURSE_ID")
    private Long courseId;
}
