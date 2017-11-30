package com.example.rs.chatserverjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by rs on 11/30/17.
 */

public class Adapter extends BaseAdapter {
    private Context mcontext;

    public Adapter(Context mcontext, LayoutInflater mlayout, ArrayList<String> arrayList) {
        this.mcontext = mcontext;
        this.mlayout = mlayout;
        this.arrayList = arrayList;
    }

    private LayoutInflater mlayout;
    private ArrayList<String> arrayList;




    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolders view;

        if(convertView == null){
            convertView = mlayout.inflate(R.layout.activity_row_message,null);
            view = new viewHolders();
            view.view = (TextView) convertView.findViewById(R.id.textView_message_row);
            convertView.setTag(view);
        }
        else {
            view = (viewHolders) convertView.getTag();
        }
        MessageModel messageModel = new MessageModel();

        return null;
    }

    public static class viewHolders {
        TextView view;
    }
}

