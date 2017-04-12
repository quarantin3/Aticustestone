package com.example.hp_pc.aticustestone;

import android.content.Context;
import android.content.SharedPreferences;

import static java.sql.Types.NULL;

/**
 * Created by Hp-PC on 1/24/2017.
 */

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "FCMSharedPref";
    private static final String TAG_TOKEN = "tagtoken";
    private static final String TAG_USERID = "userid";
    private static final String TAG_LAW_ID = "lawid";
    private static final String TAG_EMAIL = "tagemail";
    private static final String TAG_SEARCH = "tagsearch";
    private static final String TAG_COMM = "commmethod";
    private static final String IS_LOGIN = "IsloggedIn";
    private static final String KEY_EMAIL = "email";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    public boolean setloginbool(String login){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IS_LOGIN, login);
        editor.apply();
        return true;
    }


    //this method will fetch the device token from shared preferences
    public String getloginbool(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(IS_LOGIN, null);
    }

    //this method will save the device token to shared preferences
    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }





    //this method will fetch the device token from shared preferences
    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TOKEN, null);
    }


<<<<<<< HEAD
    public boolean savetoSend(String lawid) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_LAW_ID, lawid);
        editor.apply();
        return true;
    }

    //return the device email
    public String gettoSend() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG_LAW_ID, null);
    }

    public boolean saveUserid(String userid) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_USERID, userid);
        editor.apply();
        return true;
    }

    //return the device email
    public String getDeviceuserid() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG_USERID, null);
    }
=======


>>>>>>> chatactivity send


    //save the email address
    public boolean saveEmail(String email) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_EMAIL, email);
        editor.apply();
        return true;
    }

    //return the device email
    public String getDeviceEmail() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG_EMAIL, null);
    }


    public boolean saveSearchfield(int field) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TAG_SEARCH, field);
        editor.apply();
        return true;
    }

    //return the device email
    public int getSearchField() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(TAG_SEARCH, NULL);
    }

    public boolean saveComm(String comm) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_COMM, comm);
        editor.apply();
        return true;
    }

    //return the device email
    public String getComm() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG_COMM, null);
    }


}

