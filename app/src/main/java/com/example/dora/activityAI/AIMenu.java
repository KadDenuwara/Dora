package com.example.dora.activityAI;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dora.Music.SongActivity;
import com.example.dora.R;
import com.example.dora.Shorts.ShortsActivity;
import com.example.dora.activities.MainActivity;

public class AIMenu extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aimenu);

        Button buttonFeature1 = findViewById(R.id.button_feature1);
        Button buttonFeature2 = findViewById(R.id.button_feature2);
        Button buttonFeature3 = findViewById(R.id.button_feature3);
        ImageButton buttonChat = findViewById(R.id.nav_chat);
        ImageButton buttonMusic = findViewById(R.id.nav_music);
        ImageButton buttonVideo = findViewById(R.id.nav_video);

        buttonFeature1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIMenu.this, AIchatActivity.class);
                startActivity(intent);
            }
        });

        buttonFeature2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIMenu.this, AIimageActivity.class);
                startActivity(intent);
            }
        });
        buttonFeature3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIMenu.this, AItextActivity.class);
                startActivity(intent);
            }
        });

        buttonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIMenu.this, SongActivity.class);
                startActivity(intent);
            }
        });

        buttonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AIMenu.this, ShortsActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AIMenu.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}