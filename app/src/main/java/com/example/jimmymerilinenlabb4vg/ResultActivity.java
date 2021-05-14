package com.example.jimmymerilinenlabb4vg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewHowDidItGo;
    private TextView textViewWin;
    private TextView textViewTriesLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewHowDidItGo = findViewById(R.id.textViewHowDidItGo);
        textViewWin = findViewById(R.id.textViewWin);
        textViewTriesLeft = findViewById(R.id.textViewTriesLeft);

        Intent resultActivityIntent = getIntent();
        String message = resultActivityIntent.getStringExtra("MESSAGE");
        String mes = resultActivityIntent.getStringExtra("MES");
        String triesLeft = resultActivityIntent.getStringExtra("TRIES");
        textViewHowDidItGo.setText(message);
        textViewWin.setText(mes);
        textViewTriesLeft.setText(triesLeft);
    }

    public void pressToGoBack(View view) {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    public void pressToInfo(View view) {
        Intent infoActivityIntent = new Intent(this, InfoActivity.class);
        startActivity(infoActivityIntent);
    }

    public void pressToSecond(View view) {
        Intent secondActivityIntent = new Intent(this, SecondActivity.class);
        startActivity(secondActivityIntent);

    }
}