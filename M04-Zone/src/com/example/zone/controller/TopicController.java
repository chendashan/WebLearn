package com.example.zone.controller;

import com.example.zone.pojo.Reply;
import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;
import com.example.zone.service.ReplyService;
import com.example.zone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class TopicController {
    private TopicService topicService;
    private ReplyService replyService;

    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic", topic);
        return "frames/detail";
    }

    public String delTopic(Integer topicId) {
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session) {
        //从session中获取当前用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //再次查询当前用户关联的所有日志
        List<Topic> topicList = topicService.getTopList(userBasic);
        userBasic.setTopicList(topicList);
        session.setAttribute("friend", userBasic);
        return "frames/main";
    }

    public String addTopic(String title, String content, HttpSession session) {
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        Topic topic = new Topic(title, content, new Date(), userBasic);
        topicService.addTopic(topic);
        return "redirect:topic.do?operate=getTopicList";
    }

}
