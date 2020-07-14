package com.rizky.fauziah.aplikasicustomer.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    //Tag
    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "PamsimasPref";

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setSession(SessionManager.Key key, boolean value) {
        editor.putBoolean(key.toString(), value);
        editor.commit();
    }

    public void setSession(SessionManager.Key key, String value) {
        editor.putString(key.toString(), value);
        editor.commit();
    }

    public void setSession(SessionManager.Key key, int value) {
        editor.putInt(key.toString(), value);
        editor.commit();
    }

    public String getString(SessionManager.Key key) {
        return preferences.getString(key.toString(), null);
    }

    public boolean getBoolean(SessionManager.Key key) {
        return preferences.getBoolean(key.toString(), false);
    }

    public int getInt(SessionManager.Key key) {
        return preferences.getInt(key.toString(), 0);
    }

    public void removeData(SessionManager.Key key) {
        editor.remove(key.toString());
        editor.commit();
    }

    public void clearData() {
        editor.clear();
        editor.commit();
    }

    public enum Key {
        IS_LOGGED_IN,
        USER_DATA
    }
}

