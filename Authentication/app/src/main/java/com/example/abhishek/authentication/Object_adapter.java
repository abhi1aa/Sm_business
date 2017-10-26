package com.example.abhishek.authentication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 08-10-2017.
 */

public class Object_adapter extends RecyclerView.Adapter<Object_adapter.MyViewHolder> {
    private ArrayList<Object> obl;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cat;
        public TextView obj;
        public TextView val;
        public TextView msg;
        public MyViewHolder(View view) {
            super(view);
            cat=(TextView)view.findViewById(R.id.tv0);
            obj=(TextView)view.findViewById(R.id.tv1);
            val=(TextView)view.findViewById(R.id.tv2);
            msg=(TextView)view.findViewById(R.id.tv3);
        }
    }

    public Object_adapter() {
        this.obl.add(new Object("","",0,""));
    }

    Object_adapter(ArrayList<Object> obl) {
        this.obl = obl;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.object_list,parent,false);
        return  new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int pos){
        if(obl.size()>0) {
            Object ob = obl.get(pos);
            holder.msg.setText("");
            holder.cat.setText(ob.getOname());
            holder.obj.setText(ob.getOval().toString());
            holder.val.setText(ob.getOmsg());
        }
    }

    @Override
    public int getItemCount(){

        return obl.size()>0 ? obl.size():1;
    }
}
