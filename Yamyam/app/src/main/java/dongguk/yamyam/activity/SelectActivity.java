package dongguk.yamyam.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;

import dongguk.yamyam.R;

/**
 * Created by SJ on 2016-11-08.
 */
public class SelectActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFA500));
    }

    public void select_0_onclick(View v){ // 전체
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","0"); startActivity(i); finish();
    }

    public void select_1_onclick(View v){  //채식
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","14"); startActivity(i); finish();
    }

    public void select_3_onclick(View v){ // 저염실천음식점
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","4"); startActivity(i); finish();
    }

    public void select_4_onclick(View v){ // 건강음식점
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","5"); startActivity(i); finish();
    }

    public void select_5_onclick(View v){ // 안심식육판매점
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","7"); startActivity(i); finish();
    }

    public void select_6_onclick(View v){ // 먹을만큼적당히
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","6"); startActivity(i); finish();
    }

    public void select_7_onclick(View v){ // 안심참기름
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","8"); startActivity(i); finish();
    }

    public void select_8_onclick(View v){ // 트랜스지방 안심제과점
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","9"); startActivity(i); finish();
    }

    public void select_9_onclick(View v){ // 안심떡집
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","11"); startActivity(i); finish();
    }

    public void select_11_onclick(View v){ // 안심마트
        Intent i=new Intent(SelectActivity.this, RegionActivity.class);
        i.putExtra("subject","10"); startActivity(i); finish();
    }
}