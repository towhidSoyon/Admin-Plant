package com.example.admin_plant.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin_plant.MainActivity;
import com.example.admin_plant.R;
import com.example.admin_plant.utilities.Constants;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddContactActivity extends AppCompatActivity {

    ImageView backArrow;

    EditText nameEditText;
    EditText phoneNumberEditText;
    EditText mailEditText;
    EditText districtEditText;

    AppCompatButton postButton;
    ProgressBar progressBar;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        findSection();

        firebaseFirestore = FirebaseFirestore.getInstance();

        postButton.setOnClickListener(view -> {
            String name = nameEditText.getText().toString();
            String phone = phoneNumberEditText.getText().toString();
            String mail = mailEditText.getText().toString();
            String district = districtEditText.getText().toString();

            addContact(name,phone,mail,district);
        });
    }

    private void addContact(String name, String phone, String mail, String district) {

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> blog = new HashMap<>();
        blog.put(Constants.KEY_NAME, name);
        blog.put(Constants.KEY_PHONE_NUMBER, phone);
        blog.put(Constants.KEY_EMAIL, mail);
        blog.put(Constants.KEY_DISTRICT, district);
        database.collection(Constants.KEY_CONTACTS).document().set(blog).
                addOnSuccessListener(documentReference -> {
                    showToast("Contact Added");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                })
                .addOnFailureListener(exception -> {
                    showToast(exception.getMessage());

                });
    }

    private void findSection() {
        backArrow = findViewById(R.id.backArrow);
        nameEditText = findViewById(R.id.nameEditText);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        mailEditText = findViewById(R.id.emailEditText);
        districtEditText = findViewById(R.id.districtEditText);

        postButton = findViewById(R.id.postButton);
        progressBar = findViewById(R.id.progressBar);

    }

    public void showToast(String Message){
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
    }
}