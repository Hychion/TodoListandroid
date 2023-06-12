package com.example.todolistandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todolistandroid.Options;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PreferenceUtil.isDarkModeEnabled(this)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_main);
        Button add = findViewById(R.id.add_todolist);
        Button option = findViewById(R.id.options);

        float nouvelleTaillePolice = Options.getTaillePolice();

        ViewGroup rootView = findViewById(android.R.id.content);
        changeTextSizeRecursive(rootView, nouvelleTaillePolice);

// Méthode récursive pour changer la taille de police des TextView

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez un Intent pour démarrer une nouvelle activité
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Options.class);
                startActivity(intent);
            }
        });

    }

    private void changeTextSizeRecursive(View view, float textSize) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = viewGroup.getChildAt(i);
                changeTextSizeRecursive(childView, textSize);
            }
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
    }
}