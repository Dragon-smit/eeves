package dao;

import entity.MsgUserInfo;
import util.BaseDao;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public MsgUserInfo login(String username, String password) {
        MsgUserInfo msgUserInfo = null;
        try {
            // 1.编写SQL语句
            String sql = " select * from msg_userinfo where username = ? and password = ? ";
            // 2.执行
            executeQuery(sql,username,password);
            // 3.遍历结果集
            while (rs.next()){
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String email = rs.getString("email");
                msgUserInfo = new MsgUserInfo(username1,password1,email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return msgUserInfo;
    }

    @Override
    public MsgUserInfo findUser(MsgUserInfo msgUserInfo) {
        MsgUserInfo userInfo = null;
        try {
            String sql = " select * from msg_userinfo where username = ? ";
            executeQuery(sql,msgUserInfo.getUsername());
            while (rs.next()){
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String email = rs.getString("email");
                userInfo = new MsgUserInfo(username1,password1,email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return userInfo;
    }

    @Override
    public int addUser(MsgUserInfo msgUserInfo) {
        try {
            String sql = " insert into msg_userinfo values(?,?,?) ";
            return executeUpdate(sql,msgUserInfo.getUsername(),msgUserInfo.getPassword(),msgUserInfo.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            closeAll();
        }

    }

    @Override
    public List<MsgUserInfo> findUsers() {
        List<MsgUserInfo> msgUserInfoList = new ArrayList<>();
        MsgUserInfo msgUserInfo = null;
        try {
            String sql = " select * from msg_userinfo ";
            executeQuery(sql);
            while (rs.next()){
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String email = rs.getString("email");
                msgUserInfo = new MsgUserInfo(username1,password1,email);
                msgUserInfoList.add(msgUserInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return msgUserInfoList;
    }
}
