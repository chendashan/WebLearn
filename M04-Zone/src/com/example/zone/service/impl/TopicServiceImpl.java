package com.example.zone.service.impl;

import com.example.zone.dao.TopicDAO;
import com.example.zone.pojo.Reply;
import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;
import com.example.zone.service.ReplyService;
import com.example.zone.service.TopicService;
import com.example.zone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null;

    private ReplyService replyService;

    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDAO.getTopic(id);

        UserBasic author = userBasicService.getUserBasicById(topic.getAuthor().getId());
        topic.setAuthor(author);

        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }

    @Override
    public void delTopic(Integer topicId) {
        Topic topic = topicDAO.getTopic(topicId);
        if (topic != null) {
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }
    }
}
