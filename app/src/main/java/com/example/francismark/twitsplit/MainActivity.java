package com.example.francismark.twitsplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    private EditText message;
    private Button submit_btn;
    private Boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI variables
        message = findViewById(R.id.form_message_field);
        submit_btn = findViewById(R.id.submit_btn);

        // Boolean for word/s with more than 50 characters
        bool = true;

        // Add listener to submit button
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get value from message field
                String message_str = message.getText().toString();

                // Get method value
                String result = splitMessage(message_str);

                // Set error message
                String error = "[ERROR] " + message_str;

                if(result.equals(error)){
                    // Throw an error
                    Toast.makeText(MainActivity.this, "ERROR: Word/s contained more than 50 characters.",
                            Toast.LENGTH_LONG).show();

                } else {
                    // Pass the value to the next activity
                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    intent.putExtra("MESSAGE", message_str);
                    startActivity(intent);
                }
            }
        });
    }

    // Standalone function to split the messages
    public static String splitMessage(String message_str) {

        // Initialize variable
        StringBuilder finalMessage = new StringBuilder();
        Boolean noError = true;

        // Check if message is more than 50 chars
        if(message_str.length() > 50){

            // Initialize var
            StringBuilder tempMessage = new StringBuilder();
            int ctr = 1;

            // Split message by whitespace
            String[] messageAry = message_str.split(" ");

            // Count lines for split messages
            for (String word: messageAry){

                if(word.length() <= 50){
                    if(tempMessage.toString().isEmpty()){
                        tempMessage.append("1/* ").append(word).append(" ");
                    } else if ((tempMessage.toString() + " " + word).length() == (50*ctr)){
                        tempMessage.append(word);
                    } else if ((tempMessage.toString() + " " + word).length() < (50*ctr)) {
                        tempMessage.append(word).append(" ");
                    } else if ((tempMessage.toString() + " " + word).length() > (50*ctr)){
                        tempMessage.append(System.lineSeparator());
                        tempMessage.append("1/* ").append(word).append(" ");
                        ctr++;
                    }
                } else {
                    return "[ERROR] "+ message_str;
                }
            }

            // Get line counts and reset ctr to 1
            int count = ctr;
            ctr=1;

            // Finalize message
            for (String word: messageAry){

                if(word.length() <= 50){
                    if(finalMessage.toString().isEmpty()){
                        finalMessage.append(ctr + "/" + count + " ").append(word).append(" ");
                    } else if ((finalMessage.toString() + " " + word).length() == (50*ctr)){
                        finalMessage.append(word);
                    } else if ((finalMessage.toString() + " " + word).length() < (50*ctr)) {
                        finalMessage.append(word).append(" ");
                    } else if ((finalMessage.toString() + " " + word).length() > (50*ctr)){
                        ctr++;
                        finalMessage.append(System.lineSeparator());
                        finalMessage.append(ctr + "/" + count  + " ").append(word).append(" ");
                    }
                }
            }
        } else {
            // Return original message
            return message_str;
        }

        // Return split messages
        return finalMessage.toString();
    }
}
