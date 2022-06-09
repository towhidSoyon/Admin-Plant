package com.example.admin_plant.complain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_plant.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnonymousAdapter extends RecyclerView.Adapter<AnonymousAdapter.ViewHolder> {

    private List<AnonymousComplainList> anonymousList;
    private AnonymousListener anonymousListener;


    public AnonymousAdapter(List<AnonymousComplainList> anonymousList, AnonymousListener anonymousListener){
        this.anonymousList = anonymousList;
        this.anonymousListener =anonymousListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complain_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setAnonymousComplainData(anonymousList.get(position));
    }

    @Override
    public int getItemCount() {
        return anonymousList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView listComplainImage;
        TextView listComplainLocation;
        TextView listComplainDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listComplainImage = itemView.findViewById(R.id.listComplainImage);
            listComplainLocation = itemView.findViewById(R.id.listComplainLocation);
            listComplainDesc = itemView.findViewById(R.id.listComplainDesc);
        }

        void setAnonymousComplainData(AnonymousComplainList anonymousComplainList){
            Picasso.get().load(anonymousComplainList.anonymousComplainImage).fit().centerInside().placeholder(R.drawable.placeholder_image).
                    into(listComplainImage);
            listComplainLocation.setText(anonymousComplainList.location);

            String ld = anonymousComplainList.description;
            if (ld.length() > 100){
                ld = ld.substring(0,100);
            }

            listComplainDesc.setText(ld+ "...");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    anonymousListener.onAnonymousClicked(anonymousComplainList);
                }
            });
        }
    }

    public interface AnonymousListener{
        void onAnonymousClicked(AnonymousComplainList anonymousComplainList);
    }
}
