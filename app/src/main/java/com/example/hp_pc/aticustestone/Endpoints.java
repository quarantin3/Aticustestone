package com.example.hp_pc.aticustestone;

/**
 * Created by root on 1/3/17.
 */

public class Endpoints {

    public static final String BASE_URL  = "https://ec2.xynocast.com/gcm_chat/v1/index.php";

    public static final String URL_REGISTER = BASE_URL + "/user/register";
    public static final String URL_LOGIN = BASE_URL + "/user/login";
    public static final String URL_USER = BASE_URL + "/user/_ID_";


    //for searching of lawyer
    public static final String URL_SEARCHLAW = BASE_URL + "/user/search";
    public static final String URL_SEARCHCOURTS = BASE_URL + "/user/search/courts";
    public static final String URL_LAWDETAILS = BASE_URL + "/user/lawyer/details";


    // sending the notification message
    public static final String URL_SEND_MESSAGE = "https://ec2.xynocast.com/atticus/include/sendSinglePush.php";
    public static final String URL_SEND_MULTIPLE_SEND = "https://ec2.xynocast.com/atticus/include/sendMultiplePush.php";

    // fetch msgs from userid
    public static final String URL_FETCH_MSGS = "https://ec2.xynocast.com/gcm_chat/v1/index.php/fetch/chats";

    //self userid
    public static final String self_userid = "103";

}
