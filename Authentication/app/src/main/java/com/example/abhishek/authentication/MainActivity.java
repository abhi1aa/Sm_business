
package com.example.abhishek.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mauth;
    //private FirebaseAuth.AuthStateListener mauthlisten;
    DatabaseReference refid= FirebaseDatabase.getInstance().getReference("User id");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth=FirebaseAuth.getInstance();
        if(mauth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),profile.class));
            finish();
        }
    }

   /* @Override
    public void onStart(){
        super.onStart();
        mauth.addAuthStateListener(mauthlisten);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mauthlisten!=null){
            mauth.removeAuthStateListener(mauthlisten);
        }
    }
*/

    public void createnew(View view) {
        EditText edmail=(EditText)findViewById(R.id.email);
        EditText edpass=(EditText)findViewById(R.id.pass);
        final String mail=edmail.getText().toString();
        String pass=edpass.getText().toString();
        mauth.createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Auth success",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                            Bundle b=new Bundle();
                            b.putString("mail",mail);
                            i.putExtras(b);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void login(View view){
        startActivity(new Intent(this,Sign_in.class));
        finish();
    }



}
