package com.nguyendinhdoan.list_name_java.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.nguyendinhdoan.list_name_java.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class SharedPref {

    private static final String APP_NAME_PREF = "list-name-java";

    private SharedPreferences sharedPreferences;

    public SharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_NAME_PREF,
                Context.MODE_PRIVATE);
    }

    public void saveUser(User user) {
        String key = Calendar.getInstance().getTime() + user.getName();
        sharedPreferences.edit().putString(key, user.getName()).apply();
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        Map<String, ?> data = sharedPreferences.getAll();

        for (Object value : data.values()) {
            String name = (String) value;
            userList.add(new User(name));
        }

        return userList;
    }
}
