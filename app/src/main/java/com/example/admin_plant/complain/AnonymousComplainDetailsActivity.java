package com.example.admin_plant.complain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin_plant.R;
import com.example.admin_plant.utilities.Constants;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AnonymousComplainDetailsActivity extends AppCompatActivity  {

    ImageView backArrow;
    ImageView anonymousImage;

    TextView anonymousLocation;
    TextView anonymousDetailsDesc;

    AppCompatButton anonymousForwardButton;
    AppCompatButton anonymousDeleteButton;

    AnonymousComplainList complainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous_complain_details);

        findSection();

        backArrow.setOnClickListener(view -> {
            onBackPressed();
        });

        complainList = (AnonymousComplainList) getIntent().getSerializableExtra(Constants.KEY_COLLECTION_COMPLAIN);


        Picasso.get().load(complainList.anonymousComplainImage).fit().centerInside().placeholder(R.drawable.placeholder_image).
                into(anonymousImage);
        anonymousLocation.setText(complainList.location);
        anonymousDetailsDesc.setText(complainList.description);

        anonymousForwardButton.setOnClickListener(view -> {
            showToast("Will be forwarded to the authority");
            finish();
        });

        anonymousDeleteButton.setOnClickListener(view -> {
            showToast("Deleted");
            finish();
        });


    }

    private void findSection() {

        backArrow = findViewById(R.id.backArrow);
        anonymousImage = findViewById(R.id.anonymousImage);
        anonymousLocation = findViewById(R.id.anonymousLocation);
        anonymousDetailsDesc = findViewById(R.id.anonymousDetailsDesc);
        anonymousForwardButton = findViewById(R.id.anonymousForwardButton);
        anonymousDeleteButton = findViewById(R.id.anonymousDeleteButton);

    }

    public void showToast(String Message){
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
    }


}