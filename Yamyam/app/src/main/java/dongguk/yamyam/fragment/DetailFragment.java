package dongguk.yamyam.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dongguk.yamyam.R;
import dongguk.yamyam.app.AppController;

public class DetailFragment extends Fragment {
    AppController appController;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        appController = (AppController)getActivity().getApplicationContext();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView textPhone = (TextView) view.findViewById(R.id.textPhone);
        TextView textType = (TextView) view.findViewById(R.id.textType);
        TextView textDate = (TextView) view.findViewById(R.id.textDate);
        TextView textSubject = (TextView) view.findViewById(R.id.textSubject);

        Log.d(appController.getPhone(), "phone");
        Log.d(appController.getType(), "type");
        Log.d(appController.getDate(), "date");
        Log.d(appController.getSubject(), "subject");

        textPhone.setText(appController.getPhone());
        textType.setText(appController.getType());
        textDate.setText(appController.getPhone());
        textSubject.setText(appController.getPhone());
        return view;
    }
}