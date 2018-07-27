package com.example.linus.hellojudge;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class submit extends AppCompatActivity {
    private static final String db = "jdbc:mysql://192.182.183.13/tezfuerza";
    private static final String user = "sasurie";
    private static final String pass = "sasurieinfotech";
    int result ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(db, user, pass);
                    Statement stmt = conn.createStatement();
                    String submit = "select sum(j1_total),sum(j2_total) from multimedia";
                    ResultSet rs = stmt.executeQuery(submit);
                    while (rs.next()) {
                        int r1 = rs.getInt(4);
                        int r2 = rs.getInt(5);
                        result = r1+r2;
                        Toast.makeText(submit.this, result, Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(submit.this,"varala", Toast.LENGTH_SHORT).show();

                    e.printStackTrace();
                }
            }
        });
    }
}
