package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import com.mitutor.entities.University;
import com.mitutor.enums.RoleType;
import com.mitutor.enums.TutorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.dtos.input.CreateTutorInput;
import com.mitutor.entities.Tutor;
import com.mitutor.entities.User;
import com.mitutor.repositories.ITutorRepository;
import com.mitutor.repositories.IUserRepository;
import com.mitutor.services.ITutorService;
import org.springframework.transaction.annotation.Transactional;

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
	        foundUser.get().setRole(RoleType.TUTOR);
	        this.userRepository.save(foundUser.get());

	        Tutor newTutor = new Tutor()
	                .withQualificationCount(0)
	                .withPoints(0.0)
	                .withPerson(foundUser.get().getPerson())
	                .withDescription("nuevo tutor")
	                .withStatus(TutorStatus.AVAILABLE);

	        return tutorRepository.save(newTutor);
	}

    @Transactional(readOnly = true)
    @Override
    public Optional<University> findUniversityByTutorId(Integer tutorId) throws Exception {
        return tutorRepository.findUniversity(tutorId);
    }
}
