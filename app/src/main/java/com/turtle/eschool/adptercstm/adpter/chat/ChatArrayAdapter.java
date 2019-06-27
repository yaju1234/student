package com.turtle.eschool.adptercstm.adpter.chat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.turtle.eschool.R;

import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage> {

    private TextView senderrmessage_text, receivermessage_text;
    private List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
    private RelativeLayout receiver_container, sender_container;
    @Override
    public void add(ChatMessage object)
    {
        chatMessageList.add(object);
        super.add(object);
    }
    public ChatArrayAdapter(Context context, int textViewResourceId,List<ChatMessage> chatMessageList) {
        super(context, textViewResourceId);
        this.chatMessageList=chatMessageList;
    }
    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_chat_singlemessage, parent, false);
        }
        receiver_container = (RelativeLayout) row.findViewById(R.id.receiver_container);
        sender_container = (RelativeLayout) row.findViewById(R.id.sender_container);
        ChatMessage chatMessageObj = getItem(position);
        senderrmessage_text = (TextView) row.findViewById(R.id.senderrmessage_text);
        receivermessage_text = (TextView) row.findViewById(R.id.receivermessage_text);


        /*if(chatMessageObj.left)
        {
            receiver_container.setVisibility(View.VISIBLE);
            receivermessage_text.setText(chatMessageObj.message);
        }
        else
        {
            sender_container.setVisibility(View.VISIBLE);
            senderrmessage_text.setText(chatMessageObj.message);
        }*/

        if (chatMessageObj.getCHATS_SIDE().equalsIgnoreCase("t")) {
            receiver_container.setVisibility(View.VISIBLE);
            sender_container.setVisibility(View.GONE);
            receivermessage_text.setText(chatMessageObj.getCHATS_MESSAGE());
            /*receiver_container.setGravity(Gravity.LEFT);
            receiver_container
                    .setBackgroundResource(R.drawable.background_incoming_message);*/
        } else {
           /* receiver_container.setGravity(Gravity.RIGHT);
            receiver_container
                    .setBackgroundResource(R.drawable.background_outgoing_message);*/
            receiver_container.setVisibility(View.GONE);
            sender_container.setVisibility(View.VISIBLE);
            senderrmessage_text.setText(chatMessageObj.getCHATS_MESSAGE());
        }


        //receiver_container.setGravity(chatMessageObj.left ? Gravity.RIGHT : Gravity.LEFT);
        //receiver_container.setBackgroundResource(chatMessageObj.left ? R.drawable.background_outgoing_message : R.drawable.background_incoming_message);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

}