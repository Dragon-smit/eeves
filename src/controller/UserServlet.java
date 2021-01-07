package controller;


import entity.MsgUserInfo;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //action=login
        //req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        HttpSession session = req.getSession();
        // 接收数据
        String action = req.getParameter("action");
        UserService userService = new UserServiceImpl();
        if (action.equals("login")){
            // 进行登录处理
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            // 进行登录查询
            MsgUserInfo msgUserInfo = userService.login(username,password);
            if (msgUserInfo != null){
                session.setAttribute("username",msgUserInfo.getUsername());
                resp.sendRedirect("MsgServlet?action=msgList");
            }else{
                req.setAttribute("error","用户名或者密码错误!");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }
        }else if (action.equals("regist")){
            // 执行注册功能
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            // 多于2个参数 最好传类
            MsgUserInfo msgUserInfo = new MsgUserInfo(username,password,email);
            int result  = userService.register(msgUserInfo);
            if (result == 0){
                // 用户名已被注册
                req.setAttribute("error","用户名已被注册");
                req.getRequestDispatcher("register.jsp").forward(req,resp);
            }else if (result == 1){
                // 注册成功
                req.setAttribute("error","注册成功");
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            }else if (result == -1){
                // 系统错误,程序员正在努力修改bug
                req.setAttribute("error","系统错误,程序员正在努力修改bug");
                req.getRequestDispatcher("register.jsp").forward(req,resp);
            }
        }else if (action.equals("logout")){
            // 删除session中的username
            session.removeAttribute("username");
            resp.sendRedirect("index.jsp");
        }else if (action.equals("findUsers")){
            List<MsgUserInfo> msgUserInfoList = userService.findUsers();
            req.setAttribute("users",msgUserInfoList);
            req.getRequestDispatcher("newMsg.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
