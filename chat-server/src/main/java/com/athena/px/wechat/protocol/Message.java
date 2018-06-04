package com.athena.px.wechat.protocol;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/1 19:29
 */
public class Message {

    //发送人
    private String sender;

    //接收人
    private String receive;

    //发送内容
    private String content;

    //发送时间
    private String date;

    //发送类型(LOGIN/LOGOUT...)
    private String protocol;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
