package dongguk.yamyam.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.View;

import dongguk.yamyam.R;

public class PwchangeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));
        setContentView(R.layout.activity_pwchange);

    }
    public void pwchange_clear(View v){
        Intent i=new Intent(PwchangeActivity.this, MainActivity.class);
        startActivity(i);
    }
}
