package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getLocalClassName());
        Button button = (Button) findViewById(R.id.btn_login);

        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent i = new Intent(this, NewsActivity.class);
                EditText editText = findViewById(R.id.edittext_login_login);
                i.putExtra("login",editText.getText().toString());

                NewsListApplication app = (NewsListApplication) getApplicationContext();
                app.setLogin(editText.getText().toString());
                System.out.println(app.getLogin());
                startActivity(i);
                break;
        }

    }
}