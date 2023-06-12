package com.example.todolistandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Options extends AppCompatActivity implements OnItemSelectedListener {
    int taillePolice = 15;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_layout);

        Button appliquer = findViewById(R.id.button);

        appliquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Options.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Switch themeSwitch = findViewById(R.id.LightDark);

        themeSwitch.setChecked(PreferenceUtil.isDarkModeEnabled(this));

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                PreferenceUtil.saveThemeMode(Options.this, isChecked);
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        Spinner spinnerTaillePolice = findViewById(R.id.spinnerTaillePolice);

        spinnerTaillePolice.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0) {
                    taillePolice = 12;
                    View rootView = getWindow().getDecorView().getRootView();
                    appliquerTaillePolice(rootView, taillePolice);
                } else if (id == 1) {
                    taillePolice = 15;
                    View rootView = getWindow().getDecorView().getRootView();
                    appliquerTaillePolice(rootView, taillePolice);
                } else {
                    taillePolice = 18;
                    View rootView = getWindow().getDecorView().getRootView();
                    appliquerTaillePolice(rootView, taillePolice);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO
    }

    private void appliquerTaillePolice(View view, float taillePolice) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = viewGroup.getChildAt(i);
                appliquerTaillePolice(child, taillePolice);
            }
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, taillePolice);
        }
    }

    private int getTaillePolice() {
        return taillePolice;
    }
}