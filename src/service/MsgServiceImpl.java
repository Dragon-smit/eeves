package service;

import dao.MsgDao;
import dao.MsgDaoImpl;
import entity.Msg;

import java.util.List;

public class MsgServiceImpl implements MsgService {
    private MsgDao msgDao = new MsgDaoImpl();
    @Override
    public List<Msg> findMsgList(String username) {
        return msgDao.findMsgList(username);
    }

    @Override
    public Msg findMsg(String msgid) {

        // 2.根据id查询信息
        Msg msg = msgDao.findMsg(msgid);
        if (msg != null){
            // 1.修改已读状态
            int result = msgDao.updateMsgState(msgid);
        }
        return msg;
    }

    @Override
    public boolean deleteMsg(String msgid) {
        if(msgDao.deleteMsg(msgid) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addMsg(Msg msg) {
        if (msgDao.addMsg(msg) > 0){
            return true;
        }
        return false;
    }
}
