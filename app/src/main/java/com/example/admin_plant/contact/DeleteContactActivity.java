package com.example.admin_plant.contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin_plant.R;
import com.example.admin_plant.utilities.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DeleteContactActivity extends AppCompatActivity implements ContactAdapter.ContactListener {

    ImageView backArrow;

    RecyclerView contactRecyclerView;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        findSection();

        firebaseFirestore = FirebaseFirestore.getInstance();
        backArrow.setOnClickListener(view -> onBackPressed());
        getContacts();
    }

    private void getContacts() {

        firebaseFirestore.collection(Constants.KEY_CONTACTS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<ContactInfo> contactInfoList = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            ContactInfo contacts = new ContactInfo();
                            contacts.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            contacts.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            contacts.phoneNumber = queryDocumentSnapshot.getString(Constants.KEY_PHONE_NUMBER);
                            contacts.district = queryDocumentSnapshot.getString(Constants.KEY_DISTRICT);
                            contactInfoList.add(contacts);
                        }
                        if (contactInfoList.size() > 0) {
                            ContactAdapter adapter = new ContactAdapter(contactInfoList, this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            contactRecyclerView.setAdapter(adapter);
                            contactRecyclerView.setLayoutManager(linearLayoutManager);
                        } else {
                            showToast("Empty List");
                        }
                    } else {
                        showToast(task.getException().getMessage().toString());
                    }
                });
    }

    public void showToast(String Message) {
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
    }

    private void findSection() {
        backArrow = findViewById(R.id.backArrow);
        contactRecyclerView = findViewById(R.id.contactRecyclerView);
    }

    @Override
    public void onContactCLicked(ContactInfo contactInfo) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DeleteContactActivity.this);
        builder1.setMessage("Are you sure you want to Delete this?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Toast.makeText(DeleteContactActivity.this, "Contacts Deleted", Toast.LENGTH_SHORT).show();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}