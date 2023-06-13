package com.example.todolistandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtil {
    private static final String THEME_PREFS_NAME = "theme_prefs";
    private static final String THEME_KEY = "theme_key";
    private static final String KEY_TEXT_SIZE = "text_size";

    public static void saveThemeMode(Context context, boolean isDarkMode) {
        SharedPreferences prefs = context.getSharedPreferences(THEME_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(THEME_KEY, isDarkMode);
        editor.apply();
    }

    public static boolean isDarkModeEnabled(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(THEME_PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(THEME_KEY, false);
    }

    public static float getTextSize(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getFloat(KEY_TEXT_SIZE, 16f); // Taille de police par défaut
    }

    public static void setTextSize(Context context, float textSize) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putFloat(KEY_TEXT_SIZE, textSize).apply();
    }
}