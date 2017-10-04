package com.example.abhishek.authentication;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    String umail;
    DatabaseReference uref= FirebaseDatabase.getInstance().getReference("User id");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle b=getIntent().getExtras();
        umail=b.getString("mail");
    }
    public void check(View view) {
        uref.addListenerForSingleValueEvent(new ValueEventListener() {
            EditText et=(EditText)findViewById(R.id.usern);
            String uname=et.getText().toString();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(uname)){
                    Toast.makeText(Main2Activity.this,"already exists",Toast.LENGTH_SHORT).show();
                }
                else{
                    uref.child(uname).setValue(umail);
                    startActivity(new Intent(getApplicationContext(),profile.class));
                    Toast.makeText(Main2Activity.this,"Username created",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main2Activity.this,"task failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
