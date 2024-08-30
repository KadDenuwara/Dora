package com.demo.dora.activityAI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.demo.dora.R;
import com.google.android.material.textfield.TextInputEditText;

public class AIimageActivity extends AppCompatActivity {
    private static final int SELECT_IMAGE_REQUEST = 1;
    private Bitmap image;
    private ImageView imageView;
    private TextInputEditText queryEditText;
    private TextView responseTextView;
    private Button sendQueryButton, selectImageButton;
    private ProgressBar progressBar;
    private ImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aiimage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        queryEditText = findViewById(R.id.queryEditText2);
        responseTextView = findViewById(R.id.modelResponseTextView2);
        sendQueryButton = findViewById(R.id.sendPromptButton2);
        selectImageButton = findViewById(R.id.selectImageButton2);
        progressBar = findViewById(R.id.sendPromptProgressBar2);
        imageView = findViewById(R.id.imageView2);
        imageBack = findViewById(R.id.imageBack);

        imageBack.setOnClickListener(v -> onBackPressed());

        sendQueryButton.setOnClickListener(v -> {
            GeminiPro model = new GeminiPro();

            String query = queryEditText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);

            responseTextView.setText("");
            queryEditText.setText("");

            model.getResponseImage(query, image, new ResponseCallback() {
                @Override
                public void onResponse(String response) {
                    responseTextView.setText(response);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError(Throwable throwable) {
                    Toast.makeText(AIimageActivity.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

        selectImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE_REQUEST);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap originalImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                int originalWidth = originalImage.getWidth();
                int originalHeight = originalImage.getHeight();

                int targetWidth = (int) (originalWidth * 0.5);
                int targetHeight = (int) (originalHeight * 0.5);

                image = Bitmap.createScaledBitmap(originalImage, targetWidth, targetHeight, false);
                imageView.setImageBitmap(originalImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}