package dongguk.yamyam.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dongguk.yamyam.R;
import dongguk.yamyam.activity.DetailActivity;
import dongguk.yamyam.app.AppConfig;
import dongguk.yamyam.helper.GPSProvider;
import dongguk.yamyam.store.DataStore;

public class GpsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    LocationManager mlocManager;
    GPSProvider gps;
    LatLng myPosition;
    double myLongitude;
    double myLatitude;

    private GoogleMap googleMap;
    Marker selectedMarker;

    float[] distance = new float[2];
    ArrayList<DataStore> data = new ArrayList();

    ImageButton detailBtn;
    TextView textName;
    TextView textAddress;
    TextView textSubject;
    TextView textDistance;
    ImageView imgStore;
    View v;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        v = inflater.inflate(R.layout.fragment_gps, container, false);

        mlocManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        gps = new GPSProvider(mlocManager);
        myLongitude = gps.getLongitude();
        myLatitude = gps.getLatitude();

        new AsyncFetch(getActivity()).execute();

        return v;
    }

    // Create class AsyncFetch
    private class AsyncFetch extends AsyncTask<String, String, String> {
        Context context;
        ProgressDialog pdLoading;
        HttpURLConnection conn;
        URL url = null;

        public AsyncFetch(Context context){
            this.context = context;
            pdLoading = new ProgressDialog(context);
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
                url = new URL(AppConfig.URL_GPS_SEARCH);

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
                Toast.makeText(context, "검색 결과가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
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
                        storeData.storeLatX = json_data.getString("x_dnts");
                        storeData.storeLongY = json_data.getString("y_dnts");
                        Location.distanceBetween(myLongitude, myLatitude, Double.parseDouble(storeData.storeLatX),
                                Double.parseDouble(storeData.storeLongY), distance);
                        storeData.storeDistance = distance[0];
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

                    Collections.sort(data, new DistanceComparator());
                    createMap();

                } catch (JSONException e) {
                }
            }
        }
    }

    public void createMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        //지도타입 - 일반
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //기본위치(내위치)
        myPosition = new LatLng(myLatitude , myLongitude);

        MarkerOptions myMarker = new MarkerOptions().position(myPosition).title("내위치")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        // 반경 1KM원
        CircleOptions circle1KM = new CircleOptions().center(myPosition).radius(1000)
                .strokeColor(0xFF0000FF).strokeWidth((float)0.7).fillColor(0x110000FF);

        //마커추가
        this.googleMap.addMarker(myMarker);
        //화면중앙의 위치와 카메라 줌비율
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 14));
        this.googleMap.addCircle(circle1KM);
        this.googleMap.setOnMarkerClickListener(this);
        this.googleMap.setOnMapClickListener(this);

        int i = 0;
        while (Math.round((int)data.get(i).storeDistance) < 500) {
            addMarker(data.get(i), false);
            i++;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        final DataStore store = (DataStore)marker.getTag();

        CameraUpdate center = CameraUpdateFactory.newLatLng(marker.getPosition());
        googleMap.animateCamera(center);

        changeSelectedMarker(marker);

        detailBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra("key", store.storeKey);
                startActivity(i);
            }
        });

        return true;

    }

    private void changeSelectedMarker(Marker marker) {

        if(marker.getPosition().equals(myPosition)){
            MarkerOptions myMarker = new MarkerOptions().position(myPosition).title("내위치")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            this.googleMap.addMarker(myMarker);
        }
        else {
            // 선택했던 마커 되돌리기
            if (selectedMarker != null) {
                addMarker(selectedMarker, false);
                selectedMarker.remove();
            }

            // 선택한 마커 표시
            if (marker != null) {
                selectedMarker = addMarker(marker, true);
                marker.remove();
            }
        }
    }

    private Marker addMarker(Marker marker, boolean isSelectedMarker) {
        DataStore markerItem = (DataStore) marker.getTag();
        return addMarker(markerItem, isSelectedMarker);
    }

    //마커 , 원추가
    private Marker addMarker(DataStore markerItem, boolean isSelectedMarker){
        LatLng position = new LatLng(Double.parseDouble(markerItem.storeLongY) ,
                Double.parseDouble(markerItem.storeLatX));
        MarkerOptions storeMarker = new MarkerOptions().position(position);

        if (isSelectedMarker) {
            storeMarker = new MarkerOptions().position(position).title(markerItem.storeName)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            imgStore = (ImageView) v.findViewById(R.id.img_store);
            imgStore.setVisibility(View.VISIBLE);

            detailBtn = (ImageButton) v.findViewById(R.id.detailbtn);
            detailBtn.setVisibility(View.VISIBLE);

            textName = (TextView) v.findViewById(R.id.textName);
            textAddress = (TextView) v.findViewById(R.id.textAddress);
            textSubject = (TextView)v. findViewById(R.id.textSubject);
            textDistance = (TextView)v.findViewById(R.id.textDistance);

            textName.setText(markerItem.storeName);
            textAddress.setText(markerItem.storeAddress);
            textSubject.setText(markerItem.storeSubject);
            textDistance.setText(Integer.toString(Math.round((int)markerItem.storeDistance))+"m");
        }
        else {
            storeMarker = new MarkerOptions().position(position).title(markerItem.storeName)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));   //마커위치
        }

        //나의위치 마커
        Marker marker = googleMap.addMarker(storeMarker);
        marker.setTag(markerItem);

        //마커추가
        return marker;

    }

    @Override
    public void onMapClick(LatLng latLng) {
        changeSelectedMarker(null);
    }


    class DistanceComparator implements Comparator<DataStore> {
        public int compare(DataStore first, DataStore second){
            double firstValue = first.storeDistance;
            double secondValue = second.storeDistance;

            if(firstValue<secondValue) return -1;
            else if(firstValue>secondValue) return 1;
            else return 0;
        }
    }
}