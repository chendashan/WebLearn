package com.example.zone.service.impl;

import com.example.zone.dao.TopicDAO;
import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;
import com.example.zone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null;

    @Override
    public List<Topic> getTopList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
