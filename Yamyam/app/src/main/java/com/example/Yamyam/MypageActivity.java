package com.example.Yamyam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.View;

public class MypageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFA500));
    }
    public void pwchange_onclick(View v){
        Intent i=new Intent(MypageActivity.this, PwchangeActivity.class);
        startActivity(i);
    }
    public void logout_onclick(View v){
        Intent i=new Intent(MypageActivity.this, MainActivity.class);
        startActivity(i);
    }
}
