package app.com.fyweddingplanner;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import app.com.fyweddingplanner.models.Offers;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    List<Offers> mItems;
    Context context;
    Activity activity;
    private OffersTab offersTab;

    public CardAdapter(Context context, List<Offers> items, OffersTab offersTab) {
        this.offersTab = offersTab;
        this.context  = context;
        this.mItems   = items;
        this.activity = (Activity)context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_recycler_view_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Offers event = mItems.get(i);
        viewHolder.tvTitle.setText(event.getName());
        viewHolder.tvDetails.setText(event.getDisciption());
        //imageLoader.displayImage(event.getPic(), viewHolder.imgThumbnail, options);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imgThumbnail;
        public TextView tvTitle;
        public TextView tvDetails;
        public Button btnMenu,btnEdit,btnActive;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvTitle      = (TextView)itemView.findViewById(R.id.tv_title);
            tvDetails    = (TextView)itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final int itemPosition = getAdapterPosition();
            Offers offer = mItems.get(itemPosition);

            switch (v.getId()){

            }
        }
        }
}


