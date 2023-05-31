package com.example.lab3_4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewInfo;
    private RadioGroup radioGroupResolutions;
    private RadioButton radioButtonSale;
    private RadioButton radioButtonRent;
    private Button buttonBack;
    private Button buttonNext;

    private String name;
    private String size;
    private String tag;
    private String description;
    private String Author;
    private String Resolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewInfo = findViewById(R.id.textViewInfo);
        radioGroupResolutions = findViewById(R.id.radioGroupResolutions);
        radioButtonSale = findViewById(R.id.radioButtonResolution1);
        radioButtonRent = findViewById(R.id.radioButtonResolution2);
        buttonBack = findViewById(R.id.buttonBack);
        buttonNext = findViewById(R.id.buttonNext);

        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("type");
            size = intent.getStringExtra("area");
            tag = intent.getStringExtra("location");
            String infoText = "Наименование: " + name + "\n" +
                    "Размер: " + size + "\n" +
                    "Тэг: " + tag + "\n";
            textViewInfo.setText(infoText);
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdActivity();
            }
        });
    }

    public void openThirdActivity() {
        EditText editTextPrice = findViewById(R.id.editTextPrice);
        EditText editTextState = findViewById(R.id.editTextState);
        // Получаем отсутствующие данные
        description = editTextPrice.getText().toString();
        Author = editTextState.getText().toString();
        int selectedId = radioGroupResolutions.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButtonResolution1) {
            Resolutions = "1920x1080";
        } else if (selectedId == R.id.radioButtonResolution2) {
            Resolutions = "1024x720";
        }
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("type", name);
        intent.putExtra("area", size);
        intent.putExtra("location", tag);

        intent.putExtra("price", description);
        intent.putExtra("state", Author);
        intent.putExtra("resolution", Resolutions);
        startActivity(intent);
    }
}