package com.example.admin_plant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.admin_plant.blog.AddBlogActivity;
import com.example.admin_plant.complain.AnonymousComplainListActivity;
import com.example.admin_plant.complain.IdentityComplainListActivity;
import com.example.admin_plant.contact.AddContactActivity;
import com.example.admin_plant.contact.DeleteContactActivity;
import com.example.admin_plant.imageSlider.AddImageToSliderActivity;
import com.example.admin_plant.utilities.Constants;

public class MainActivity extends AppCompatActivity {


    AppCompatButton addImageSliderButton;
    AppCompatButton complainHandleButton;
    AppCompatButton contactsHandleButton;
    AppCompatButton blogButton;
    AppCompatButton userControlButton;

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findSection();

        addImageSliderButton.setOnClickListener(view -> {
            showDialog();
        });

        complainHandleButton.setOnClickListener(view -> {
            showComplainDialog();
        });

        contactsHandleButton.setOnClickListener(view -> {
            showContactsDialog();
        });

        blogButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddBlogActivity.class));
        });


    }

    private void showComplainDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialouge_box_3);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout anonymous = dialog.findViewById(R.id.anonymousLayout);
        LinearLayout identity = dialog.findViewById(R.id.identityLayout);


        anonymous.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, AnonymousComplainListActivity.class));

        });

        identity.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, IdentityComplainListActivity.class));
        });

        dialog.show();
    }

    private void showDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialouge_box_2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout homeSlider = dialog.findViewById(R.id.homeSliderLayout);
        LinearLayout contactSlider = dialog.findViewById(R.id.contactLayout);
        LinearLayout deforestationSlider = dialog.findViewById(R.id.deforestationLayout);


        homeSlider.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(MainActivity.this, AddImageToSliderActivity.class);
            intent.putExtra(Constants.KEY_TO_ADD,"SlideShow" );
            startActivity(intent);

        });

        contactSlider.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(MainActivity.this, AddImageToSliderActivity.class);
            intent.putExtra(Constants.KEY_TO_ADD,"ContactSlideShow" );
            startActivity(intent);
        });

        deforestationSlider.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(MainActivity.this, AddImageToSliderActivity.class);
            intent.putExtra(Constants.KEY_TO_ADD,"DeforestationSlideShow" );
            startActivity(intent);
        });

        dialog.show();
    }

    public void showContactsDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialouge_box);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LinearLayout addContact = dialog.findViewById(R.id.addLayout);
        LinearLayout deleteContact = dialog.findViewById(R.id.deleteLayout);


        addContact.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, AddContactActivity.class));

        });

        deleteContact.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, DeleteContactActivity.class));
        });

        dialog.show();
    }

    private void findSection() {
        backArrow = findViewById(R.id.backArrow);
        addImageSliderButton = findViewById(R.id.addImageSliderButton);
        complainHandleButton = findViewById(R.id.complainHandle);
        contactsHandleButton = findViewById(R.id.contactsHandle);
        blogButton = findViewById(R.id.blogButton);
        userControlButton = findViewById(R.id.userControlButton);

    }
}