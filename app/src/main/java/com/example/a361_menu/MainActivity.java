package com.example.a361_menu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // set LANDSCAPE
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Find the button by its ID
        Button btNewStory = findViewById(R.id.bt_newstory);
        Button btstart = findViewById(R.id.bt_start);

        btNewStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the DialogActivity
                Intent intent = new Intent(MainActivity.this, dialog_page5.class);
                startActivity(intent);  // Start the activity
            }
        });

        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the DialogActivity
                Intent intent = new Intent(MainActivity.this, Dialogpage.class);
                startActivity(intent);  // Start the activity
            }
        });

        Button btexit = findViewById(R.id.bt_exit);

        btexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
