package com.example.admin_plant.complain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin_plant.R;
import com.example.admin_plant.utilities.Constants;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class AnonymousComplainListActivity extends AppCompatActivity implements AnonymousAdapter.AnonymousListener {

    FirebaseFirestore firebaseFirestore;
    private RecyclerView anonymousRecyclerView;

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous_complain_list);

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(view -> onBackPressed());

        firebaseFirestore = FirebaseFirestore.getInstance();

        anonymousRecyclerView = findViewById(R.id.anonymousRecyclerView);

        getAnonymousComplains();
    }

    private void getAnonymousComplains() {

        firebaseFirestore.collection(Constants.KEY_COLLECTION_COMPLAIN_ANONYMOUS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null){
                        List<AnonymousComplainList> Lists=new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            AnonymousComplainList complainList=new AnonymousComplainList();
                            complainList.anonymousComplainImage = queryDocumentSnapshot.getString(Constants.KEY_COMPLAIN_ANONYMOUS_IMAGE);
                            complainList.location = queryDocumentSnapshot.getString(Constants.KEY_LOCATION);
                            complainList.description = queryDocumentSnapshot.getString(Constants.KEY_DESCRIPTION);
                            Lists.add(complainList);
                        }
                        if (Lists.size()>0){
                            AnonymousAdapter adapter=new AnonymousAdapter(Lists,this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            anonymousRecyclerView.setAdapter(adapter);
                            anonymousRecyclerView.setLayoutManager(linearLayoutManager);
                        }
                        else {
                            showToast("Empty Blogs");
                        }
                    }
                    else {
                        showToast(task.getException().getMessage().toString());
                    }
                });
    }

    public void showToast(String Message){
        Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnonymousClicked(AnonymousComplainList anonymousComplainList) {
        Intent intent = new Intent(getApplicationContext(),AnonymousComplainDetailsActivity.class);
        intent.putExtra(Constants.KEY_COLLECTION_COMPLAIN,anonymousComplainList);
        startActivity(intent);

    }
}