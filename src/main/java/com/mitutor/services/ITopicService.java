package com.mitutor.services;

import com.mitutor.entities.Topic;

import java.util.List;

public interface ITopicService extends ICrudService<Topic> {
    List<Topic> findAllByCourseId(Integer courseId) throws Exception;
}
