package com.mitutor.converters;

import com.mitutor.dtos.CourseDTO;
import com.mitutor.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter implements IConverter<Course, CourseDTO> {
    @Override
    public Course fromDto(CourseDTO dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());

        return course;
    }

    @Override
    public CourseDTO fromEntity(Course entity) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(entity.getId());
        courseDTO.setName(entity.getName());

        return courseDTO;
    }
}
