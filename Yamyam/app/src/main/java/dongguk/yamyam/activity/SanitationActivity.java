package dongguk.yamyam.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dongguk.yamyam.R;
/**
 * Created by SJ on 2016-11-10.
 */
public class SanitationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanitation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFA500));
    }

    public void AAA_onclick(View v){ // AAA
        Intent i=new Intent(SanitationActivity.this, SanitationSearchActivity.class);
        i.putExtra("sanitation","1"); startActivity(i); finish();
    }

    public void AA_onclick(View v){  //AA
        Intent i=new Intent(SanitationActivity.this, SanitationSearchActivity.class);
        i.putExtra("sanitation","2"); startActivity(i); finish();
    }

    public void A_onclick(View v){ // A
        Intent i=new Intent(SanitationActivity.this, SanitationSearchActivity.class);
        i.putExtra("sanitation","3"); startActivity(i); finish();
    }
}