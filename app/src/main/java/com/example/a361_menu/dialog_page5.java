package com.example.a361_menu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class dialog_page5 extends AppCompatActivity {

    private int Index = 0;
    List<Dialog> dialogues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_page1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView detailTextView = findViewById(R.id.detail);
        TextView textView = findViewById(R.id.text);
        Button changeTextButton = findViewById(R.id.changeTextButton);
        LinearLayout optionButton = findViewById(R.id.dialogOptions);
        ImageButton opt1 =findViewById(R.id.option1);
        ImageButton opt2 =findViewById(R.id.option2);
        TextView opt1Text = findViewById(R.id.option1Text);
        TextView opt2Text = findViewById(R.id.option2Text);
        View blackScreen = findViewById(R.id.blackScreen);
        ImageView charImageView1 = findViewById(R.id.characterImage1);
        ImageView charImageView2 = findViewById(R.id.characterImage2);
        ImageView backgroundView = findViewById(R.id.background);

        textView.setText(getString(R.string.s1));
        detailTextView.setText(getString(R.string.s1_d));
        charImageView1.setImageResource(R.drawable.empty);
        charImageView2.setImageResource(R.drawable.empty);
        optionButton.setVisibility(View.GONE);

        // Scene 2: ตามหาพี่ยาม
        dialogues.add(new Dialog(getString(R.string.b1_scene_2_title), getString(R.string.b1_scene_2_desc), 0, 0));
        dialogues.add(new Dialog(null, getString(R.string.b1_mc_dialog_key), R.drawable.mc_scared, 0));
        dialogues.add(new Dialog(null, getString(R.string.b1_pued_dialog_1), 0, R.drawable.pued_normal));
        dialogues.add(new Dialog(null, getString(R.string.b1_pued_suggestion_search), 0, R.drawable.pued_excited));

// Scene 3: หลังหลบหนีสำเร็จ
        dialogues.add(new Dialog(getString(R.string.b1_scene_3_title), getString(R.string.b1_mc_dialog_2), R.drawable.mc_surprised, 0));
        dialogues.add(new Dialog(null, getString(R.string.b1_guard_dialog_1), 0, R.drawable.guard_normal));
        dialogues.add(new Dialog(null, getString(R.string.b1_mc_dialog_3), R.drawable.mc_scared, 0));
        dialogues.add(new Dialog(null, getString(R.string.b1_pued_reaction_2), 0, R.drawable.pued_scared));
        dialogues.add(new Dialog(null, getString(R.string.b1_guard_dialog_2), 0, R.drawable.guard_creepy));
        dialogues.add(new Dialog(null, getString(R.string.b1_guard_dialog_3), 0, R.drawable.guard_flex));
        dialogues.add(new Dialog(null, getString(R.string.b1_guard_dialog_3), getString(R.string.b1_final_choice_2), getString(R.string.b1_final_choice_1), "useFakeKey", "noUseFakeKey", 1, 0));


        changeTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);

                // Get the corresponding dialogue from the dialogues list
                Dialog dialogue = dialogues.get(Index);

                // Check if the dialogue exists, then update the UI accordingly
                if (dialogue != null) {
                    if(dialogue.option1Action == null && dialogue.option2Action == null ){
                        Index++;
                    }
                    updateDialogue(
                            dialogue.getText(),
                            dialogue.getDetail(),
                            dialogue.getOption1(),
                            dialogue.getOption2(),
                            dialogue.getOption1Action(),
                            dialogue.getOption2Action(),
                            dialogue.getImageLeft(),
                            dialogue.getImageRight()
                    );
                } else {
                    // Handle the case where there is no more dialogue
                    updateDialogue("End of dialogue", null, null, null, null, null,0, 0);
                }
            }
        });
    }

    private void updateDialogue(String text, String details, String option1Text, String option2Text, String option1Action, String option2Action, int charImage1Path, int charImage2Path) {
        // Views
        TextView textView = findViewById(R.id.text);
        TextView detailTextView = findViewById(R.id.detail);
        Button changeTextButton = findViewById(R.id.changeTextButton);
        LinearLayout optionButton = findViewById(R.id.dialogOptions);
        ImageButton opt1 = findViewById(R.id.option1);
        ImageButton opt2 = findViewById(R.id.option2);
        TextView opt1Text = findViewById(R.id.option1Text);
        TextView opt2Text = findViewById(R.id.option2Text);
        View blackScreen = findViewById(R.id.blackScreen);
        ImageView charImageView1 = findViewById(R.id.characterImage1);
        ImageView charImageView2 = findViewById(R.id.characterImage2);

        // Animations
        Animation moveToSide = AnimationUtils.loadAnimation(this, R.anim.move_to_left);
        Animation moveBack = AnimationUtils.loadAnimation(this, R.anim.move_back_left);
        Animation moveToRight = AnimationUtils.loadAnimation(this, R.anim.move_to_right);
        Animation moveBackRight = AnimationUtils.loadAnimation(this, R.anim.move_back_right);
        Animation optionBounce = AnimationUtils.loadAnimation(this, R.anim.bounce);

        ColorFilter filter_dark = new PorterDuffColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);

        // Helper method to handle image transitions and animations
        updateCharacterImage(charImage1Path, charImageView1, charImageView2, moveToSide, moveBack, filter_dark);
        updateCharacterImage(charImage2Path, charImageView2, charImageView1, moveToRight, moveBackRight, filter_dark);

        // Handle text visibility and update
        textView.setVisibility(text == null ? View.GONE : View.VISIBLE);
        if (text != null) textView.setText(text);
        //detailTextView.setText(details);
        startTypingAnimation(detailTextView, details, 1);

        handleOptions(option1Text, option2Text, optionButton, changeTextButton, opt1, opt2, opt1Text, opt2Text, optionBounce);

        opt1.setOnClickListener(v -> handleOptionClick(option1Action, optionButton, textView, detailTextView, changeTextButton, blackScreen));
        opt2.setOnClickListener(v -> handleOptionClick(option2Action, optionButton, textView, detailTextView, changeTextButton, blackScreen));
    }

    private void updateCharacterImage(int imagePath, ImageView imageView, ImageView otherImageView, Animation moveTo, Animation moveBack, ColorFilter filter) {
        if (imagePath == 1) {
            imageView.clearColorFilter();
        } else if (imagePath == 0) {
            imageView.setColorFilter(filter);
        } else {
            imageView.startAnimation(moveTo);
            otherImageView.setColorFilter(filter);

            moveTo.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imageView.setImageResource(imagePath);
                    imageView.clearColorFilter();
                    imageView.startAnimation(moveBack);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }

    private void handleOptions(String option1Text, String option2Text, LinearLayout optionButton, Button changeTextButton, ImageButton opt1, ImageButton opt2, TextView opt1Text, TextView opt2Text, Animation optionBounce) {
        View blackScreen = findViewById(R.id.blackScreen);
        if (option1Text == null && option2Text == null) {
            optionButton.setVisibility(View.GONE);
            changeTextButton.setVisibility(View.VISIBLE);
        } else {
            optionButton.setVisibility(View.VISIBLE);
            changeTextButton.setVisibility(View.GONE);
            blackScreen.setVisibility(View.VISIBLE);

            if (option1Text != null) {
                opt1Text.setText(option1Text);
                opt1.setVisibility(View.VISIBLE);
                opt1.startAnimation(optionBounce);
            } else {
                opt1.setVisibility(View.GONE);
            }

            if (option2Text != null) {
                opt2Text.setText(option2Text);
                opt2.setVisibility(View.VISIBLE);
                opt2.startAnimation(optionBounce);
            } else {
                opt2.setVisibility(View.GONE);
            }
        }
    }

    private void handleOptionClick(String optionAction, LinearLayout optionButton, TextView textView, TextView detailTextView, Button changeTextButton, View blackScreen) {
        optionButton.setVisibility(View.GONE);
        changeTextButton.setVisibility(View.VISIBLE);
        blackScreen.setVisibility(View.GONE);
        handleOptionAction(optionAction);
    }


    private void handleOptionAction(String action) {
        switch (action) {
            case "gameOver":
                Intent gameOverIntent = new Intent(this, GameOverPage.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(gameOverIntent);
                break;
            case "noUseFakeKey":
                dialogues.add(new Dialog(null, getString(R.string.b1_mc_dialog_4), R.drawable.mc_scared, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_pued_dialog_3), 0, R.drawable.pued_cry));

                dialogues.add(new Dialog(null, getString(R.string.b1_surrender_dialog_1), R.drawable.mc_scared, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_surrender_dialog_2), R.drawable.mc_normal, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_guard_dialog_4), 0, R.drawable.guard_flex));
                dialogues.add(new Dialog(null, getString(R.string.b1_guard_reveal), 0, R.drawable.guard_flex2));
                dialogues.add(new Dialog(null, getString(R.string.b1_mc_final_reaction), R.drawable.mc_cry, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_ending), R.drawable.empty, R.drawable.empty));
                Index++;
                break;
            case "useFakeKey":
                dialogues.add(new Dialog(null, getString(R.string.b1_fake_key_result_1), R.drawable.mc_surprised, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_fake_key_result_2), 0, R.drawable.pued_scared));
                dialogues.add(new Dialog(null, getString(R.string.b1_mc_dialog_4), R.drawable.mc_scared, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_pued_dialog_3), 0, R.drawable.pued_cry));

                dialogues.add(new Dialog(null, getString(R.string.b1_surrender_dialog_1), R.drawable.mc_scared, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_surrender_dialog_2), R.drawable.mc_normal, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_guard_dialog_4), 0, R.drawable.guard_flex));
                dialogues.add(new Dialog(null, getString(R.string.b1_guard_reveal), 0, R.drawable.guard_flex2));
                dialogues.add(new Dialog(null, getString(R.string.b1_mc_final_reaction), R.drawable.mc_cry, 0));
                dialogues.add(new Dialog(null, getString(R.string.b1_ending), R.drawable.empty, R.drawable.empty));
                Index++;
                break;
            default:
                Index++;
                break;
        }
    }

    private void startTypingAnimation(TextView textView, String fullText, int typingDelay) {
        Handler handler = new Handler();
        int[] index = {0};
        Runnable typingRunnable = new Runnable() {
            @Override
            public void run() {
                if (index[0] < fullText.length()) {
                    textView.setText(fullText.substring(0, index[0] + 1));
                    index[0]++;
                    handler.postDelayed(this, typingDelay);
                }
            }
        };
        handler.post(typingRunnable);
    }

}