package com.example.admin_plant.imageSlider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin_plant.MainActivity;
import com.example.admin_plant.R;
import com.example.admin_plant.utilities.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class AddImageToSliderActivity extends AppCompatActivity {

    FrameLayout layoutSliderImage;

    ImageView sliderImage;
    ImageView backArrow;
    TextView textAdd;

    AppCompatButton sliderImagePostButton;

    private Uri uri= null;
    private static final int GALLERY_CODE=2;

    DatabaseReference mDatabase;

    StorageReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_to_slider);

        findSection();

        mRef= FirebaseStorage.getInstance().getReference();

        backArrow.setOnClickListener(view -> {
            onBackPressed();
        });

        String intentData = getIntent().getStringExtra(Constants.KEY_TO_ADD);

        layoutSliderImage.setOnClickListener(view -> {
            textAdd.setVisibility(View.GONE);
            Intent galleryIntent =new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent,GALLERY_CODE);
        });

        sliderImagePostButton.setOnClickListener(view -> {
            if (intentData.equals("SlideShow")){
                String text = "SlideShow";
                postImage(text);
            } else if (intentData.equals("ContactSlideShow")){
                String text = "ContactSlideShow";
                postImage(text);
            } else if (intentData.equals("DeforestationSlideShow")){
                String text = "DeforestationSlideShow";
                postImage(text);
            }
        });
    }

    private void postImage(String text) {

        mDatabase = FirebaseDatabase.getInstance().getReference().child(text);

        if(uri != null) {
            StorageReference filePath= mRef.child("Slider Image").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnCompleteListener(task -> {
                if (task.isSuccessful())
                {
                    task.getResult().getStorage().getDownloadUrl()
                            .addOnSuccessListener(uri -> {
                                String downloadUrl= uri.toString();

                                mDatabase.setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        showToast("Image Added");
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        showToast(e.getMessage());
                                    }
                                });
                            });
                }
                else
                {

                }
            });
        }
    }

    private void findSection() {
        layoutSliderImage = findViewById(R.id.layoutSliderImage);
        sliderImage = findViewById(R.id.sliderImage);
        backArrow = findViewById(R.id.backArrow);
        textAdd = findViewById(R.id.textAddImage);

        sliderImagePostButton = findViewById(R.id.sliderImagePostButton);
    }

    public void showToast(String Message){
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== GALLERY_CODE && resultCode == RESULT_OK){
            uri =data.getData();

            sliderImage.setImageURI(uri);
        }
    }
}