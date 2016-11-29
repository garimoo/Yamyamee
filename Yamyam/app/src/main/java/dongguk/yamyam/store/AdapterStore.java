package dongguk.yamyam.store;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import dongguk.yamyam.R;
import dongguk.yamyam.activity.DetailActivity;
import dongguk.yamyam.app.AppConfig;
import dongguk.yamyam.app.AppController;
import dongguk.yamyam.helper.SQLiteHandler;

/**
 * Created by SJ on 2016-11-07.
 */
public class AdapterStore extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    private Context context;
    private LayoutInflater inflater;
    List<DataStore> data= Collections.emptyList();
    AppController appController;

    private SQLiteHandler db;

    String query;
    String id;
    String key;

    // create constructor to initialize context and data sent from NameSearchActivity
    public AdapterStore(Context context, List<DataStore> data, Context getAppContext){
        Log.d(data.toString(), "data");
        Log.d(context.toString(), "context");
        this.context=context;

        appController = (AppController)getAppContext;

        db = new SQLiteHandler(getAppContext);

        HashMap<String, String> user = db.getUserDetails();
        id = user.get("id");

        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_store, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataStore current=data.get(position);
        myHolder.textName.setText(current.storeName);
        myHolder.textAddress.setText(current.storeAddress);
        myHolder.textSubject.setText(current.storeSubject);
    }
    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textName;
        TextView textAddress;
        TextView textSubject;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textName= (TextView) itemView.findViewById(R.id.textName);
            textAddress = (TextView) itemView.findViewById(R.id.textAddress);
            textSubject = (TextView) itemView.findViewById(R.id.textSubject);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext() , DetailActivity.class);
            appController.setKey(data.get(getAdapterPosition()).storeKey);
            appController.setAddr(data.get(getAdapterPosition()).storeAddress);
            appController.setDate(data.get(getAdapterPosition()).storeDate);
            appController.setType(data.get(getAdapterPosition()).storeType);
            appController.setDistance(data.get(getAdapterPosition()).storeDistance);
            appController.setLatX(data.get(getAdapterPosition()).storeLatX);
            appController.setLongY(data.get(getAdapterPosition()).storeLongY);
            appController.setPhone(data.get(getAdapterPosition()).storePhone);
            appController.setScore(data.get(getAdapterPosition()).storeScore);
            appController.setSname(data.get(getAdapterPosition()).storeName);
            appController.setSubject(data.get(getAdapterPosition()).storeSubject);

            key = appController.getKey();
            Log.d(key, "adapterKey");

            new AsyncFavorite(id + "$" + key).execute();
            new AsyncScore(key).execute();
            Log.d(Float.toString(appController.getScore()), "isScoreinit?");
            context.startActivity(intent);
        }

        private class AsyncFavorite extends AsyncTask<String, String, String> {

            ProgressDialog pdLoading = new ProgressDialog(context);
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
                    url = new URL(AppConfig.URL_CHECK_FAVORITE);

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
                if (result.equals("yes rows")) {
                    appController.setFavorite(true);
                } else if (result.equals("no rows")) {
                    appController.setFavorite(false);
                }
            }
        }

        private class AsyncScore extends AsyncTask<String, String, String> {

            ProgressDialog pdLoading = new ProgressDialog(context);
            HttpURLConnection conn;
            URL url = null;
            String searchQuery;

            public AsyncScore(String searchQuery) {
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
                    url = new URL(AppConfig.URL_GET_SCORE);

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
                if(result.equals("no rows")) {
                    appController.setScore(0);
                } else{
                    try {
                        JSONArray jArray = new JSONArray(result);
                        JSONObject json_data = jArray.getJSONObject(0);
                        String stringScore = json_data.getString("AVG(score)");
                        Log.d(stringScore, "stringScore");
                        if(stringScore == "null") {
                            appController.setScore(0);
                        } else {
                            appController.setScore(Float.parseFloat(json_data.getString("AVG(score)")));
                        }
                    } catch (JSONException e) {
                        // You to understand what actually error is and handle it appropriately
                        Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                        Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
