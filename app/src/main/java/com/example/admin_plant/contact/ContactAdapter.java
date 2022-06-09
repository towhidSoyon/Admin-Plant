package com.example.admin_plant.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_plant.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<ContactInfo> contacts;
    private ContactListener contactListener;

    public ContactAdapter(List<ContactInfo> contacts, ContactListener contactListener) {
        this.contacts = contacts;
        this.contactListener = contactListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setUserData(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phoneNumber, email, district;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textName);
            phoneNumber = itemView.findViewById(R.id.textPhoneNumber);
            email = itemView.findViewById(R.id.textEmail);
            district = itemView.findViewById(R.id.textDistrict);
        }

        void setUserData(ContactInfo contactInfo){
            name.setText(contactInfo.name);
            phoneNumber.setText(contactInfo.phoneNumber);
            email.setText(contactInfo.email);
            district.setText(contactInfo.district);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contactListener.onContactCLicked(contactInfo);
                }
            });
        }
    }

    public interface ContactListener {
        void onContactCLicked(ContactInfo contactInfo);
    }
}
