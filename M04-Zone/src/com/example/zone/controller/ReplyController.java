package com.example.zone.controller;

import com.example.zone.pojo.Reply;
import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;
import com.example.zone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReplyController {

    private ReplyService replyService;

    public String addReply(String content, Integer topicId, HttpSession session) {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(), author, new Topic(topicId));
        replyService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
