package com.example.zone.service.impl;

import com.example.zone.dao.ReplyDAO;
import com.example.zone.pojo.HostReply;
import com.example.zone.pojo.Reply;
import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;
import com.example.zone.service.HostReplyService;
import com.example.zone.service.ReplyService;
import com.example.zone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;
    //此处引入的是其他POJO对应的Service接口，而不是DAO接口，调用其他POJO的业务层方法，不引入内部方法
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(topicId));
        for (Reply reply : replyList) {
            //将回复的用户信息设置进去
            UserBasic author = userBasicService.getUserBasicById(reply.getId());
            reply.setAuthor(author);

            //将关联的HostReply设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }
}
