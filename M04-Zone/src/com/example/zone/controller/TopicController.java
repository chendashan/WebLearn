package com.example.zone.controller;

import com.example.zone.pojo.Reply;
import com.example.zone.pojo.Topic;
import com.example.zone.service.ReplyService;
import com.example.zone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TopicController {
    private TopicService topicService;
    private ReplyService replyService;

    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic", topic);
        return "frames/detail";
    }
}
