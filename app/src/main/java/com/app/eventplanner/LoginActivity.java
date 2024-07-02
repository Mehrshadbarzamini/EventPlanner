package com.app.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonCancel;
    private Button buttonSignup;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonSignup = findViewById(R.id.buttonSignup);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (isValid(username, password) && isValidCredentials(username, password)) {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    finish();
                } else if (!isValid(username, password)) {
                    Toast.makeText(LoginActivity.this, "Regex didn't match", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
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

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValid(String username, String password) {
        String usernamePattern = "^[a-zA-Z0-9._%+\\-@]{3,}$";
        String passwordPattern = "^[a-zA-Z0-9]{3,}$";
        return username.matches(usernamePattern) && password.matches(passwordPattern);
    }

    private boolean isValidCredentials(String username, String password) {
        String storedPassword = sharedPreferences.getString(username, null);
        return storedPassword != null && storedPassword.equals(password);
    }

    private static class UserCredentials {
        private final String username;
        private final String password;

        public UserCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    @SuppressLint("HardwareIds")
    private String getDeviceUUID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
