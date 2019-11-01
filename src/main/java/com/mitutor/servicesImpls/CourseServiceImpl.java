package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Course;
import com.mitutor.repositories.ICourseRepository;
import com.mitutor.services.ICourseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public Optional<Course> findById(Integer id) throws Exception {
        return courseRepository.findById(id);
    }


    @Override
    public List<Course> findAll() throws Exception {
        return courseRepository.findAll();
    }

    @Transactional
    @Override
    public Course save(Course t) throws Exception {
        return courseRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) throws Exception {
        courseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() throws Exception {
        courseRepository.deleteAll();
    }

    @Override
    public Optional<Course> findByUniversityIdAndName(Integer universityId, String name) {
        return courseRepository.findByUniversityIdAndName(universityId, name);
    }

    @Override
    public List<Course> findAllByUniversityId(Integer universityId) {
        return courseRepository.findAllByUniversityId(universityId);
    }

    @Override
    public List<Course> findAllByTutorId(Integer tutorId) {
        return courseRepository.findAllByTutorCoursesTutorId(tutorId);
    }
}
