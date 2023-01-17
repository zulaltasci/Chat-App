package com.example.mychatapp.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mychatapp.R;

public class MessageList extends RecyclerView.Adapter<MessageList.MyViewHolder> {

    private ArrayList<MessageData> messageDataArrayList;
    private Context context;

    public MessageList(ArrayList<MessageData> messageDataArrayList, Context context) {
        this.messageDataArrayList = messageDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messageitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messageDataArrayList.size();
    }

    static class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView messagetitle,messagedesc;
        public MyViewHolder(@NonNull View itemView) {
            messagetitle=itemView.findViewById(R.id.message_title);
            messagedesc=itemView.findViewById((R.id.message_desc));

            super(itemView);
        }
    }
}
