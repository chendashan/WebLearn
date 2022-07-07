package com.example.zone.service;

import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopList(UserBasic userBasic);

    //根据id获取特定topic
    Topic getTopicById(Integer id);

    //删除特定的topic
    void delTopic(Integer topicId);

    //添加topic
    void addTopic(Topic topic);
}
