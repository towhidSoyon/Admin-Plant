package com.example.admin_plant.complain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin_plant.R;
import com.example.admin_plant.utilities.Constants;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class IdentityComplainListActivity extends AppCompatActivity implements IdentityAdapter.IdentityListener{

    FirebaseFirestore firebaseFirestore;
    private RecyclerView identityRecyclerView;

    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_complain_list);

        backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(view -> onBackPressed());

        firebaseFirestore = FirebaseFirestore.getInstance();

        identityRecyclerView = findViewById(R.id.identityRecyclerView);

        getIdentityComplains();
    }

    private void getIdentityComplains() {

        firebaseFirestore.collection(Constants.KEY_COLLECTION_COMPLAIN_WITH_IDENTITY)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null){
                        List<IdentityComplainList> lists=new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            IdentityComplainList identityComplainList=new IdentityComplainList();
                            identityComplainList.identityComplainImage = queryDocumentSnapshot.getString(Constants.KEY_COMPLAIN_ANONYMOUS_IMAGE);
                            identityComplainList.location = queryDocumentSnapshot.getString(Constants.KEY_LOCATION);
                            identityComplainList.description = queryDocumentSnapshot.getString(Constants.KEY_DESCRIPTION);
                            lists.add(identityComplainList);
                        }
                        if (lists.size()>0){
                            IdentityAdapter adapter=new IdentityAdapter(lists,this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            identityRecyclerView.setAdapter(adapter);
                            identityRecyclerView.setLayoutManager(linearLayoutManager);
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
    public void onIdentityClicked(IdentityComplainList identityComplainList) {
        Intent intent = new Intent(getApplicationContext(),IdentityComplainDetailsActivity.class);
        intent.putExtra(Constants.KEY_COLLECTION_COMPLAIN,identityComplainList);
        startActivity(intent);
    }
}