package com.example.jimmymerilinenlabb4vg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextGuess;
    private TextView textViewCountdown;
    private TextView textViewTheWord;
    private ImageView imageView;
    private TextView textViewShowAll;
    private final int[] pic = {R.drawable.hangman_1, R.drawable.hangman_2, R.drawable.hangman_3,
            R.drawable.hangman_4, R.drawable.hangman_5, R.drawable.hangman_6, R.drawable.hangman_7,
            R.drawable.hangman_8, R.drawable.hangman_9, R.drawable.hangman_10, R.drawable.hangman_11};
    private int i = 1;
    private int sum;
    private String theWord;
    private String used = "";
    private char[] charArray;
    private ArrayList<String> allWords = new ArrayList<>();
    private ArrayList<String> charUsedLetterArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextGuess = findViewById(R.id.editTextGuess);
        imageView = findViewById(R.id.imageViewHangman1);
        textViewCountdown = findViewById(R.id.textViewCountdown);
        textViewTheWord = findViewById(R.id.textViewTheWord);
        textViewShowAll = findViewById(R.id.textViewShowAll);

        Scanner data_in = new Scanner(getResources().openRawResource(R.raw.random_words));
        while (data_in.hasNext()) {
            allWords.add(data_in.nextLine());

        }
        data_in.close();
        playing();

    }

    public void playing() {
        Random random = new Random();
        int index = random.nextInt(allWords.size());
        theWord = allWords.get(index);

        charArray = theWord.toCharArray();
        for (int i = 0; i < charArray.length ; i++) {
            charArray[i] = '-';
        }
        textViewTheWord.setText(String.valueOf(charArray));

    }

    public void changeImage() {

        if (i < 10) {
            imageView.setImageResource(pic[i]);
            i++;
            int num = Integer.parseInt(String.valueOf(textViewCountdown.getText()));
            sum = num - 1;
            textViewCountdown.setText(Integer.toString(sum));
        } else {
            String lose = getString(R.string.lose);
            Intent resultActivityIntent =  new Intent(this, ResultActivity.class);
            resultActivityIntent.putExtra("MESSAGE", lose);
            startActivity(resultActivityIntent);

        }

    }

    public void showUsedLetters(String c) {
        int index = theWord.indexOf(String.valueOf(c));

        if (charUsedLetterArray.contains(c)) {
            Toast.makeText(SecondActivity.this, getString(R.string.already_used)+c, Toast.LENGTH_SHORT).show();

        } else {
            charUsedLetterArray.add(c);
            used += c + ", ";
            textViewShowAll.setText(used);
            if (index < 0) {
                changeImage();
            }
            while (index >= 0) {
                charArray[index] = theWord.charAt(index);
                index = theWord.indexOf(String.valueOf(c), index + 1);
            }
            textViewTheWord.setText(String.valueOf(charArray));
            if (String.valueOf(charArray).equals(theWord)) {
                String win = getString(R.string.win);
                String word = getString(R.string.word) + theWord;
                String tries = getString(R.string.tries) + textViewCountdown.getText();
                Intent resultActivityIntent =  new Intent(this, ResultActivity.class);
                resultActivityIntent.putExtra("MESSAGE", win);
                resultActivityIntent.putExtra("MES", word);
                resultActivityIntent.putExtra("TRIES", tries);
                startActivity(resultActivityIntent);
            }

        }

    }

    public void pressToInfo(View view) {
        Intent infoActivityIntent = new Intent(this, InfoActivity.class);
        startActivity(infoActivityIntent);
    }

    public void pressToGoBack(View view) {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    public void pressGuess(View view) { //BARA EN TEST
        String guess = editTextGuess.getText().toString();
        if (guess.trim().equals("")) {
            Toast.makeText(this, R.string.input_letter, Toast.LENGTH_SHORT).show();
            return;
        }
        showUsedLetters(guess);
        editTextGuess.setText("");
    }
}