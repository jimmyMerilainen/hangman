package com.example.jimmymerilinenlabb4vg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pressToSecond(View view) {

        Intent secondActivityIntent = new Intent(this, SecondActivity.class);
        startActivity(secondActivityIntent);

    }

    public void pressToInfo(View view) {
        Intent infoActivityIntent = new Intent(this, InfoActivity.class);
        startActivity(infoActivityIntent);
    }
}