package com.example.zone.dao.impl;

import com.example.myssm.basedao.BaseDAO;
import com.example.zone.dao.ReplyDAO;
import com.example.zone.pojo.Reply;
import com.example.zone.pojo.Topic;

import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

    @Override
    public List<Reply> getReplyList(Topic topic) {
        return super.executeQuery("select * from t_reply where topic = ?", topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        executeUpdate("insert into t_reply values(0, ?, ?, ?, ?)", reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        executeUpdate("delete from t_reply where id = ?", id);
    }
}
