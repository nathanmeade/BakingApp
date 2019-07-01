package com.example.android.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import static com.example.android.bakingapp.ExampleAppWidgetConfig.KEY_BUTTON_TEXT;
import static com.example.android.bakingapp.ExampleAppWidgetConfig.SHARED_PREFS;
//import com.example.widgettest.MainActivity.SHARED_PRES;

public class ExampleAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId: appWidgetIds){
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            SharedPreferences prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            String buttonText = prefs.getString(KEY_BUTTON_TEXT + appWidgetId, "Press me");

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.example_widget);
            remoteViews.setOnClickPendingIntent(R.id.example_widget_button, pendingIntent);
            remoteViews.setCharSequence(R.id.example_widget_button, "setText", buttonText);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    public static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, String string){
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, string);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, String string){
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);
        views.setOnClickPendingIntent(R.id.example_widget_button, pendingIntent);
        String testText = "rickJames";
        views.setCharSequence(R.id.example_widget_button, "setText", string);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
