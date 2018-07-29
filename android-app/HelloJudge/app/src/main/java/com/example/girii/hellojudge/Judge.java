package com.example.girii.hellojudge;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.SmileRating;

public class Judge extends AppCompatActivity {

    LoadDataHelper loadDataHelper;
    public String scanedOutput,gro,period;
    SmileRating mark_one,mark_two,mark_three;
    int First,Second,Third,total;
    DatabaseReference rootRef,demoRef;
    TextView na,cg;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.girii.hellojudge.R.layout.judge);

        //loading image
        ((ImageView)findViewById(com.example.girii.hellojudge.R.id.tez_logo)).setImageResource(com.example.girii.hellojudge.R.drawable.logo);

        loadData();
        //Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        scanedOutput = intent.getStringExtra("QR_DATA");
        na =(TextView)findViewById(com.example.girii.hellojudge.R.id.group_detail);
        cg = (TextView)findViewById(com.example.girii.hellojudge.R.id.group_detail2);
        rootRef = FirebaseDatabase.getInstance().getReference().child("participents").child(scanedOutput);
        demoRef = rootRef;
        //new Send().execute();
        //scanedOutput = bundle.getString("par_id");
        //gro = bundle.getString("par_group");
        //scanedOutput =


        //chronometer


        markOne();
        markTwo();
        markThree();
        swipeButton();

    }


    private void markThree() {

        mark_three= findViewById(com.example.girii.hellojudge.R.id.mark_three);
        mark_three.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley){
                    case SmileRating.BAD:
                        Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_SHORT).show();
                        Third = 2;
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT).show();
                        Third = 4;
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(getApplicationContext(), "great", Toast.LENGTH_SHORT).show();
                        Third = 5;
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(getApplicationContext(), "okay", Toast.LENGTH_SHORT).show();
                        Third = 3;
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(getApplicationContext(), "terrible", Toast.LENGTH_SHORT).show();
                        Third = 1;
                        break;
                }
            }
        });
    }

    private void markTwo() {
        mark_two= findViewById(com.example.girii.hellojudge.R.id.mark_two);
        mark_two.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley){
                    case SmileRating.BAD:
                        Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_SHORT).show();
                        Second = 2;
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT).show();
                        Second = 4;
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(getApplicationContext(), "great", Toast.LENGTH_SHORT).show();
                        Second = 5;
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(getApplicationContext(), "okay", Toast.LENGTH_SHORT).show();
                        Second = 3;
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(getApplicationContext(), "terrible", Toast.LENGTH_SHORT).show();
                        Second = 1;
                        break;
                }
            }
        });
    }

    private void swipeButton() {
        SwipeButton swipeButton = findViewById(com.example.girii.hellojudge.R.id.swipe_to_okay);
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

                //geting time of the participant
                period = chronometer.getText().toString();
                chronometer.stop();//stopping chronometer
                chronometer.setBase(SystemClock.elapsedRealtime()); //resetting chronometer
                //new update().execute();
                int result = First+Second+Third;
                demoRef.child("judge_one").setValue(result);
                demoRef.child("time").setValue(period);
                Toast.makeText(getApplicationContext(), "success"+" : "+period, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Judge.this,Qr_code_min.class);
                startActivity(intent);
                finish();

            }
        });

    }


    private void markOne() {

        mark_one= findViewById(com.example.girii.hellojudge.R.id.mark_one);
        mark_one.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley){
                    case SmileRating.BAD:
                        Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_SHORT).show();
                        First = 2;
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(getApplicationContext(), "good", Toast.LENGTH_SHORT).show();
                        First = 4;
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(getApplicationContext(), "great", Toast.LENGTH_SHORT).show();
                        First = 5;
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(getApplicationContext(), "okay", Toast.LENGTH_SHORT).show();
                        First = 3;
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(getApplicationContext(), "terrible", Toast.LENGTH_SHORT).show();
                        First = 1;
                        break;
                }
            }
        });

    }

    private void loadData() {
        if(loadDataHelper != null){
            loadDataHelper.cancel(true);
        }
        loadDataHelper = new LoadDataHelper();
        loadDataHelper.execute();
    }

    private class LoadDataHelper extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try{

                Thread.sleep(3000);

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateData();
        }
    }

    private void updateData() {
        ((TextView)findViewById(com.example.girii.hellojudge.R.id.group_id)).setText(scanedOutput);
       // ((TextView)findViewById(R.id.group_detail)).setText(gro);
        chronometer = findViewById(com.example.girii.hellojudge.R.id.chronometer);
        chronometer.start();
    }
    //
    private class Send extends AsyncTask<String, String, String> {

        String msg;

        @Override
        protected String doInBackground(String... strings) {
            try {
                /*demoRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);
                        String p_d_b = user.dept_and_branch;
                        String p_college = user.college_name;
                        na.setText(p_d_b);
                        cg.setText(p_college);


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });*/
                ValueEventListener first = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        String p_d_b = user.dept_and_branch;
                        String p_college = user.college_name;
                        na.setText(p_d_b);
                        cg.setText(p_college);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                demoRef.addValueEventListener(first);
            }catch (Exception e){
                e.printStackTrace();
                msg= "varala";

            }
            return null;

        }

    }
}
