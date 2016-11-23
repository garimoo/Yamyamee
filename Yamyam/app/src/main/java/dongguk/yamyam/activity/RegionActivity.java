package dongguk.yamyam.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import dongguk.yamyam.R;

/**
 * Created by SJ on 2016-11-08.
 */

public class RegionActivity extends AppCompatActivity{
    String subject;
    String dataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        subject = getIntent().getStringExtra("subject");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFA500));
    }
    public void all_onclick(View v){
        dataset = subject + " " + "0";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void gangnam_onclick(View v){
        dataset = subject + " " + "3220000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void gangdong_onclick(View v){
        dataset = subject + " " + "3240000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void gangbuk_onclick(View v){
        dataset = subject + " " + "3080000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void gangseo_onclick(View v){
        dataset = subject + " " + "3150000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void gwanak_onclick(View v){
        dataset = subject + " " + "3200000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void gwangjin_onclick(View v){
        dataset = subject + " " + "3040000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void guro_onclick(View v){
        dataset = subject + " " + "3160000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void geumcheon_onclick(View v){
        dataset = subject + " " + "3170000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void nowon_onclick(View v){
        dataset = subject + " " + "3100000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void dobong_onclick(View v){
        dataset = subject + " " + "3090000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void dongdaemun_onclick(View v){
        dataset = subject + " " + "3050000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void dongjak_onclick(View v){
        dataset = subject + " " + "3190000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void mapo_onclick(View v){
        dataset = subject + " " + "3130000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void seodaemun_onclick(View v){
        dataset = subject + " " + "3120000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void seocho_onclick(View v){
        dataset = subject + " " + "3210000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void seongdong_onclick(View v){
        dataset = subject + " " + "3030000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void songpa_onclick(View v){
        dataset = subject + " " + "3230000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void yangcheon_onclick(View v){
        dataset = subject + " " + "3140000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void  yeongdeungpo_onclick(View v){
        dataset = subject + " " + "3180000"; // 영등포구
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void yongsan_onclick(View v){
        dataset = subject + " " + "3020000"; // 용산구
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void eunpyeong_onclick(View v){
        dataset = subject + " " + "3110000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void jongno_onclick(View v){
        dataset = subject + " " + "3000000";
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void junggu_onclick(View v){
        dataset = subject + " " + "3010000"; // 중구
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
    public void jungrang_onclick(View v){
        dataset = subject + " " + "3060000"; // 중랑구
        Intent i=new Intent(this, SelectSearchActivity.class);
        i.putExtra("dataset",dataset);
        startActivity(i);
    }
}