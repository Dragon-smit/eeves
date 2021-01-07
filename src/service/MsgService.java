package service;

import entity.Msg;

import java.util.List;

public interface MsgService {
    List<Msg> findMsgList(String username);

    Msg findMsg(String msgid);

    boolean deleteMsg(String msgid);

    boolean addMsg(Msg msg);
}
