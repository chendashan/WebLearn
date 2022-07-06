package com.example.zone.service;

import com.example.zone.pojo.Reply;
import com.example.zone.pojo.Topic;

import java.util.List;

public interface ReplyService {
    //根据topic的id获取关联的所有回复
    List<Reply> getReplyListByTopicId(Integer topicId);

    //添加回复
    void addReply(Reply reply);

    //删除指定的回复
    void delReply(Integer replyId);

    //删除指定的日志关联的所有回复
    void delReplyList(Topic topic);
}
