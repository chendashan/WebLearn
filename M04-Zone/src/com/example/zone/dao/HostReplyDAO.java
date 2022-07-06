package com.example.zone.dao;

import com.example.zone.pojo.HostReply;

public interface HostReplyDAO {
    //根据replyId查询关联的HostReply实体
    HostReply getHostReplyByReplyId(Integer replyId);
}
