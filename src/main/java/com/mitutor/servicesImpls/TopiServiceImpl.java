package com.mitutor.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitutor.entities.Topic;
import com.mitutor.repositories.ITopicRepository;
import com.mitutor.services.ITopicService;

@Service
public class TopiServiceImpl implements ITopicService {

	@Autowired
	private ITopicRepository topicRepository;

	@Override
	public Optional<Topic> findById(Integer id) throws Exception {
		return topicRepository.findById(id);
	}

	@Override
	public List<Topic> findAll() throws Exception {
		return topicRepository.findAll();
	}

	@Override
	public Topic save(Topic t) throws Exception {
		return topicRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		topicRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		topicRepository.deleteAll();
	}

}
