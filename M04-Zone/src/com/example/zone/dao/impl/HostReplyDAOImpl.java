package com.example.zone.dao.impl;

import com.example.myssm.basedao.BaseDAO;
import com.example.zone.dao.HostReplyDAO;
import com.example.zone.pojo.HostReply;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select * from t_host_reply where reply = ?", replyId);
    }
}
