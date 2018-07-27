package com.example.linus.hellojudge;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.hsalf.smilerating.SmileRating;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Judge extends AppCompatActivity {

    LoadDataHelper loadDataHelper;
    public String scanedOutput,gro,period;
    SmileRating mark_one,mark_two,mark_three;
    int First,Second,Third,total;
    private static final String db = "jdbc:mysql://192.182.183.13/tezfuerza";
    private static final String user = "sasurie";
    private static final String pass = "sasurieinfotech";
    Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.judge);
        chronometer = findViewById(R.id.chronometer);
        chronometer.start();

        //loading image
        ((ImageView)findViewById(R.id.tez_logo)).setImageResource(R.drawable.logo);



        loadData();
        //Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        scanedOutput = intent.getStringExtra("par_id");
        gro = intent.getStringExtra("par_group");
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

        mark_three= findViewById(R.id.mark_three);
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
        mark_two= findViewById(R.id.mark_two);
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
        SwipeButton swipeButton = findViewById(R.id.swipe_to_okay);
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

                //geting time of the participant
                period = chronometer.getText().toString();

                chronometer.stop();//stopping chronometer

                chronometer.setBase(SystemClock.elapsedRealtime()); //resetting chronometer


                Toast.makeText(getApplicationContext(), "success"+" : "+period, Toast.LENGTH_SHORT).show();
                new Send().execute();
                Intent intent = new Intent(Judge.this,Qr_code.class);
                startActivity(intent);
                finish();

            }
        });

    }


    private void markOne() {

        mark_one= findViewById(R.id.mark_one);
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
        ((TextView)findViewById(R.id.group_id)).setText(scanedOutput);
        ((TextView)findViewById(R.id.group_detail)).setText(gro);
    }
        //
    private class Send extends AsyncTask<String, String, String> {

        String msg;

        @Override
        protected String doInBackground(String... strings) {
            total = First+Second+Third;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(db,user,pass);
                Statement stmt = conn.createStatement();
                String sql = "update multimedia set stud_group_id='"+gro+"',j1_total='"+total+"'where id='"+scanedOutput+"'";
                //String sql="update multimedia set id ='20',stud_group_id='20',mark_1='20',mark_2='20',mark_3='20',total_mark='60.0',start_time='1',end_time='2',completed='*' ";
                //String sql ="insert into final(id,stud_group_id,j1_m_1,j1_m_2,j1_m_3,j1_total,start_time,end_time,completed) values('"+par+"','"+gro+"','"+num1+"','"+num2+"','"+num3+"','"+total+"','1','2','*')";
//                String sql="update final set id='"+par+"',stud_group_id='"+gro+"',j1_m_1='"+num1+"',j1_m_2='"+num2+"',j1_m_3='"+num3+"',j1_total='"+total+"'";
                stmt.executeUpdate(sql);
                //msg= "success";
               // Intent intent = new Intent(Judge.this,Qr_code.class);
                //startActivity(intent);
                //finish();
            }catch (Exception e){
                e.printStackTrace();
                msg= "varala";
            }
            return null;

        }

    }

}
