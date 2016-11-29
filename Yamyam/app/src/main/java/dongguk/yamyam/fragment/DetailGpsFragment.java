package dongguk.yamyam.fragment;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import dongguk.yamyam.R;
import dongguk.yamyam.app.AppController;
import dongguk.yamyam.helper.GPSProvider;

public class DetailGpsFragment extends Fragment
        implements OnMapReadyCallback{
    AppController appController;
    float actual_distance; //실제 거리 값을 담을 변수
    double myLongitude;
    double myLatitude;
    double x_dnts;
    double y_dnts;
    private GoogleMap googleMap;
    View view;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        appController = (AppController)getActivity().getApplicationContext();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_detail_gps, container, false);

        x_dnts = Double.parseDouble(appController.getLatX());
        y_dnts = Double.parseDouble(appController.getLongY());

        getDistance();

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }
    public void getDistance(){
        TextView tdistance;

        LocationManager mlocManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        GPSProvider gps = new GPSProvider(mlocManager); //오브젝트 생성

        myLongitude = gps.getLongitude();
        myLatitude = gps.getLatitude();
        float[] distance = new float[2]; // float 형태의 사이즈 2의 행렬 생성

        Location.distanceBetween(myLatitude, myLongitude, y_dnts, x_dnts, distance);

        actual_distance = distance[0]; //간단한 사용을 위해 일반 변수로 넘겨주기.

        tdistance=(TextView)view.findViewById(R.id.textDistance);

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