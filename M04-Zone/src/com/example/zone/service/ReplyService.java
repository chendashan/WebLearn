package com.example.zone.service;

import com.example.zone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    //根据topic的id获取关联的所有回复
    List<Reply> getReplyListByTopicId(Integer topicId);

    //添加回复
    void addReply(Reply reply);
}
