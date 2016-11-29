package dongguk.yamyam.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dongguk.yamyam.R;

public class MypageActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));
        setContentView(R.layout.activity_mypage);
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
