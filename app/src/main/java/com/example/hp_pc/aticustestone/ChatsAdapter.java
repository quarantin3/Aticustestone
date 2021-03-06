package com.example.hp_pc.aticustestone;

/**
 * Created by root on 10/4/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Interface on 08/04/17.
 */

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder>  {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView msg_tv;
        Context context;

        public ViewHolder(Context context, View itemView){
            super(itemView);

            this.msg_tv = (TextView)itemView.findViewById(R.id.textViewMessage);
            this.context = context;
        }
    }

    String userid;
    List<Chat_POJO> messages = new ArrayList<>();
    Context context;
    String SELF = Endpoints.self_userid;

    public ChatsAdapter(Context context, List<Chat_POJO> messages, String userid){
        this.userid = userid;
        this.messages = messages;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position){
        Chat_POJO msg_temp = messages.get(position);
        if (msg_temp.getUserId() == userid)
            return -123;
        else
            return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        ViewHolder vh;

        if (viewType == -123) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_self, parent, false);
            vh = new ViewHolder(parent.getContext(), itemView);
        }
        else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_other, parent, false);
            vh = new ViewHolder(parent.getContext(), itemView);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chat_POJO chat = messages.get(position);
        holder.msg_tv.setText(chat.getMessage());

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


}