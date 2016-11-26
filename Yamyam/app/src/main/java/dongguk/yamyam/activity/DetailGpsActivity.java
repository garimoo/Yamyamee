package dongguk.yamyam.activity;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import dongguk.yamyam.R;
import dongguk.yamyam.helper.GPSProvider;

/**
 * Created by SJ on 2016-11-19.
 */
public class DetailGpsActivity extends AppCompatActivity implements OnMapReadyCallback {
    float actual_distance; //실제 거리 값을 담을 변수
    double myLongitude;
    double myLatitude;
    double x_dnts;
    double y_dnts;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gps);

        String xy_dnt = getIntent().getStringExtra("xy_dnts");
        String[] xy_dnts = xy_dnt.split(",");

        x_dnts = Double.parseDouble(xy_dnts[0]);
        y_dnts = Double.parseDouble(xy_dnts[1]);

        getDistance();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void getDistance(){
        TextView tdistance;

        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        GPSProvider gps = new GPSProvider(mlocManager); //오브젝트 생성

        myLongitude = gps.getLongitude();
        myLatitude = gps.getLatitude();
        float[] distance = new float[2]; // float 형태의 사이즈 2의 행렬 생성

        Location.distanceBetween(myLatitude, myLongitude, y_dnts, x_dnts, distance);

        actual_distance = distance[0]; //간단한 사용을 위해 일반 변수로 넘겨주기.

        tdistance=(TextView)findViewById(R.id.textDistance);

        if(actual_distance>=1000) {
            tdistance.setText(Float.toString(Math.round(actual_distance / 1000 * 100f) / 100f)+"km");
        }
        else {
            tdistance.setText(Integer.toString(Math.round((int)actual_distance))+"m");
        }
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng START = new LatLng(myLatitude, myLongitude);
        LatLng END = new LatLng(y_dnts, x_dnts);
        googleMap = map;

        Marker start = googleMap.addMarker(new MarkerOptions().position(START)
                .title("start").icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        Marker end = googleMap.addMarker(new MarkerOptions().position(END)
                .title("end").icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(START));
        googleMap.addPolyline(new PolylineOptions().add(START, END).width(10).color(Color.RED));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }

}
