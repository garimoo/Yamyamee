package dongguk.yamyam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import dongguk.yamyam.R;
import dongguk.yamyam.activity.SelectSearchActivity;

public class RegionFragment extends Fragment {
    String subject;
    String dataset;
    private static final String ARG_PARAM = "subject";

    public RegionFragment() {}

    public static RegionFragment newInstance(String param) {
        RegionFragment fragment = new RegionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        subject = getArguments().getString(ARG_PARAM);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_region, container, false);

        Button all_btn = (Button)view.findViewById(R.id.button_0);
        all_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "0";
                    Intent i = new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset", dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button gangnam=(Button)view.findViewById(R.id.button1);
        gangnam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3220000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button gangdong=(Button)view.findViewById(R.id.button2);
        gangdong.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3240000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button gangbuk=(Button)view.findViewById(R.id.button3);
        gangbuk.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3080000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_5=(Button)view.findViewById(R.id.button4);
        button_5.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3150000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_6=(Button)view.findViewById(R.id.button5);
        button_6.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3200000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_7=(Button)view.findViewById(R.id.button6);
        button_7.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3040000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_8=(Button)view.findViewById(R.id.button7);
        button_8.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3160000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }); Button button_9=(Button)view.findViewById(R.id.button8);
        button_9.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3170000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_10=(Button)view.findViewById(R.id.button9);
        button_10.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3100000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_11=(Button)view.findViewById(R.id.button10);
        button_11.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3090000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_12=(Button)view.findViewById(R.id.button11);
        button_12.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3050000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_13=(Button)view.findViewById(R.id.button12);
        button_13.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3190000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_14=(Button)view.findViewById(R.id.button13);
        button_14.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3130000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_15=(Button)view.findViewById(R.id.button14);
        button_15.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3120000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_16=(Button)view.findViewById(R.id.button15);
        button_16.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3210000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_17=(Button)view.findViewById(R.id.button16);
        button_17.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3070000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_18=(Button)view.findViewById(R.id.button17);
        button_18.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3230000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_19=(Button)view.findViewById(R.id.button18);
        button_19.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3140000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_20=(Button)view.findViewById(R.id.button19);
        button_20.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3180000"; // 영등포구
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_21=(Button)view.findViewById(R.id.button20);
        button_21.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3020000"; // 용산구
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_22=(Button)view.findViewById(R.id.button21);
        button_22.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3110000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_23=(Button)view.findViewById(R.id.button22);
        button_23.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3000000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_24=(Button)view.findViewById(R.id.button23);
        button_24.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3010000"; // 중구
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_25=(Button)view.findViewById(R.id.button24);
        button_25.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3060000"; // 중랑구
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Button button_26=(Button)view.findViewById(R.id.button25);
        button_26.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    dataset = subject + " " + "3030000";
                    Intent i=new Intent(getActivity(), SelectSearchActivity.class);
                    i.putExtra("dataset",dataset);
                    startActivity(i);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}