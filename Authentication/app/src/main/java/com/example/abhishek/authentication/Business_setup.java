package com.example.abhishek.authentication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Business_setup extends AppCompatActivity {
    DatabaseReference ref;
    ArrayList<Employee> emp=new ArrayList<>();
    ArrayList<EditText> edt=new ArrayList<>();
    ArrayList<EditText> eno=new ArrayList<>();
    ArrayList<EditText> efn=new ArrayList<>();
    ArrayList<Functions> func=new ArrayList<>();
    public int num=3,n=3;
    private TextView mTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_setup);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl);
        LinearLayout ll=(LinearLayout)findViewById(R.id.llayout);
        RelativeLayout rlc=(RelativeLayout)findViewById(R.id.rlc);
        rl.setVisibility(View.GONE);
        ll.setVisibility(View.GONE);
        rlc.setVisibility(View.VISIBLE);
        //  FrameLayout fl=(FrameLayout)findViewById(R.id.content);
        // fl.setVisibility(View.GONE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl);
                    LinearLayout ll=(LinearLayout)findViewById(R.id.llayout);
                    RelativeLayout rlc=(RelativeLayout)findViewById(R.id.rlc);
                    rl.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                    rlc.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    RelativeLayout rl1=(RelativeLayout)findViewById(R.id.rl);
                    LinearLayout ll1=(LinearLayout)findViewById(R.id.llayout);
                    RelativeLayout rlc1=(RelativeLayout)findViewById(R.id.rlc);
                    rl1.setVisibility(View.VISIBLE);
                    ll1.setVisibility(View.GONE);
                    rlc1.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_notifications:
                    RelativeLayout rl2=(RelativeLayout)findViewById(R.id.rl);
                    LinearLayout ll2=(LinearLayout)findViewById(R.id.llayout);
                    RelativeLayout rlc2=(RelativeLayout)findViewById(R.id.rlc);
                    rl2.setVisibility(View.GONE);
                    ll2.setVisibility(View.VISIBLE);
                    rlc2.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };


    public void f_launch(View view) {
        EditText eb=(EditText)findViewById(R.id.bid);
        String bid=eb.getText().toString();
        EditText ebn=(EditText)findViewById(R.id.bname);
        String bname=ebn.getText().toString();
        ref=FirebaseDatabase.getInstance().getReference(bid);
        ref.child("Business name").setValue(bname);
        Toast.makeText(this, "Data stored", Toast.LENGTH_SHORT).show();
    }

    public void add_user(View view) {
        EditText eu1=(EditText)findViewById(R.id.usr1);
        eu1.setVisibility(View.VISIBLE);
        EditText eu2=(EditText)findViewById(R.id.usr2);
        eu2.setVisibility(View.VISIBLE);
        Button bt=(Button)findViewById(R.id.more);
        bt.setVisibility(View.VISIBLE);
    }

    public void more_user(View view) {
        int i=0;
        LinearLayout ll=(LinearLayout)findViewById(R.id.llayout);
        EditText et=new EditText(this);
        EditText en=new EditText(this);
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams f=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(p);
        et.setHint("Username  ");
        et.setId(++num);
        et.setGravity(Gravity.CENTER_HORIZONTAL);
        en.setLayoutParams(f);
        en.setHint("No.");
        en.setGravity(Gravity.CENTER_HORIZONTAL);
        en.setId(++num);
        ll.addView(et);
        ll.addView(en);
        edt.add(et);
        eno.add(en);
    }

    public void store(View view) {
        EditText eu1=(EditText)findViewById(R.id.usr1);
        String u1=eu1.getText().toString();
        EditText ef1=(EditText)findViewById(R.id.fno1);
        Integer x=Integer.parseInt(ef1.getText().toString());
        emp.add(new Employee(u1,x));
        EditText eu2=(EditText)findViewById(R.id.usr2);
        String u2=eu2.getText().toString();
        EditText ef2=(EditText)findViewById(R.id.fno2);
        Integer y=Integer.parseInt(ef2.getText().toString());
        emp.add(new Employee(u2,y));
        for(int j=0;j<edt.size();j++) {
            String u3 = edt.get(j).getText().toString();
            Integer i=Integer.parseInt(eno.get(j).getText().toString());
            emp.add(new Employee(u3, i));
        }
        ref.child("Employees").setValue(emp);
    }

    public void business(){

    }

    public void moref(View view) {
        int i=0;
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.rl);
        EditText er=new EditText(this);
        RelativeLayout.LayoutParams r=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        if(n==3)
           r.addRule(RelativeLayout.BELOW,R.id.m);
        else {
            i = n - 1;
            r.addRule(RelativeLayout.BELOW, i);
        }

        er.setLayoutParams(r);
        er.setHint("Functions "+n);
        er.setId(n++);
        er.setGravity(Gravity.CENTER_HORIZONTAL);
        rl.addView(er);
        efn.add(er);
    }

    public void createf(View view) {
        EditText f1=(EditText)findViewById(R.id.f1);
        EditText f2=(EditText)findViewById(R.id.f2);
        func.add(new Functions(f1.getText().toString(),1));
        func.add(new Functions(f2.getText().toString(),2));
        for(int j=0;j<efn.size();j++)
        {
            func.add(new Functions(efn.get(j).getText().toString(),j+3));
        }
        ref.child("Functions").setValue(func);
    }
}
