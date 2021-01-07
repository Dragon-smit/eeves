package dao;

import entity.Msg;

import java.util.List;

public interface MsgDao {
    List<Msg> findMsgList(String username);

    Msg findMsg(String msgid);

    int updateMsgState(String msgid);

    int deleteMsg(String msgid);

    int addMsg(Msg msg);
}
