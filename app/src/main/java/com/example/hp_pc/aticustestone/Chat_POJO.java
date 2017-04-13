package com.example.hp_pc.aticustestone;

/**
 * Created by root on 10/4/17.
 */

public class Chat_POJO {

    String userid;
    String msg, name;
    int msg_id;

    public  Chat_POJO(String userid, String msg, String name, int msg_id){
        this.userid = userid;
        this.msg = msg;
        this.name = name;
        this.msg_id = msg_id;
    }

    public String getUserId(){
        return userid;
    }

    public String getMessage(){
        return msg;
    }
}