package dongguk.yamyam.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import dongguk.yamyam.R;
import dongguk.yamyam.helper.GPSProvider;

/**
 * Created by SJ on 2016-11-19.
 */
public class DetailGpsActivity extends AppCompatActivity {

    float actual_distance; //실제 거리 값을 담을 변수
    double myLongitude;
    double myLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        String xy_dnt = getIntent().getStringExtra("xy_dnts");
        String[] xy_dnts = xy_dnt.split(",");
        Log.d(xy_dnt, "xy_dnts");


        Double x_dnts = Double.parseDouble(xy_dnts[0]);
        Double y_dnts = Double.parseDouble(xy_dnts[1]);

        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // 시스템에서 제공하는 위치 정보 서비스를 받아와서 그를 LocationManager로 캐스팅

        GPSProvider gps = new GPSProvider(mlocManager); //오브젝트 생성

        myLongitude = gps.getLongitude();
        Log.d(Double.toString(myLongitude), "longitude");

        myLatitude = gps.getLatitude();
        Log.d(Double.toString(myLatitude), "latitude");

        float[] distance = new float[2]; // float 형태의 사이즈 2의 행렬 생성

        Location.distanceBetween(myLongitude, myLatitude, x_dnts, y_dnts, distance);

        actual_distance = distance[0]; //간단한 사용을 위해 일반 변수로 넘겨주기.
        Log.d(Double.toString(actual_distance), "distance");

        printResult();
    }

    public void printResult() {
        TextView latitude;
        TextView longitude;
        TextView distance;

        latitude = (TextView) findViewById(R.id.textLatitude);
        longitude=(TextView)findViewById(R.id.textLongitude);
        distance=(TextView)findViewById(R.id.textDistance);

        latitude.setText(Double.toString(myLatitude));
        longitude.setText(Double.toString(myLongitude));
        distance.setText(Float.toString(actual_distance)+"m");

    }

    public float returnDistance(){
        return actual_distance;
    }
}
