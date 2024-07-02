package com.app.eventplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup radioGroupOpponent, radioGroupPlayer;
    private RadioButton radioButtonCPU, radioButtonHuman, radioButtonX, radioButtonO;
    private Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        radioGroupOpponent = findViewById(R.id.radioGroupOpponent);
        radioGroupPlayer = findViewById(R.id.radioGroupPlayer);
        radioButtonCPU = findViewById(R.id.radioButtonCPU);
        radioButtonHuman = findViewById(R.id.radioButtonHuman);
        radioButtonX = findViewById(R.id.radioButtonX);
        radioButtonO = findViewById(R.id.radioButtonO);
        buttonStartGame = findViewById(R.id.buttonStartGame);

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

                // Pass the selected options to MainActivity
                intent.putExtra("opponent", radioButtonCPU.isChecked() ? "CPU" : "Human");
                intent.putExtra("player", radioButtonX.isChecked() ? "X" : "O");

                startActivity(intent);
                finish();
            }
        });
    }
}
