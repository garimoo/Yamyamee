package dongguk.yamyam.store;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dongguk.yamyam.R;
import dongguk.yamyam.activity.DetailActivity;

/**
 * Created by SJ on 2016-11-07.
 */
public class AdapterDistance extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataStore> data= new ArrayList<>();

    // create constructor to initialize context and data sent from NameSearchActivity
    public AdapterDistance(Context context, List<DataStore> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        Collections.sort(data, new DistanceComparator());
        this.data = data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_store_distance, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataStore current=data.get(position);
        myHolder.textName.setText(current.storeName);
        myHolder.textAddress.setText(current.storeAddress);
        myHolder.textSubject.setText(current.storeSubject);
        myHolder.textDistance.setText(Double.toString(current.storeDistance));
    }
    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textName;
        TextView textAddress;
        TextView textSubject;
        TextView textDistance;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textName= (TextView) itemView.findViewById(R.id.textName);
            textAddress = (TextView) itemView.findViewById(R.id.textAddress);
            textSubject = (TextView) itemView.findViewById(R.id.textSubject);
            textDistance = (TextView) itemView.findViewById(R.id.textDistance);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext() , DetailActivity.class);
            intent.putExtra("key", data.get(getAdapterPosition()).storeKey);
            context.startActivity(intent);
        }
    }
}
class DistanceComparator implements Comparator<DataStore> {
    public int compare(DataStore first, DataStore second){
        double firstValue = first.storeDistance;
        double secondValue = second.storeDistance;

        if(firstValue>secondValue) return -1;
        else if(firstValue<secondValue) return 1;
        else return 0;
    }
}

