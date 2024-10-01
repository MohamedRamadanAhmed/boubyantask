package com.boubyan.boubyantask.mapper;


import com.boubyan.boubyantask.entity.Course;
import com.boubyan.boubyantask.model.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = ALWAYS)

public interface CourseMapper {
    CourseDTO mapCourseToCourseDto(Course course);

    List<CourseDTO> mapCourseToCourseDto(List<Course> courses);

    Course mapCourseDTOToCourse(CourseDTO courseDTO);
}
