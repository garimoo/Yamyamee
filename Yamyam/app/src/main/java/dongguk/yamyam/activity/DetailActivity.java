package dongguk.yamyam.activity;

/**
 * Created by SJ on 2016-11-14.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import dongguk.yamyam.helper.SQLiteHandler;
import dongguk.yamyam.store.DataStore;


public class DetailActivity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private SQLiteHandler db;
    String key;
    String id;

    RatingBar rb;
    DataStore storeData;
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

        rb = (RatingBar) findViewById(R.id.detailRating);
        id = user.get("id");
        key = getIntent().getStringExtra("key");
        favorite = (ImageButton) findViewById(R.id.likeButton);

        new AsyncFetch(id + "$" + key).execute();
    }

    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(DetailActivity.this);
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;

        public AsyncFetch(String searchQuery){
            this.searchQuery=searchQuery;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL(AppConfig.URL_DETAIL_SEARCH);

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
                    return("Connection error");
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

            pdLoading.dismiss();
            if(result.equals("no rows")) {
                Toast.makeText(DetailActivity.this, "검색 결과가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
            }else{

                try {
                    JSONArray jArray = new JSONArray(result);

                    JSONObject json_score = jArray.getJSONObject(0);
                    JSONObject json_data = jArray.getJSONObject(1);

                    storeData = new DataStore();

                    storeData.storeKey = json_data.getString("serial");
                    storeData.storeName = json_data.getString("name");
                    storeData.storeSubject = json_data.getString("genre");
                    storeData.storeAddress = json_data.getString("addr");
                    storeData.storePhone = json_data.getString("phone");
                    storeData.storeLatX = json_data.getString("x_dnts");
                    storeData.storeLongY = json_data.getString("y_dnts");

                    if(json_data.has("1") == true) {
                        favorite.setBackgroundResource(R.drawable.star1);
                    }
                    else favorite.setBackgroundResource(R.drawable.star2);

                    textName = (TextView) findViewById(R.id.textName);
                    textSubject = (TextView) findViewById(R.id.textSubject);
                    textAddress = (TextView) findViewById(R.id.textAddress);
                    textPhone = (TextView) findViewById(R.id.textPhone);
                    textScore = (TextView) findViewById(R.id.textScore);

                    textName.setText(storeData.storeName);
                    textSubject.setText(storeData.storeSubject);
                    textAddress.setText(storeData.storeAddress);
                    textPhone.setText(storeData.storePhone);

                    String stringScore = json_score.getString("AVG(score)");

                    if(stringScore != "null") storeData.storeScore = Float.valueOf(stringScore);
                    else storeData.storeScore = (float)0;

                    rb.setRating(storeData.storeScore);
                    textScore.setText(Float.toString(Math.round(storeData.storeScore * 100f) / 100f));

                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(DetailActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(DetailActivity.this, result.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void like_onclick(View v){
        Intent i=new Intent(this, LikeActivity.class);
        i.putExtra("key", key); startActivity(i);
    }

    public void review_onclick(View v){
        Intent i=new Intent(this, ReviewListActivity.class);
        i.putExtra("key", key); startActivity(i);
    }

    public void review_write_onclick(View v){
        Intent i=new Intent(this, WriteReviewActivity.class);
        i.putExtra("key", key); startActivity(i);
    }

    public void gps_onclick(View v){
        Intent i=new Intent(this, DetailGpsActivity.class);
        i.putExtra("xy_dnts", storeData.storeLatX +"," + storeData.storeLongY);
        startActivity(i);
    }


}

