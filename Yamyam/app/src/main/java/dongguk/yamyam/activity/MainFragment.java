package dongguk.yamyam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import dongguk.yamyam.R;


public class MainFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //검색창 버튼
        ImageButton btn_search=(ImageButton)view.findViewById(R.id.magnifierbtn);
        btn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    Intent i=new Intent(getActivity(),NameSearchActivity.class);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        //분야별 선택 버튼
        ImageButton btn_select=(ImageButton)view.findViewById(R.id.searchbtn);
        btn_select.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.main, new SelectFragment());
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        //gps 버튼
        ImageButton btn_gps=(ImageButton)view.findViewById(R.id.gpsbtn);
        btn_gps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.main, new GpsFragment());
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);
                    trans.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        //위생업체 버튼
        ImageButton btn_sani=(ImageButton)view.findViewById(R.id.sanibtn);
        btn_sani.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    FragmentTransaction trans = getFragmentManager().beginTransaction();
                    trans.replace(R.id.main, new SaniFragment());
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
