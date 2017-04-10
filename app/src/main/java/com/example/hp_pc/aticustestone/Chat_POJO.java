package com.example.hp_pc.aticustestone;

/**
 * Created by root on 10/4/17.
 */

public class Chat_POJO {

    String userid;
    String msg, name;

    public  Chat_POJO(String userid, String msg, String name){
        this.userid = userid;
        this.msg = msg;
        this.name = name;
    }

    public String getUserId(){
        return userid;
    }

    public String getMessage(){
        return msg;
    }
}