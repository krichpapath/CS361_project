package com.example.a361_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the button by its ID
        Button btNewStory = findViewById(R.id.bt_newstory);

        // Set the OnClickListener
        btNewStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the DialogActivity
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);  // Start the activity
            }
        });
    }
}
