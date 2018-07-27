package com.example.linus.hellojudge;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    Button button;
    EditText editText,editText2;
    String un,ps;
    private static final String db = "jdbc:mysql://192.182.183.13/tezfuerza";
    private static final String user = "sasurie";
    private static final String pass = "sasurieinfotech";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        un = editText.getText().toString();
        ps = editText2.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("admin1") && editText2.getText().toString().equals("admin1")){

                    Send obj = new Send();
                    obj.execute();
                    Intent intent = new Intent(getApplicationContext(),Qr_code.class);
                    //intent.putExtra("CODE","one");
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"logged in sucessfully admin1",Toast.LENGTH_LONG).show();
                    finish();

                }
                else if (editText.getText().toString().equals("admin2") && editText2.getText().toString().equals("admin2")){
                    Send obj = new Send();
                    obj.execute();
                    Intent intent = new Intent(getApplicationContext(),Qr_code_One.class);
                    //intent.putExtra("CODE","two");
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"logged in sucessfully admin2",Toast.LENGTH_LONG).show();
                    finish();
                }
                else if (editText.getText().toString().equals("submit") && editText2.getText().toString().equals("submit")){
                    Intent intent = new Intent(getApplicationContext(),submit.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"logged in sucessfully submition",Toast.LENGTH_LONG).show();
                    finish();

                }
               // Toast.makeText(Login.this, "invalid", Toast.LENGTH_SHORT).show();
                //Send obj = new Send();
                //obj.execute();

            }
        });
    }
    private class Send extends AsyncTask<String, String, String> {
        String msg;

        @Override
        protected String doInBackground(String... strings) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(db,user,pass);
                Statement stmt = conn.createStatement();
                String sql = "insert into final (id,stud_group_id) select id,stud_group_id from centerlised_table where multimedia = '*'";
                stmt.executeUpdate(sql);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }
}
