package com.app.eventplanner.utils;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityChanger {

    public static void changeActivity(Context currentContext, ActivityEnum activityEnum, ArrayList<String> data) {
        Intent intent = new Intent(currentContext, activityEnum.getActivityClass());
        intent.putStringArrayListExtra("data", data);
        currentContext.startActivity(intent);
        if (currentContext instanceof AppCompatActivity) {
            ((AppCompatActivity) currentContext).finish();
        }
    }
}
