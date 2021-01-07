package controller;

import entity.Msg;
import service.MsgService;
import service.MsgServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class MsgServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MsgService msgService = new MsgServiceImpl();
        resp.setContentType("text/html; charset=utf-8");

        PrintWriter out = resp.getWriter();
        // 接收消息
        String action = req.getParameter("action");
        // 获取当前登录的用户名
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        if (action.equals("msgList")){
            List<Msg> msgList = msgService.findMsgList(username);
            req.setAttribute("msgList",msgList);
            req.getRequestDispatcher("main.jsp").forward(req,resp);
        }else if("msg".equals(action)){
            //MsgServlet?action=msg&msgid="+${msg.msgid}
            String msgid = req.getParameter("msgid");
            // 根据id 查询单条新闻 将未读状态 改为已读状态
            Msg msg =  msgService.findMsg(msgid);
            req.setAttribute("msg",msg);
            req.getRequestDispatcher("readMsg.jsp").forward(req,resp);
        }else if("delete".equals(action)){
            // 执行删除功能
            String msgid = req.getParameter("msgid");
            boolean result = msgService.deleteMsg(msgid);
            if (result){
                req.setAttribute("error","删除成功");
            }else{
                req.setAttribute("error","删除失败");
            }
            req.getRequestDispatcher("MsgServlet?action=msgList").forward(req,resp);
        }else if ("send".equals(action)){

            String toUser = req.getParameter("toUser");
            String title = req.getParameter("title");
            String content = req.getParameter("content");
            Integer state = 1;
            Date createDate = new Date();
            Msg msg = new Msg();
            msg.setMsgcontent(content);
            msg.setMsgCreateDate(createDate);
            msg.setSendto(toUser);
            msg.setState(state);
            msg.setTitle(title);
            msg.setUsername(username);
            boolean result = msgService.addMsg(msg);
            if (!result){
                out.print("<script type=\"application/javascript\" >");
                out.print(" alert(\"发送失败!\");");
                out.print("location.href=\"newMsg.jsp\";");
                out.print("</script>");
            }
            req.getRequestDispatcher("MsgServlet?action=msgList").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
