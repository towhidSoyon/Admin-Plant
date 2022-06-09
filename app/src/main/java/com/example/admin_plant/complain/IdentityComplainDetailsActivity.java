package com.example.admin_plant.complain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin_plant.R;
import com.example.admin_plant.utilities.Constants;
import com.squareup.picasso.Picasso;

public class IdentityComplainDetailsActivity extends AppCompatActivity {

    ImageView backArrow;
    ImageView identityImage;

    TextView identityLocation;
    TextView identityDetailsDesc;

    AppCompatButton identityForwardButton;
    AppCompatButton identityDeleteButton;

    IdentityComplainList identityComplainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_complain_details);

        findSection();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        identityComplainList = (IdentityComplainList) getIntent().getSerializableExtra(Constants.KEY_COLLECTION_COMPLAIN);

        Picasso.get().load(identityComplainList.identityComplainImage).fit().centerInside().placeholder(R.drawable.placeholder_image).
                into(identityImage);
        identityLocation.setText(identityComplainList.location);

        identityDetailsDesc.setText(identityComplainList.description);

        identityForwardButton.setOnClickListener(view -> {
            showToast("Will be forwarded to the authority");
            finish();
        });

        identityDeleteButton.setOnClickListener(view -> {
            showToast("Deleted");
            finish();
        });
    }

    private void findSection() {
        backArrow = findViewById(R.id.backArrow);
        identityImage = findViewById(R.id.identityImage);
        identityLocation = findViewById(R.id.identityLocation);
        identityDetailsDesc = findViewById(R.id.identityDetailsDesc);
        identityForwardButton = findViewById(R.id.identityForwardButton);
        identityDeleteButton = findViewById(R.id.identityDeleteButton);

    }

    public void showToast(String Message){
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
    }
}