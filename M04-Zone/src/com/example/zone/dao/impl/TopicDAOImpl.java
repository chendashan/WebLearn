package com.example.zone.dao.impl;

import com.example.myssm.basedao.BaseDAO;
import com.example.zone.dao.TopicDAO;
import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?", userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {
        super.executeUpdate("insert into t_topic value (0, ?, ?, ?, ?)", topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor().getId());
    }

    @Override
    public void delTopic(Topic topic) {
        executeUpdate("delete from t_topic where id = ?", topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {
        return super.load("select * from t_topic where id = ?", id);
    }
}
