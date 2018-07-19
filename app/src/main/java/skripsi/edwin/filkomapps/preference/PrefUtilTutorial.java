package skripsi.edwin.filkomapps.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtilTutorial {
    private static String USER_PREF_TUTORIAL = "USER_PREFT";


    public static void setShowCaseNotifikasi(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("show_case_notif",value);
        editor.apply();
    }
    public static Boolean getShowCaseNotif(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("show_case_notif",true);
    }

    public static void setShowCaseNavDrawer(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("show_case_nav",value);
        editor.apply();
    }
    public static Boolean getShowCaseNavDrawer(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("show_case_nav",true);
    }

    public static Boolean getShowCaseJadwalUjian(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("show_case_jadwal",true);
    }
    public static void setShowCaseJadwalUjian(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("show_case_jadwal",value);
        editor.apply();
    }
    public static void setShowCaseDokumen(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("show_case_dokumen",value);
        editor.apply();
    }
    public static Boolean getShowCaseDokumen(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("show_case_dokumen",true);
    }
    public static void setShowCaseVerifikasi(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("show_case_verifikasi",value);
        editor.apply();
    }
    public static Boolean getShowCaseVerifikasi(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("show_case_verifikasi",true);
    }
    public static void setShowCaseMenilai(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("show_case_menilai",value);
        editor.apply();
    }
    public static Boolean getShowCaseMenilai(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("show_case_menilai",true);
    }
    public static void setShowCaseTimer(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("show_case_timer",value);
        editor.apply();
    }
    public static Boolean getShowCaseTimer(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("show_case_timer",true);
    }


    public static void setTutorialRekapPenilain(Context context,boolean value){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("tutorial_rekap",value);
        editor.apply();
    }
    public static Boolean getTutorialRekapPenilain(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_TUTORIAL, Context.MODE_PRIVATE);
        return  pref.getBoolean("tutorial_rekap",true);
    }
}
