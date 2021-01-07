package service;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.MsgUserInfo;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public MsgUserInfo login(String username, String password) {

        return userDao.login(username,password);
    }

    @Override
    public int register(MsgUserInfo msgUserInfo) {

        // 去数据库中查询是否有此用户
        MsgUserInfo userInfo = userDao.findUser(msgUserInfo);
        if (userInfo == null){
            // 如果没有注册 新增用户
           return userDao.addUser(msgUserInfo);
        }
        return 0;
    }

    @Override
    public List<MsgUserInfo> findUsers() {
        return userDao.findUsers();
    }
}
