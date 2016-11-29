package dongguk.yamyam.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;
import java.util.List;

import dongguk.yamyam.R;
import dongguk.yamyam.app.AppConfig;
import dongguk.yamyam.store.AdapterStore;
import dongguk.yamyam.store.DataStore;

/**
 * Created by SJ on 2016-11-10.
 */
public class SelectSearchActivity extends AppCompatActivity {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView mRVStore;
    private AdapterStore mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFA500")));
        setContentView(R.layout.activity_recycler_store);

        String query = getIntent().getStringExtra("dataset");

        new AsyncFetch(query).execute();
    }

    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(SelectSearchActivity.this);
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
                url = new URL(AppConfig.URL_SEL_SEARCH);

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
            List<DataStore> data=new ArrayList<>();

            pdLoading.dismiss();
            if(result.equals("no rows")) {
                Toast.makeText(SelectSearchActivity.this, "검색 결과가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
            }else{

                try {

                    JSONArray jArray = new JSONArray(result);

                    // Extract data from json and store into ArrayList as class objects
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        DataStore storeData = new DataStore();
                        storeData.storeKey = json_data.getString("serial");
                        storeData.storeName = json_data.getString("name");
                        storeData.storeAddress = json_data.getString("addr");
                        storeData.storePhone = json_data.getString("phone");
                        storeData.storeLatX = json_data.getString("x_dnts");
                        storeData.storeLongY = json_data.getString("y_dnts");
                        /********************************************************************/
                        int genre = json_data.getInt("genre");
                        if(genre == 10101) storeData.storeSubject = "한식";
                        else if(genre == 10102) storeData.storeSubject = "중식";
                        else if(genre == 10103) storeData.storeSubject = "양식";
                        else if(genre == 10104) storeData.storeSubject = "일식";
                        else if(genre == 10105) storeData.storeSubject = "분식";
                        else if(genre == 10106) storeData.storeSubject = "뷔페식";
                        else if(genre == 10107) storeData.storeSubject = "선술집";
                        else if(genre == 10108) storeData.storeSubject = "전통찻집";
                        else if(genre == 10110) storeData.storeSubject = "출장조리";
                        else if(genre == 10111) storeData.storeSubject = "패스트푸드 ";
                        else if(genre == 10112) storeData.storeSubject = "호프";
                        else if(genre == 10113) storeData.storeSubject = "치킨집";
                        else if(genre == 10114) storeData.storeSubject = "복어취급";
                        else if(genre == 10115) storeData.storeSubject = "도시락";
                        else if(genre == 10116) storeData.storeSubject = "생선회";
                        else if(genre == 10117) storeData.storeSubject = "카페";
                        else if(genre == 10118) storeData.storeSubject = "식육취급";
                        else if(genre == 10119) storeData.storeSubject = "탕류";
                        else if(genre == 10199) storeData.storeSubject = "기타(일반음식점)";
                        else if(genre == 10301) storeData.storeSubject = "단란주점";
                        else if(genre == 10401) storeData.storeSubject = "과자점";
                        else if(genre == 10501) storeData.storeSubject = "집단급식소";
                        else if(genre == 10601) storeData.storeSubject = "식품제조가공업";
                        else if(genre == 10701) storeData.storeSubject = "즉석판매제조가공업";
                        else if(genre == 11401) storeData.storeSubject = "기타식품판매업";
                        else if(genre == 12101) storeData.storeSubject = "제과점영업";
                        else if(genre == 90001) storeData.storeSubject = "안심식육판매점";
                        else if(genre == -1) storeData.storeSubject = "";
                        /********************************************************************/
                        data.add(storeData);
                    }

                    // Setup and Handover data to recyclerview
                    mRVStore = (RecyclerView) findViewById(R.id.storeList);
                    mAdapter = new AdapterStore(SelectSearchActivity.this, data, getApplicationContext());
                    mRVStore.setAdapter(mAdapter);
                    mRVStore.setLayoutManager(new LinearLayoutManager(SelectSearchActivity.this));

                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(SelectSearchActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(SelectSearchActivity.this, result.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
