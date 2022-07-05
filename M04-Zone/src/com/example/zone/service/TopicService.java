package com.example.zone.service;

import com.example.zone.pojo.Topic;
import com.example.zone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopList(UserBasic userBasic);
}
