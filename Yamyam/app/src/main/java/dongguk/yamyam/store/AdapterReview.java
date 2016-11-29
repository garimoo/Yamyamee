package dongguk.yamyam.store;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import dongguk.yamyam.R;

/**
 * Created by SJ on 2016-11-14.
 */
public class AdapterReview extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataReview> data= Collections.emptyList();

    // create constructor to initialize context and data sent from NameSearchActivity
    public AdapterReview(Context context, List<DataReview> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_review, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataReview current=data.get(position);
        myHolder.textId.setText(current.reviewId);
        myHolder.textDate.setText(current.reviewDate);
        myHolder.textContent.setText(current.reviewContent);

        myHolder.rb.setRating(Float.parseFloat(current.reviewRate));
        myHolder.textScore.setText(Float.toString(Math.round(Float.parseFloat(current.reviewRate) * 100f) / 100f));
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textId;
        TextView textDate;
        TextView textContent;
        TextView textScore;
        RatingBar rb;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textId= (TextView) itemView.findViewById(R.id.textId);
            textDate = (TextView) itemView.findViewById(R.id.textDate);
            textContent = (TextView) itemView.findViewById(R.id.textContent);
            textScore = (TextView) itemView.findViewById(R.id.textScore);
            rb = (RatingBar) itemView.findViewById(R.id.reviewRating);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {

        }
    }
}

