package com.example.a361_menu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;

public class Dialogpage extends AppCompatActivity {

    // Array
    private int Index = 0;
    private String[] word;
    private boolean isOptionSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_page2);

        // อ้างอิง TextView และ Button
        TextView detailTextView = findViewById(R.id.detail);
        TextView TextView = findViewById(R.id.text);
        Button changeTextButton = findViewById(R.id.changeTextButton);
        LinearLayout optionButton = findViewById(R.id.dialogOptions);
        Button opt1 =findViewById(R.id.option1);
        Button opt2 =findViewById(R.id.option2);



        // ข้อความจาก strings.xml
        String s1   = getString(R.string.s1);       String s1_d  = getString(R.string.s1_d);
        String w   = getString(R.string.w);
        String w1   = getString(R.string.w1);       String w2    = getString(R.string.w2);
        String w3   = getString(R.string.w3);       String w4    = getString(R.string.w4);
        String w5   = getString(R.string.w5);       String w6    = getString(R.string.w6);
        String w7   = getString(R.string.w7);       String w8    = getString(R.string.w8);
        String w9   = getString(R.string.w9);       String w10   = getString(R.string.w10);
        String w11  = getString(R.string.w11);      String w12   = getString(R.string.w12);
        String s3   = getString(R.string.s3);       String s3_d  = getString(R.string.s3_d);


        String c1_s4   = getString(R.string.c1_s4);       String c1_w1  = getString(R.string.c1_w1);
        String c1_w2   = getString(R.string.c1_w2);       String c1_w3  = getString(R.string.c1_w3);
        String c1_w4   = getString(R.string.c1_w4);       String c1_w5  = getString(R.string.c1_w5);
        String c1_s5   = getString(R.string.c1_s5);       String c1_s6  = getString(R.string.c1_s6);
        String c1_w6   = getString(R.string.c1_w6);       String c1_w7  = getString(R.string.c1_w7);
        String c1_w8   = getString(R.string.c1_w8);       String c1_w9  = getString(R.string.c1_w9);
        String c1_w10  = getString(R.string.c1_w10);      String c1_w11 = getString(R.string.c1_w11);
        String c1_w12  = getString(R.string.c1_w12);      String c1_w13 = getString(R.string.c1_w13);
        String c1_s7   = getString(R.string.c1_s7);       String c1_w14 = getString(R.string.c1_w14);
        String c1_w15  = getString(R.string.c1_w15);      String c1_w14_1 = getString(R.string.c1_w14_1);
        String c1_w14_2 = getString(R.string.c1_w14_2);   String c1_w14_3 = getString(R.string.c1_w14_3);
        String c1_w14_4 = getString(R.string.c1_w14_4);   String c1_s9  = getString(R.string.c1_s9);
        String c1_s9_d = getString(R.string.c1_s9_d);


        //array
        word = new String[]{w,w1, w2, w3, w4, w5,s3_d/*6*/, w6, w7, w8, w9, w10, w11, w12,c1_w1/*14*/, c1_w2, c1_w3, c1_w4, c1_w5/*18*/, c1_w6/*19*/,
                c1_w7/*20*/, c1_w10/*21*/, c1_w11, c1_w12, c1_w13, c1_w14/*25*/,c1_w14_1, c1_w14_2, c1_w14_3, c1_w14_4
        };
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
                isOptionSelected = true;
                // ตั้งค่าข้อความใหม่จาก messages array โดยใช้ currentIndex
                detailTextView.setText(word[Index]);
                Index++;
                if(detailTextView.getText().toString().equals(word[6])){
                    TextView.setVisibility(View.VISIBLE);
                    TextView.setText(s3);
                }
                if(detailTextView.getText().toString().equals(word[14])){
                    TextView.setVisibility(View.VISIBLE);
                    TextView.setText(c1_s4);
                }
                if(detailTextView.getText().toString().equals(word[19])){
                    TextView.setVisibility(View.VISIBLE);
                    TextView.setText(c1_s5);
                }
                if (detailTextView.getText().toString().equals(word[20])) {
                    TextView.setVisibility(View.VISIBLE);
                    TextView.setText(c1_s6);
                    detailTextView.setText(c1_w7);
                    optionButton.setVisibility(View.VISIBLE);

                    opt1.setText(c1_w8);
                    opt2.setText(c1_w9);

                    opt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isOptionSelected = true;  // อัปเดตสถานะ
                            optionButton.setVisibility(View.GONE);
                            TextView.setVisibility(View.GONE);
                            detailTextView.setText(word[Index]); //อันนี้มันพูดซ้ำ แต่พอเป็นindex++ มันก็ข้ามจาก 20 ไป 22 เลย
                        }
                    });

                    opt2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isOptionSelected = true; // อัปเดตสถานะ
                            TextView.setVisibility(View.GONE);
                            optionButton.setVisibility(View.GONE);
                            detailTextView.setText("Game Over");

                        }
                    });
                }

                if(detailTextView.getText().toString().equals(word[25])){ //
                    TextView.setVisibility(View.VISIBLE);
                    TextView.setText(c1_s7);
                    optionButton.setVisibility(View.VISIBLE);

                    opt1.setText(c1_w14);
                    opt2.setText(c1_w15);

                    opt1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isOptionSelected = true;
                            optionButton.setVisibility(View.GONE);
                            TextView.setVisibility(View.GONE);
                            Index = 25;
                        }
                    });

                    opt2.setOnClickListener(new View.OnClickListener() {//ติดไว้ก่อน ---เล่นกับเเมว
                        @Override
                        public void onClick(View v) {
                            isOptionSelected = true;
                            TextView.setVisibility(View.GONE);
                            optionButton.setVisibility(View.GONE);
                            detailTextView.setText("Game Over");
                        }
                    });

                }

            }
        });
    }
}