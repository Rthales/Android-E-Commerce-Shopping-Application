package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClothingCategory extends AppCompatActivity implements View.OnClickListener {
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_category);

        this.nextButton = findViewById(R.id.nextPageBtnClothing);

    }

    @Override
    public void onClick(View v) {
        try {

            if (v.getId() == R.id.nextPageBtnClothing) {
                Intent intent = new Intent(ClothingCategory.this, ClothingActivityTwo.class);
                startActivity(intent);

            }

        } catch (ActivityNotFoundException exc) {

            Log.d("Error", exc.toString());
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
