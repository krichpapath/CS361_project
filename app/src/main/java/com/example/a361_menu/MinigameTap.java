package com.example.a361_menu;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a361_menu.R;

public class MinigameTap extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView scoreText;
    private TextView countdownText;
    private ImageView startMessage;// ตัวแปรสำหรับข้อความเริ่มต้น
    private int progress = 0;
    private final Handler handler = new Handler();
    private int timeRemaining = 10; // เวลานับถอยหลังเริ่มต้น
    private boolean gameOver = false; // ตัวแปรเช็คว่าเกมจบหรือยัง
    private boolean gameStarted = false; // ตัวแปรเช็คว่าเกมเริ่มแล้ว

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame_tap);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // เชื่อมต่อ UI
        progressBar = findViewById(R.id.progressBar);
        scoreText = findViewById(R.id.scoreText);
        countdownText = findViewById(R.id.countdownText);
        startMessage = findViewById(R.id.startMessage);
        LinearLayout mainLayout = findViewById(R.id.mainLayout);

        // ตั้งค่าข้อความเริ่มต้นและหน่วงเวลา 3 วินาทีก่อนเริ่มเกม
        startMessage.setVisibility(TextView.VISIBLE); // แสดงข้อความ
        handler.postDelayed(() -> {
            startMessage.setVisibility(TextView.GONE); // ซ่อนข้อความเมื่อครบ 3 วินาที
            gameStarted = true; // เริ่มเกม
            startCountdown(); // เริ่มนับถอยหลัง
        }, 3000);

        // ตั้งค่า OnTouchListener
        mainLayout.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && !gameOver && gameStarted) {
                if (progress < 100) {
                    progress += 5; // เพิ่ม progress 5 ต่อการกด
                    progressBar.setProgress(progress);
                    scoreText.setText("Progress: " + progress + "%");
                }

                // ตรวจสอบว่าชนะหรือไม่
                if (progress >= 100) {
                    gameOver = true;
                    scoreText.setText("You Win!");
                    scoreText.setVisibility(View.INVISIBLE);
                    startMessage.setImageResource(R.drawable.youwin); // เปลี่ยนภาพใน startMessage เป็น youwin.png
                    startMessage.setVisibility(ImageView.VISIBLE);
                }
            }
            return true; // ให้ OnTouchListener รับ Event นี้
        });

        // ลด progress เมื่อเวลาผ่านไป
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progress > 0 && !gameOver && gameStarted) {
                    progress -= 2; // ลด progress
                    progressBar.setProgress(progress);
                    scoreText.setText("Progress: " + progress + "%");
                }
                if (!gameOver) {
                    handler.postDelayed(this, 100); // ลดทุกๆ 100ms
                }
            }
        }, 100);
    }

    // ฟังก์ชันเริ่มต้นการนับถอยหลัง
    private void startCountdown() {
        handler.postDelayed(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                if (timeRemaining > 0 && !gameOver) {
                    timeRemaining--; // ลดเวลา
                    countdownText.setText("Time: " + timeRemaining + "s"); // แสดงเวลาที่เหลือ
                    handler.postDelayed(this, 1000); // อัปเดตทุก 1 วินาที
                } else {
                    if (!gameOver) {
                        countdownText.setText("Time's Up!"); // เมื่อหมดเวลา
                        scoreText.setText("Game Over!");
                        gameOver = true; // สิ้นสุดเกม
                        scoreText.setVisibility(View.INVISIBLE);
                        startMessage.setImageResource(R.drawable.youlose); // เปลี่ยนภาพใน startMessage เป็น youwin.png
                        startMessage.setVisibility(ImageView.VISIBLE); // แสดงภาพ
                    }
                }
            }
        }, 1000); // เริ่มต้นที่ 1 วินาที
    }
}
