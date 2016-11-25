package com.example.Yamyam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_nav_drawer,
                container, false);

        //분야별선택 버튼 클릭
        Button button1=(Button)view.findViewById(R.id.searchbtn);
        button1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
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
        return view;
    }
}
