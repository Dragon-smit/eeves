package dao;

import entity.Msg;
import jdk.nashorn.internal.objects.annotations.Where;
import util.BaseDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MsgDaoImpl extends BaseDao implements MsgDao {
    @Override
    public List<Msg> findMsgList(String name) {
        List<Msg> msgList = new ArrayList<>();
        Msg msg = null;
        try {
            // sql 语句
            String sql = " select * from msg where sendto = ? ";
            executeQuery(sql,name);
            while (rs.next()){
                int msgid = rs.getInt(1);
                String username = rs.getString(2);
                String title = rs.getString(3);
                String msgcontent = rs.getString(4);
                int state = rs.getInt(5);
                String sendto  = rs.getString(6);
                Date msgCreateDate = rs.getDate(7);
                msg = new Msg(msgid,username,title,msgcontent,state,sendto,msgCreateDate);
                msgList.add(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return msgList;
    }

    @Override
    public Msg findMsg(String msgid) {
        Msg msg = null;
        try {
            String sql = " select * from msg where msgid = ? ";
            executeQuery(sql,msgid);
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String title = rs.getString(3);
                String msgcontent = rs.getString(4);
                int state = rs.getInt(5);
                String sendto = rs.getString(6);
                Date msgCreateDate = rs.getDate(7);
                msg = new Msg(id, username, title, msgcontent, state, sendto, msgCreateDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }


        return msg;
    }

    @Override
    public int updateMsgState(String msgid) {
        int row = 0;
        try {
            String sql = " update msg set state=2 where msgid = ? ";
            row = executeUpdate(sql,msgid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return row;
    }

    @Override
    public int deleteMsg(String msgid) {
        try {
            String sql  = " delete from msg where msgid = ? ";
            return executeUpdate(sql,msgid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return 0;
    }

    @Override
    public int addMsg(Msg msg) {

        try {
            String  sql = " insert into msg values(default,?,?,?,?,?,?) ";
            return executeUpdate(sql,msg.getUsername(),msg.getTitle(),msg.getMsgcontent(),msg.getState(),msg.getSendto(),msg.getMsgCreateDate());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return 0;
    }
}
