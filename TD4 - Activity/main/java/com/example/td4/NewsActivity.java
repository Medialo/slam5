package com.example.td4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle(getLocalClassName());
        Button button = findViewById(R.id.btn_detail);
        button.setOnClickListener(this);
        Button button2 = findViewById(R.id.btn_back_news);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.btn_logout_news);
        button3.setOnClickListener(this);
        Button button4 = findViewById(R.id.btn_about_news);
        button4.setOnClickListener(this);
        Intent intent = getIntent();
        EditText editText = findViewById(R.id.editTextTextPersonName);
            editText.setText(intent.getStringExtra("login"));
    }



    private static final String TAG ="NewsList";
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG,"terminaison de l 'activité "+getLocalClassName());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_detail:
                System.out.println("okokokk");
                Intent i = new Intent(this,DetailActivity.class);
                startActivity(i);
                break;
            case R.id.btn_logout_news:
                    finish();
                break;
            case R.id.btn_back_news:
                this.finishAffinity();
                break;
            case R.id.btn_about_news:
                String url ="https://perso.univ-rennes1.fr/pierre.nerzic/Android";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
        }

    }
}