package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getLocalClassName());

        Button button = (Button) findViewById(R.id.btn_back_detail);

        button.setOnClickListener(this);

        NewsListApplication app = (NewsListApplication) getApplicationContext();

        TextView textView = findViewById(R.id.textview111);
        textView.setText(app.getLogin());


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_detail:
                Intent i = new Intent(this, NewsActivity.class);
                startActivity(i);
                break;
        }

    }
}