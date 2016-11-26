package dongguk.yamyam.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dongguk.yamyam.R;

public class GpsFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_main,container, false);
    }
}

