package com.mitutor.servicesImpls;

import com.mitutor.entities.TutorCourse;
import com.mitutor.repositories.ITutorCourseRepository;
import com.mitutor.services.ITutorCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class TutorCourseServiceImpl implements ITutorCourseService {
    @Autowired
    public ITutorCourseRepository tutorCourseRepository;

    @Override
    public Optional<TutorCourse> findById(Integer id) throws Exception {
        return tutorCourseRepository.findById(id);
    }

    @Override
    public List<TutorCourse> findAll() throws Exception {
        return tutorCourseRepository.findAll();
    }

    @Transactional
    @Override
    public TutorCourse save(TutorCourse tutorCourse) throws Exception {
        return tutorCourseRepository.save(tutorCourse);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) throws Exception {
        tutorCourseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() throws Exception {
        tutorCourseRepository.deleteAll();
    }
}
