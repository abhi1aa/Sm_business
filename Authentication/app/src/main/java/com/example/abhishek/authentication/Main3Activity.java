package com.example.abhishek.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference mref = FirebaseDatabase.getInstance().getReference("Data");
    FirebaseAuth mauth=FirebaseAuth.getInstance();
    ArrayList<Functions> funct = new ArrayList();
    String str;
    String bid;
    private ArrayList<Object> ob=new ArrayList<>();
    private ArrayList<Object> of=new ArrayList<>();
    private RecyclerView rv;
    private Object_adapter oa;
    int c=1;
    SharedPreferences spref1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        spref1= this.getSharedPreferences("bbb",MODE_PRIVATE);
        /*try {
            InputStream in=openFileInput(fname);
            if(in!=null){
                InputStreamReader inr=new InputStreamReader(in);
                BufferedReader buf=new BufferedReader(inr);
                String ret="";
                StringBuilder sb=new StringBuilder();
                while ( ( ret =buf.readLine())!=null){
                    sb.append(ret);
                }
                in.close();
                ret=sb.toString();


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        bid=spref1.getString("Bid","j");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
              cjoin();
               oa=new Object_adapter(of);
               rv.setAdapter(oa);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Bundle bundle = getIntent().getExtras();
       // bid = bundle.getString("bid");

        mref.child(bid + "/Functions").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dsnap : dataSnapshot.getChildren()) {
                    Functions funt = dsnap.getValue(Functions.class);
                    funct.add(funt);

                }
                preparedata();
                rv=(RecyclerView)findViewById(R.id.rv);
                oa=new Object_adapter(ob);
                oa.notifyDataSetChanged();
                RecyclerView.LayoutManager rm=new GridLayoutManager(Main3Activity.this,2);
                rv.setLayoutManager(rm);
                rv.setItemAnimator(new DefaultItemAnimator());
                rv.setAdapter(oa);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main3Activity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
     /*   EditText ed1=(EditText)findViewById(R.id.ed1);
        EditText ed2=(EditText)findViewById(R.id.ed2);
        EditText ed3=(EditText)findViewById(R.id.ed3);
        Button bt1=(Button)findViewById(R.id.bt1);*/
        CardView cv=(CardView)findViewById(R.id.cv);
        cv.setVisibility(View.GONE);
       /* ed1.setVisibility(View.GONE);
        ed2.setVisibility(View.GONE);
        ed3.setVisibility(View.GONE);
        bt1.setVisibility(View.GONE);
*/


    }

    public void preparedata() {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Data/"+bid+"/Function_Data");
        spref1 = this.getSharedPreferences("bbb", MODE_PRIVATE);
        int fcount = spref1.getInt("Fcount", 0);
        //Toast.makeText(this,funct.get(0).getFname(),Toast.LENGTH_SHORT).show();
        for (int i = 0; i < funct.size(); i++) {

            if (fcount == funct.get(i).getFnum()) {
                str = funct.get(i).getFname();
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
            }
        }
        ref.child(str).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                of.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    of.add(ds.getValue(Object.class));
                    //Toast.makeText(Main3Activity.this,ob.get(0).getOmsg(),Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main3Activity.this,"failed",Toast.LENGTH_SHORT).show();

            }
        });


       ob.add(new Object("sfdf","asf",3,"wrdg"));
        ob.add(new Object("sfdf","asf",4,"wrdg"));
        ob.add(new Object("sfdf","asf",5,"wrdg"));
        ob.add(new Object("sfdf","asf",6,"wrdg"));
        ob.add(new Object("sfdf","asf",7,"wrdg"));
        ob.add(new Object("sfdf","asf",8,"wrdg"));
        ob.add(new Object("sfdf","asf",9,"wrdg"));
        ob.add(new Object("sfdf","asf",10,"wrdg"));




    }


    public void cjoin() {
        if(c%2==1) {
            c++;
      /*  EditText ed1=(EditText)findViewById(R.id.ed1);
        EditText ed2=(EditText)findViewById(R.id.ed2);
        EditText ed3=(EditText)findViewById(R.id.ed3);
        Button  bt1=(Button)findViewById(R.id.bt1);*/
            CardView cv = (CardView) findViewById(R.id.cv);
            cv.setVisibility(View.VISIBLE);
      /*  ed1.setVisibility(View.VISIBLE);
        ed2.setVisibility(View.VISIBLE);
        ed3.setVisibility(View.VISIBLE);
        bt1.setVisibility(View.VISIBLE);*/

            //Bundle bundle=getIntent().getExtras();
            spref1 = this.getSharedPreferences("bbb", MODE_PRIVATE);
            int fcount = spref1.getInt("Fcount", 0);
           // Toast.makeText(this,funct.size(),Toast.LENGTH_SHORT).show();
            for (int i = 0; i < funct.size(); i++) {
               // Toast.makeText(this,funct.get(i).getFname(),Toast.LENGTH_SHORT).show();
                if (fcount == funct.get(i).getFnum()) {
                    TextView text = (TextView) findViewById(R.id.fun);

                    text.setText("" + funct.get(i).getFname());
                    str = funct.get(i).getFname();
                }

            }
        }
        else{
            CardView cv = (CardView) findViewById(R.id.cv);
            cv.setVisibility(View.GONE);
            c++;

        }


    }

    public void update(View view) {
        EditText ed1=(EditText)findViewById(R.id.ed1);
        String e1=ed1.getText().toString();
        EditText ed2=(EditText)findViewById(R.id.ed2);
        String e2 =ed2.getText().toString();
        EditText ed3=(EditText)findViewById(R.id.ed3);
        String e3=ed3.getText().toString();
        ed1.clearAnimation();
        ed2.clearAnimation();
        ed3.clearAnimation();
        Object ob=new Object(str,e1,Integer.parseInt(e2),e3);
        mref.child(bid).child("Function_Data").child(str).child(e1).setValue(ob);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
            // Handle navigation view item clicks here.

            int id = item.getItemId();

            if (id == R.id.nav_camera) {
                // Handle the camera action
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {


            } else if (id == R.id.nav_manage) {

            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

                mauth.signOut();
                SharedPreferences ref=this.getSharedPreferences("aaa",MODE_PRIVATE);
                SharedPreferences.Editor ed=ref.edit();
                ed.putInt("Access",0);
                ed.commit();
                startActivity(new Intent(this, MainActivity.class));
                finish();

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    public void bt(MenuItem item) {

    }

}
