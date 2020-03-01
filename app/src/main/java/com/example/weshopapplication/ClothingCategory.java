package com.example.weshopapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ClothingCategory extends AppCompatActivity {
    private Button nextPageBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_category);

        this.nextPageBtn = findViewById(R.id.nextPageC);

        this.nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try {
                    Intent intent = new Intent(ClothingCategory.this, ClothingActivityTwo.class);
                    startActivity(intent);

                } catch (ActivityNotFoundException exc) {
                    Log.d(String.valueOf(R.string.error), exc.toString());
                }
            }
        });

    }

}
