package entity;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

public class Msg implements Serializable {
    private Integer msgid;
    private String username;
    private String title;
    private String msgcontent;
    private Integer state;
    private String sendto;
    private Date msgCreateDate;

    public Msg() {
    }

    public Msg(Integer msgid, String username, String title, String msgcontent, Integer state, String sendto, Date msgCreateDate) {
        this.msgid = msgid;
        this.username = username;
        this.title = title;
        this.msgcontent = msgcontent;
        this.state = state;
        this.sendto = sendto;
        this.msgCreateDate = msgCreateDate;
    }

    public Date getMsgCreateDate() {
        return msgCreateDate;
    }

    public void setMsgCreateDate(Date msgCreateDate) {
        this.msgCreateDate = msgCreateDate;
    }

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSendto() {
        return sendto;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }


}
