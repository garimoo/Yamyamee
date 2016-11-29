package dongguk.yamyam.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.HashMap;
import java.util.List;

import dongguk.yamyam.R;
import dongguk.yamyam.app.AppConfig;
import dongguk.yamyam.helper.SQLiteHandler;
import dongguk.yamyam.store.AdapterStore;
import dongguk.yamyam.store.DataStore;

public class LikeFragment extends Fragment {
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private SQLiteHandler db;
    private RecyclerView mRVStore;
    private AdapterStore mAdapter=null;

    String id;

    View view;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.activity_recycler_store,container, false);

        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());

        // session manager
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        id = user.get("id");

        new AsyncFetch(id, getActivity()).execute();
        return view;
    }

    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(getActivity());
        HttpURLConnection conn;
        URL url = null;
        String searchQuery;
        private Context context;

        public AsyncFetch(String searchQuery, Context context){
            this.searchQuery=searchQuery;
            this.context = context;
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
                url = new URL(AppConfig.URL_LOAD_FAVORITE);

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
                Toast.makeText(getActivity(), "검색 결과가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
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
                        storeData.storeType = json_data.getString("crf_type");
                        storeData.storeDate = json_data.getString("crf_date");
                        storeData.storePhone = json_data.getString("phone");
                        storeData.storeLatX = json_data.getString("x_dnts");
                        storeData.storeLongY = json_data.getString("y_dnts");

                        int genre = json_data.getInt("genre");
                        storeData.storeSubject = setSubject(genre);

                        data.add(storeData);
                    }

                    // Setup and Handover data to recyclerview
                    mRVStore = (RecyclerView) view.findViewById(R.id.storeList);
                    mAdapter = new AdapterStore(context, data, getActivity().getApplicationContext());
                    mRVStore.setAdapter(mAdapter);
                    mRVStore.setLayoutManager(new LinearLayoutManager(context));

                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    public String setSubject(int genre) {
        if(genre == 10101) return "한식";
        else if(genre == 10102) return "중식";
        else if(genre == 10103) return "양식";
        else if(genre == 10104) return "일식";
        else if(genre == 10105) return "분식";
        else if(genre == 10106) return "뷔페식";
        else if(genre == 10107) return "선술집";
        else if(genre == 10108) return "전통찻집";
        else if(genre == 10110) return "출장조리";
        else if(genre == 10111) return "패스트푸드 ";
        else if(genre == 10112) return "호프";
        else if(genre == 10113) return "치킨집";
        else if(genre == 10114) return "복어취급";
        else if(genre == 10115) return "도시락";
        else if(genre == 10116) return "생선회";
        else if(genre == 10117) return "카페";
        else if(genre == 10118) return "식육취급";
        else if(genre == 10119) return "탕류";
        else if(genre == 10199) return "기타(일반음식점)";
        else if(genre == 10301) return "단란주점";
        else if(genre == 10401) return "과자점";
        else if(genre == 10501) return "집단급식소";
        else if(genre == 10601) return "식품제조가공업";
        else if(genre == 10701) return "즉석판매제조가공업";
        else if(genre == 11401) return "기타식품판매업";
        else if(genre == 12101) return "제과점영업";
        else if(genre == 90001) return "안심식육판매점";
        else if(genre == -1) return "NONE";
        else return null;
    }
}