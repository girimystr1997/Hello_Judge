package com.example.girii.hellojudge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button button;
    EditText editText,editText2;
    String un,ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.girii.hellojudge.R.layout.login);
        button = (Button)findViewById(com.example.girii.hellojudge.R.id.button);
        editText = (EditText)findViewById(com.example.girii.hellojudge.R.id.editText);
        editText2 = (EditText)findViewById(com.example.girii.hellojudge.R.id.editText2);
        un = editText.getText().toString();
        ps = editText2.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText.getText().toString().equals("admin1") && editText2.getText().toString().equals("admin1")){

                    Intent intent = new Intent(getApplicationContext(),samp.class);
                    //intent.putExtra("CODE","one");
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"logged in sucessfully admin1",Toast.LENGTH_LONG).show();
                    finish();

                }
                else if (editText.getText().toString().equals("admin2") && editText2.getText().toString().equals("admin2")) {

                    Intent intent = new Intent(getApplicationContext(), Qr_code_One.class);
                    //intent.putExtra("CODE","two");
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "logged in sucessfully admin2", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }
}
