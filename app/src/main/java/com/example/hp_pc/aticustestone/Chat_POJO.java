package com.example.hp_pc.aticustestone;

/**
 * Created by Interface on 08/04/17.
 */

public class Chat_POJO {

    int userid;
    String msg, name;

    public  Chat_POJO(int userid, String msg, String name){
        this.userid = userid;
        this.msg = msg;
        this.name = name;
    }

    public int getUserId(){
        return userid;
    }

    public String getMessage(){
        return msg;
    }
}
