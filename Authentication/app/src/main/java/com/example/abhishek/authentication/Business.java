package com.example.abhishek.authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Business extends AppCompatActivity implements frga_bmain.OnFragmentInteractionListener,fragment_business.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
   private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
   private ViewPager mViewPager;

    DatabaseReference ref;
    SharedPreferences spref,sref1;
    SharedPreferences mref;
    ArrayList<Object> ob=new ArrayList<>();
    ArrayList<ArrayList<Object>> ob1=new ArrayList<ArrayList<Object>>();
    ArrayList<String> fname=new ArrayList<>();
    FirebaseAuth mauth;
    String it;
    int d,i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        mauth=FirebaseAuth.getInstance();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
       mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.

       mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_business, menu);
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

    public void click(View view) {
        spref=this.getSharedPreferences("bbb",MODE_PRIVATE);
        sref1=this.getSharedPreferences("uuu",MODE_PRIVATE);
        String s=sref1.getString("user","");
        String t=spref.getString("Bname","");
        ref=FirebaseDatabase.getInstance().getReference("Data/"+s);
        ref.child("Functions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fs : dataSnapshot.getChildren()) {
                    fname.add(fs.child("fname").getValue(String.class));
                }//Toast.makeText(Business.this, fname.get(0), Toast.LENGTH_SHORT).show();
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("Function_Data")) {

                                Toast.makeText(Business.this, fname.get(d), Toast.LENGTH_SHORT).show();
                                ref.child("Function_Data").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (d = 0; d < fname.size(); d++) {
                                            for (DataSnapshot ds : dataSnapshot.child(fname.get(d)).getChildren()) {
                                                //Toast.makeText(Business.this, fname.get(1), Toast.LENGTH_SHORT).show();
                                                Object o = ds.getValue(Object.class);
                                                ob.add(o);
                                                TextView tv = (TextView) findViewById(R.id.section_label);
                                                tv.setText(ob.get(0).getOcategory());
                                                TextView tv1 = (TextView) findViewById(R.id.tv1);
                                                tv1.setText(ob.get(0).getOname());
                                                TextView tv2 = (TextView) findViewById(R.id.tv2);
                                                tv2.setText(ob.get(0).getOval().toString());
                                                TextView tv3 = (TextView) findViewById(R.id.tv3);
                                                tv3.setText(ob.get(0).getOmsg());
                                            }
                                            ob1.add(new ArrayList<Object>(ob));
                                            ob.clear();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });


                            } else {
                                Toast.makeText(Business.this, "No data", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(Business.this, "failed", Toast.LENGTH_LONG).show();

                        }
                    });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    @Override
    public void onFragmentInteraction(Uri uri){

    }

    public void log_out(MenuItem item) {
        mauth.signOut();
        SharedPreferences ref=this.getSharedPreferences("aaa",MODE_PRIVATE);
        SharedPreferences.Editor ed=ref.edit();
        ed.putInt("Access",0);
        ed.apply();
        ref=Business.this.getSharedPreferences("uuu",MODE_PRIVATE);
        SharedPreferences.Editor e=ref.edit();
        e.putString("user","");
        e.apply();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void card_more(View view) {
        fname.clear();
        for(i=0;i<ob1.size();i++) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.llayout);
            final CardView card = new CardView(getApplicationContext());
            ViewPager.LayoutParams params = new ViewPager.LayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = 450;
            card.setLayoutParams(params);
            card.setCardElevation(10);

            LinearLayout ll1 = new LinearLayout(getApplicationContext());
            ViewPager.LayoutParams param0 = new ViewPager.LayoutParams();
            param0.width = ViewGroup.LayoutParams.MATCH_PARENT;
            param0.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            ll1.setOrientation(LinearLayout.VERTICAL);

            TextView tv0 = new TextView(getApplicationContext());
            ViewPager.LayoutParams param1 = new ViewPager.LayoutParams();
            param1.width = ViewGroup.LayoutParams.MATCH_PARENT;
            param1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            tv0.setLayoutParams(param1);
            tv0.setText(ob1.get(i).get(0).getOcategory());
            tv0.setBackgroundColor(Color.parseColor("#c5c3cc"));
            tv0.setTextSize(25);
            //fname.add(ob1.get(i).get(0).getOcategory());

            TextView tv1 = new TextView(getApplicationContext());
            tv1.setLayoutParams(param1);
            tv1.setText(ob1.get(i).get(0).getOname());

            TextView tv2 = new TextView(getApplicationContext());
            tv2.setLayoutParams(param1);
            tv2.setText(ob1.get(i).get(0).getOval().toString());


            TextView tv3 = new TextView(getApplicationContext());
            tv3.setLayoutParams(param1);
            tv3.setText(ob1.get(i).get(0).getOmsg());

            ll1.addView(tv0);
            ll1.addView(tv1);
           ll1.addView(tv2);
           ll1.addView(tv3);
            View c=getLayoutInflater().inflate(R.layout.object_list,null);
            TextView t=(TextView)c.findViewById(R.id.tv1);
            t.setText(ob1.get(i).get(0).getOname());
            ll.addView(c);

            card.addView(ll1);
            ll.addView(card);
            card.setId(i);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b=new Bundle();
                    b.putString("category",fname.get(card.getId()));
                    Intent in=new Intent(getApplicationContext(),ItemListActivity.class);
                    in.putExtras(b);
                    startActivity(in);
                }
            });
        }
        Toast.makeText(this,ob1.get(1).get(1).getOname(),Toast.LENGTH_SHORT).show();
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0: fragment_business fb=new fragment_business();
                        return fb;
                case 1: frga_bmain fg=new frga_bmain();
                        return fg;

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
