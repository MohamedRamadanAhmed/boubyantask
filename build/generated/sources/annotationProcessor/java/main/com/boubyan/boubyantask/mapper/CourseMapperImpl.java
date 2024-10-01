package com.boubyan.boubyantask.mapper;

import com.boubyan.boubyantask.entity.Course;
import com.boubyan.boubyantask.model.dto.CourseDTO;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-01T02:32:15+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO mapCourseToCourseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO.CourseDTOBuilder courseDTO = CourseDTO.builder();

        if ( course.getId() != null ) {
            courseDTO.id( course.getId() );
        }
        if ( course.getName() != null ) {
            courseDTO.name( course.getName() );
        }
        if ( course.getCourseStartDate() != null ) {
            courseDTO.courseStartDate( LocalDateTime.ofInstant( course.getCourseStartDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        if ( course.getCourseEndDate() != null ) {
            courseDTO.courseEndDate( LocalDateTime.ofInstant( course.getCourseEndDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return courseDTO.build();
    }

    @Override
    public List<CourseDTO> mapCourseToCourseDto(List<Course> courses) {
        if ( courses == null ) {
            return null;
        }

        List<CourseDTO> list = new ArrayList<CourseDTO>( courses.size() );
        for ( Course course : courses ) {
            list.add( mapCourseToCourseDto( course ) );
        }

        return list;
    }

    @Override
    public Course mapCourseDTOToCourse(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        Course.CourseBuilder course = Course.builder();

        if ( courseDTO.getId() != null ) {
            course.id( courseDTO.getId() );
        }
        if ( courseDTO.getName() != null ) {
            course.name( courseDTO.getName() );
        }
        if ( courseDTO.getCourseStartDate() != null ) {
            course.courseStartDate( Date.from( courseDTO.getCourseStartDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        if ( courseDTO.getCourseEndDate() != null ) {
            course.courseEndDate( Date.from( courseDTO.getCourseEndDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }

        return course.build();
    }
}
