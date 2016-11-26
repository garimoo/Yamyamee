package dongguk.yamyam.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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

/**
 * Created by SJ on 2016-11-14.
 */
public class LikeActivity extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private SQLiteHandler db;

    String key;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        id = user.get("id");
        key = getIntent().getStringExtra("key");

        String query = id + "$" + key;
        Log.d(query, "query");
        new AsyncFetch(query).execute();
    }

    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(LikeActivity.this);
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
            if(result.equals("already exists")) {
                Toast.makeText(LikeActivity.this, "즐겨찾기에서 제거되었습니다.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(LikeActivity.this, DetailActivity.class);
                i.putExtra("key", key);
                startActivity(i);
            }

            else if(result.equals("add successfully")) {
                Toast.makeText(LikeActivity.this, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(LikeActivity.this, DetailActivity.class);
                i.putExtra("key", key);
                startActivity(i);
            }
        }
    }
}
