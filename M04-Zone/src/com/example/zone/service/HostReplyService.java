package com.example.zone.service;

import com.example.zone.pojo.HostReply;

public interface HostReplyService {

    HostReply getHostReplyByReplyId(Integer replyId);

    void delHostReply(Integer id);
}
