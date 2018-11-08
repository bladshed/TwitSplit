package com.example.francismark.twitsplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    // Declare variables
    private TextView displayMessage;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Initialize UI variables
        displayMessage = findViewById(R.id.display_message);
        backBtn = findViewById(R.id.back_btn);

        // Add listener to submit button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go back to main activity
                Intent intent = new Intent(MessageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Get the intent of the activity
        Intent intent = getIntent();

        // Get message from previous activity
        String message = intent.getStringExtra("MESSAGE");

        // Convert message by using standalone splitMessage function
        String messageStr = MainActivity.splitMessage(message);

        // Display message on the activity
        displayMessage.setText(messageStr);

    }
}
