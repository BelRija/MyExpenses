package com.example.yulia_000.myexpenses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yulia_000 on 09.01.2018.
 */

public class KontostandActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnKontostand;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.einnahme_layout);

        btnKontostand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KontostandActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }
}
