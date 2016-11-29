package dongguk.yamyam.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import dongguk.yamyam.R;

public class SelectFragment extends Fragment {
    private static final String ARG_PARAM = "subject";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_select,container, false);

        Button button_1=(Button)view.findViewById(R.id.select_0);
        button_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("0"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_2=(Button)view.findViewById(R.id.select_1);
        button_2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("14"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_3=(Button)view.findViewById(R.id.select_2);
        button_3.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("4"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_4=(Button)view.findViewById(R.id.select_3);
        button_4.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("5"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_6=(Button)view.findViewById(R.id.select_5);
        button_6.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("7"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_7=(Button)view.findViewById(R.id.select_6);
        button_7.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("6"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_8=(Button)view.findViewById(R.id.select_7);
        button_8.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("8"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_9=(Button)view.findViewById(R.id.select_8);
        button_9.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("9"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_10=(Button)view.findViewById(R.id.select_9);
        button_10.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("11"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_11=(Button)view.findViewById(R.id.select_10);
        button_11.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.select, RegionFragment.newInstance("10"));
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}