package com.example.zone.service.impl;

import com.example.zone.dao.HostReplyDAO;
import com.example.zone.pojo.HostReply;
import com.example.zone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDAO hostReplyDAO;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }
}
