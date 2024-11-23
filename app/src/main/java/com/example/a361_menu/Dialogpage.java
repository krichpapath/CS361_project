package com.example.a361_menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;  // เพิ่มการอ้างอิงนี้
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;

public class Dialogpage extends AppCompatActivity {

    // Array
    private int Index = 0;
    private String[] word;
    private String[] scene;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_page2);

        // อ้างอิง TextView และ Button
        TextView detailTextView = findViewById(R.id.detail);
        TextView TextView = findViewById(R.id.text);
        Button changeTextButton = findViewById(R.id.changeTextButton);
        LinearLayout optionButton = findViewById(R.id.dialogOptions);

        // ข้อความจาก strings.xml
        String s1   = getString(R.string.s1);       String s1_d  = getString(R.string.s1_d);
        String w1   = getString(R.string.w1);       String w2    = getString(R.string.w2);
        String w3   = getString(R.string.w3);       String w4    = getString(R.string.w4);
        String w5   = getString(R.string.w5);       String w6    = getString(R.string.w6);
        String w7   = getString(R.string.w7);       String w8    = getString(R.string.w8);
        String w9   = getString(R.string.w9);       String w10   = getString(R.string.w10);
        String w11  = getString(R.string.w11);      String w12   = getString(R.string.w12);
        String s3   = getString(R.string.s3);       String s3_d  = getString(R.string.s3_d);

        //array
        word = new String[]{w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12};
        scene = new String[]{s1, s3,};

        // เซ็ตข้อความเริ่มต้น
        TextView.setText(s1);
        detailTextView.setText(s1_d);
        optionButton.setVisibility(View.GONE);


        // ตั้งค่า margin top 10dp
        ViewGroup.LayoutParams params = detailTextView.getLayoutParams(); // แก้เป็น ViewGroup.LayoutParams
        if (params instanceof ViewGroup.MarginLayoutParams) {  // ตรวจสอบว่า params เป็น MarginLayoutParams หรือไม่
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) params;
            marginParams.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            detailTextView.setLayoutParams(marginParams);  // ตั้งค่ากลับ
        }

        changeTextButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                TextView.setVisibility(View.GONE);
                // ตั้งค่าข้อความใหม่จาก messages array โดยใช้ currentIndex
                detailTextView.setText(word[Index]);
                // เพิ่มค่า index เพื่อให้ไปที่ข้อความถัดไป
                Index++;
                if(detailTextView.getText().toString().equals(word[5])){
                    TextView.setVisibility(View.VISIBLE);
                    TextView.setText(s3);
                    detailTextView.setText(s3_d);
                }


            }
        });
    }
}
