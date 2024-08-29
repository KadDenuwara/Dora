package com.example.dora.Shorts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBinderMapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dora.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShortsActivity extends AppCompatActivity {

    private ArrayList<DataHandler> dataHandlers = new ArrayList<>();
    ViewPager2 viewPager2;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shorts);

        database = FirebaseDatabase.getInstance();

        DatabaseReference mRef = database.getReference("Videos");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    for (DataSnapshot s : snapshot.getChildren()) {

                        Map map = (Map) s.getValue();
                        DataHandler data = new DataHandler(map.get("title").toString(), map.get("url").toString());

                        dataHandlers.add(data);

                        viewPager2 = findViewById(R.id.viewPager);
                        PagerAdapter pagerAdapter = new PagerAdapter(dataHandlers, ShortsActivity.this);
                        viewPager2.setAdapter(pagerAdapter);


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", error.getMessage());

            }
        });
    }
}