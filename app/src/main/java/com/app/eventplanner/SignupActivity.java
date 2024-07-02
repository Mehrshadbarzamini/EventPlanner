package com.app.eventplanner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonSignup;
    private Button buttonCancel;
    private Button buttonLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Ensure this points to the correct layout file

        // Fetching the stored data from the SharedPreference
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignup = findViewById(R.id.buttonSignup);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (isValid(username, password)) {
                    if (isUsernameTaken(username)) {
                        Toast.makeText(SignupActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        saveUserCredentials(username, password);
                        Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUsername.setText("");
                editTextPassword.setText("");
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValid(String username, String password) {
        String usernamePattern = "^[a-zA-Z0-9@.\\-+/%]{3,}$";
        String passwordPattern = "^[a-zA-Z0-9]{3,}$";
        return username.matches(usernamePattern) && password.matches(passwordPattern);
    }

    private boolean isUsernameTaken(String username) {
        String storedPassword = sharedPreferences.getString(username, null);
        return storedPassword != null;
    }

    private void saveUserCredentials(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, password);
        editor.apply();
    }
}
