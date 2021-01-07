package service;

import entity.MsgUserInfo;

import java.util.List;

public interface UserService {
    MsgUserInfo login(String username, String password);

    int register(MsgUserInfo msgUserInfo);

    List<MsgUserInfo> findUsers();
}
