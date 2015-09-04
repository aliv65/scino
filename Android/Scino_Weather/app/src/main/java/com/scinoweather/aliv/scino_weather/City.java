package com.scinoweather.aliv.scino_weather;

import android.app.Activity;
import android.content.SharedPreferences;

public class City {

    SharedPreferences prefs;

    public City(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // Если пользователь не выбрал город, на умолчанию
    // будем показывать погоду на Москву
    String getCity(){
        return prefs.getString("city", "Moscow, RU");
    }

    void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }
}