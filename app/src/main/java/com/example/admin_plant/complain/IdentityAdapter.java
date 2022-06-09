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

public class IdentityAdapter extends RecyclerView.Adapter<IdentityAdapter.ViewHolder> {

    private List<IdentityComplainList> identityComplainList;
    private IdentityListener identityListener;


    public IdentityAdapter(List<IdentityComplainList> identityComplainList, IdentityListener identityListener){
        this.identityComplainList = identityComplainList;
        this.identityListener =identityListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complain_item, null);
        return new IdentityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setIdentityComplainData(identityComplainList.get(position));
    }

    @Override
    public int getItemCount() {
        return identityComplainList.size();
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

        void setIdentityComplainData(IdentityComplainList identityComplainList){
            Picasso.get().load(identityComplainList.identityComplainImage).fit().centerInside().placeholder(R.drawable.placeholder_image).
                    into(listComplainImage);
            listComplainLocation.setText(identityComplainList.location);

            String ld = identityComplainList.description;
            if (ld.length() > 100){
                ld = ld.substring(0,100);
            }

            listComplainDesc.setText(ld+ "...");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    identityListener.onIdentityClicked(identityComplainList);
                }
            });
        }
    }

    public interface IdentityListener{
        void onIdentityClicked(IdentityComplainList identityComplainList);
    }
}
