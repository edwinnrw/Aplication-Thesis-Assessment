package skripsi.edwin.filkomapps.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class TimerPrefUtil {
    private static String TIMER_PREF="TIMER_PREF";

    public static void setMilisLeft(Context context,long timeMillies){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putLong("millisLeft", timeMillies);
        editor.apply();
    }
    public static long getMilisLeft(Context context,long defaultTimerMilis){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        return  pref.getLong("millisLeft",defaultTimerMilis);
    }
    public static void setTimerIsRuniing(Context context,boolean isRunning){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("timerRunning", isRunning);
        editor.apply();
    }
    public static boolean getTimerIsRunning(Context context){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        return  pref.getBoolean("timerRunning",false);
    }
    public static void setTimerIsPause(Context context,boolean isRunning){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("timerPause", isRunning);
        editor.apply();
    }
    public static boolean getTimerIsPause(Context context){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        return  pref.getBoolean("timerPause",false);
    }
    public static void setEndTime(Context context,long endTime){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putLong("endTime",endTime);
        editor.apply();
    }
    public static long getEndTime(Context context){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        return  pref.getLong("endTime",0);
    }
    public static void setRunningTitle(Context context,String title){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("runningTitle",title);
        editor.apply();
    }
    public static String getRunningTitle(Context context){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        return  pref.getString("runningTitle","");
    }
    public static void destroyRunningTitle(Context context,String title){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor=pref.edit();
        editor.remove("runningTitle");
        editor.apply();
    }
    public static void destroyTimeLeftMilis(Context context){
        SharedPreferences pref=context.getSharedPreferences(TIMER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor=pref.edit();
        editor.remove("millisLeft");
        editor.apply();
    }
}
