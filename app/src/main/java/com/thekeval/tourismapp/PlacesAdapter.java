package com.thekeval.tourismapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private Context ctx;
    private ArrayList<PlaceModel> lstPlaces;
    private LayoutInflater layoutInflater;

    int selected_position = 0; // You have to set this globally in the Adapter class


    public PlacesAdapter(Context context, ArrayList<PlaceModel> places) {
        ctx = context;
        this.lstPlaces = places;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaceViewHolder placeVH;
        View placeView = layoutInflater.inflate(R.layout.item_place, parent, false);
        placeVH = new PlaceViewHolder(placeView);

        return placeVH;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        PlaceModel place = lstPlaces.get(position);

        ImageView iv_place = holder.placeImage;
        int imgId = ctx.getResources().getIdentifier(place.image, "drawable", ctx.getPackageName());
        iv_place.setImageDrawable(ContextCompat.getDrawable(ctx, imgId));

        holder.placeName.setText(place.name);
        holder.placePrice.setText(String.valueOf(place.price));

        // highlighting the background
        holder.itemView.setBackgroundColor(selected_position == position ? ctx.getResources().getColor(R.color.material_blue_light) : Color.TRANSPARENT);

    }

    @Override
    public int getItemCount() {
        return lstPlaces.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView placeImage;
        public TextView placeName, placePrice;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.img_place);
            placeName = itemView.findViewById(R.id.tv_place_name);
            placePrice = itemView.findViewById(R.id.tv_price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            MainActivity.selectedPlace = lstPlaces.get(selected_position);
        }
    }
}
