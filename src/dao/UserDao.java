package dao;

import entity.MsgUserInfo;

import java.util.List;

public interface UserDao {
    MsgUserInfo login(String username, String password);

    MsgUserInfo findUser(MsgUserInfo msgUserInfo);

    int addUser(MsgUserInfo msgUserInfo);

    List<MsgUserInfo> findUsers();
}
