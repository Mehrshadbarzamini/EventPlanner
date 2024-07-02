package com.app.eventplanner.utils;

import com.app.eventplanner.LoginActivity;
import com.app.eventplanner.MainActivity;
import com.app.eventplanner.TasksActivity;

public enum ActivityEnum {
    LOGIN(LoginActivity.class),
    MAIN(MainActivity.class),
    TASKS(TasksActivity.class);

    private final Class<?> activityClass;

    ActivityEnum(Class<?> activityClass) {
        this.activityClass = activityClass;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }
}
