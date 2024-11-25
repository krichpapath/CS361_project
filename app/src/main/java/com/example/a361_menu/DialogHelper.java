package com.example.a361_menu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DialogHelper {
    private TextView detailTextView;
    private TextView textView;
    private LinearLayout optionButtonLayout;
    private Button option1;
    private Button option2;
    private Context context;

    public DialogHelper(Context context, TextView detailTextView, TextView textView,
                        LinearLayout optionButtonLayout, Button option1, Button option2) {
        this.context = context;
        this.detailTextView = detailTextView;
        this.textView = textView;
        this.optionButtonLayout = optionButtonLayout;
        this.option1 = option1;
        this.option2 = option2;
    }

    public void updateText(String detailText, String optionalText) {
        detailTextView.setText(detailText);
        if (optionalText != null) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(optionalText);
        } else {
            textView.setVisibility(View.GONE);
        }
    }

    public void showOptions(String option1Text, String option2Text, Runnable option1Action, Runnable option2Action) {
        optionButtonLayout.setVisibility(View.VISIBLE);
        option1.setText(option1Text);
        option2.setText(option2Text);

        option1.setOnClickListener(v -> {
            optionButtonLayout.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            option1Action.run();
        });

        option2.setOnClickListener(v -> {
            optionButtonLayout.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            option2Action.run();
        });
    }

    public void startNewActivity(Class<?> activityClass) {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
}
