package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.entities.User;
import com.mitutor.repositories.ITutorRepository;
import com.mitutor.repositories.IUserRepository;
import com.mitutor.services.ITutorService;

@Service
public class TutorServiceImpl implements ITutorService {

    @Autowired
    private ITutorRepository tutorRepository;
    
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Optional<Tutor> findById(Integer id) throws Exception {
        return tutorRepository.findById(id);
    }

    @Override
    public List<Tutor> findAll() throws Exception {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor save(Tutor t) throws Exception {
        return tutorRepository.save(t);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        tutorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        tutorRepository.deleteAll();
    }

    @Override
    public List<Tutor> findAllByUniversityIdAndCourseId(Integer universityId, Integer courseId) {
        return tutorRepository.findAllByUniversityIdAndCourseId(universityId, courseId);
    }

	@Override
	public Tutor subscription(CreateTutorInput createTutorInput) throws Exception {
	     Optional<User> foundUser = this.userRepository.findById(createTutorInput.getUserId());

	        if (!foundUser.isPresent()) {
	            return null;
	        }
	        foundUser.get().setRole("tutor");
	        this.userRepository.save(foundUser.get());

	        Tutor newTutor = new Tutor()
	                .withQualificationCount(0)
	                .withPoints(0.0)
	                .withPerson(foundUser.get().getPerson())
	                .withDescription("Nuevo tutor")
	                .withStatus("AVAILABLE");

	        return tutorRepository.save(newTutor);
	}
}
