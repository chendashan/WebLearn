package com.example.zone.service;

import com.example.zone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {

    UserBasic login(String loginId, String pwd);

    List<UserBasic> getFriendList(UserBasic userBasic);
}
