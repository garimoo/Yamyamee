package dongguk.yamyam.activity;

/**
 * Created by SJ on 2016-11-14.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import dongguk.yamyam.R;
import dongguk.yamyam.app.AppConfig;
import dongguk.yamyam.app.AppController;
import dongguk.yamyam.store.DetailTabAdapter;
import dongguk.yamyam.helper.SQLiteHandler;
import dongguk.yamyam.store.DataStore;


public class DetailActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private SQLiteHandler db;
    String key;
    String id;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    RatingBar rb;
    public DataStore storeData;

    AppController appController;

    ImageButton favorite;
    TextView textName;
    TextView textSubject;
    TextView textAddress;
    TextView textPhone;
    TextView textScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new SQLiteHandler(getApplicationContext());

        HashMap<String, String> user = db.getUserDetails();

        appController = (AppController)getApplicationContext();

        rb = (RatingBar) findViewById(R.id.detailRating);
        favorite = (ImageButton) findViewById(R.id.likeButton);

        if(appController.getFavorite() == true) {
            favorite.setBackgroundResource(R.drawable.star_yes);
        }
        else favorite.setBackgroundResource(R.drawable.star_no);

        textName = (TextView) findViewById(R.id.textName);
        textSubject = (TextView) findViewById(R.id.textSubject);
        textAddress = (TextView) findViewById(R.id.textAddr);
        textScore = (TextView) findViewById(R.id.textScore);

        Log.d(appController.getSname(), "detailName");
        Log.d(appController.getSubject(), "detailSubject");
        Log.d(appController.getAddr(), "detailAddr");

        textName.setText(appController.getSname());
        textSubject.setText(appController.getSubject());
        textAddress.setText(appController.getAddr());

        float score = appController.getScore();

        rb.setRating(score);

        textScore.setText(Float.toString(Math.round(score * 100f) / 100f));

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // continueApp();
                new AsyncFavorite(id + "$" + key).execute();
            }
        });

        //탭 구현
        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_detail);
        tabLayout.addTab(tabLayout.newTab().setText("가게정보"));
        tabLayout.addTab(tabLayout.newTab().setText("리뷰"));
        tabLayout.addTab(tabLayout.newTab().setText("지도"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager_detail);

        // Creating TabPagerAdapter adapter
        final DetailTabAdapter pagerAdapter = new DetailTabAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), getApplicationContext());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    private class AsyncFavorite extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(DetailActivity.this);
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;

        public AsyncFavorite(String searchQuery) {
            this.searchQuery = searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_FAVORITE);

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput to true as we send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {
                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return ("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //this method will be running on UI thread
            pdLoading.dismiss();
            if (result.equals("already exists")) {
                Toast.makeText(DetailActivity.this, "즐겨찾기에서 제거되었습니다.", Toast.LENGTH_LONG).show();
                favorite.setBackgroundResource(R.drawable.star2);
            } else if (result.equals("add successfully")) {
                Toast.makeText(DetailActivity.this, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_LONG).show();
                favorite.setBackgroundResource(R.drawable.star1);
            }
        }
    }

    public void write_review_onclick(View v){
        Intent i=new Intent(this, WriteReviewActivity.class);
        i.putExtra("key", key); startActivity(i);
    }

    public void share_kakao_onclick(View v){
        //Intent i=new Intent(this, DetailGpsActivity.class);
        //i.putExtra("xy_dnts", storeData.storeLatX +"," + storeData.storeLongY);
        //startActivity(i);
    }
}
