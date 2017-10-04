package com.example.abhishek.authentication;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_in extends AppCompatActivity {
    String email;
    private static final String TAG="Sign_in";
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mauth=FirebaseAuth.getInstance();
        if(mauth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),profile.class));
            finish();
     }
    }

    public void signin(View view) {

        int f=0;
        EditText edname = (EditText) findViewById(R.id.inname);
        EditText edpass = (EditText) findViewById(R.id.inpass);
        String ina = edname.getText().toString();
        String ipa = edpass.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(ina).matches()) {
            f++;
            mauth.signInWithEmailAndPassword(ina, ipa)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Log.d(TAG, "signed in" + task.isSuccessful());
                                Toast.makeText(Sign_in.this, "Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), profile.class));
                                finish();
                            }
                            if (!task.isSuccessful()) {
                                Toast.makeText(Sign_in.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }
        else
        {
            DatabaseReference ref= FirebaseDatabase.getInstance().getReference("User id").child(ina);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    email=dataSnapshot.getValue().toString();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mauth.signInWithEmailAndPassword(email, ipa)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Log.d(TAG, "signed in" + task.isSuccessful());
                                Toast.makeText(Sign_in.this, "Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), profile.class));
                                finish();
                            }
                            if (!task.isSuccessful()) {
                                Toast.makeText(Sign_in.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }

    }

}

