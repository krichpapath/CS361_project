package com.example.a361_menu;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dialog_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView character1Image = findViewById(R.id.character1Image);
        ImageView character2Image = findViewById(R.id.character2Image);

        character1Image.setImageResource(R.drawable.left_ricardo_milos);
        character2Image.setImageResource(R.drawable.right_ricardo_milos);

        // Make the overlay visible
        ImageView overlayImage = findViewById(R.id.overlayImage);
        View overlayBackground = findViewById(R.id.overlayBackground);

//        overlayImage.setVisibility(View.VISIBLE);
//        overlayBackground.setVisibility(View.VISIBLE);
    }
}