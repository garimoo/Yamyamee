package dongguk.yamyam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import dongguk.yamyam.R;
import dongguk.yamyam.activity.SanitationActivity;
import dongguk.yamyam.activity.SanitationSearchActivity;
import dongguk.yamyam.activity.SelectSearchActivity;

public class SaniFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sanitation, container, false);

        ImageButton aaa_btn = (ImageButton) view.findViewById(R.id.aaabtn);
        aaa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), SanitationSearchActivity.class);
                    i.putExtra("sanitation", "1");
                    startActivity(i);
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ImageButton aa_btn = (ImageButton) view.findViewById(R.id.aabtn);
        aa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), SanitationSearchActivity.class);
                    i.putExtra("sanitation", "2");
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ImageButton a_btn = (ImageButton) view.findViewById(R.id.abtn);
        a_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getActivity(), SanitationSearchActivity.class);
                    i.putExtra("sanitation", "3");
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
