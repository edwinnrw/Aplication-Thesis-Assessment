package skripsi.edwin.filkomapps.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefUtil {

    private static String USER_PREF = "USER_PREF ";
    private static String USER_PREF_UJIAN = "USER_PREF1";

    public static void setIsLogin(Context context,boolean isLogin) {
        SharedPreferences pref=context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("is_login",isLogin);
        editor.apply();
    }
    public static boolean haveNewNotif(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return pref.getBoolean("is_notif",false);
    }
    public static void setHaveNewNotif(Context context,boolean isLogin) {
        SharedPreferences pref=context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("is_notif",isLogin);
        editor.commit();
    }
    public static  boolean getIsLogin(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return pref.getBoolean("is_login",false);
    }
    public static void setIdUjianTemp(Context context,String id){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("id_ujian",id);
        editor.apply();
    }
    public static String getIdUjianTemp(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getString("id_ujian","");
    }
    //Untuk menyimpan kondisi ketikan dapat menilai keseluruhan
    public static boolean getAccessPenilaian(Context context,String id_ujian){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return pref.getBoolean(id_ujian,false);

    }
    public static void setAccessPenilaian(Context context,String id_ujian,boolean isNilai){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean(id_ujian,isNilai);
        editor.apply();
    }
    //

    public static void setIdSkripsiTemp(Context context,String id){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("id_skripsi",id);
        editor.apply();
    }
    public static   String getIdSkripsiTemp(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getString("id_skripsi","");
    }

    //untuk menyimpan kondisi ketika sudah mencubmit nilai
    //tujuan untuk mengatur flow,ketika menekan button menilai
    public static void setIsSubmitNilai(Context context,String id,boolean isSubmitNilai){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor=pref.edit();
        editor.putBoolean(id+"submit",isSubmitNilai);
        editor.apply();
    }
    public static boolean getIsSubmitNilai(Context context,String id){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getBoolean(id+"submit",false);

    }
    public static void setIsKetua(Context context,String isKetua){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("is_ketua",isKetua);
        editor.apply();
    }
    public static String getIsKetua(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getString("is_ketua","");
    }

    public static void setIsPenguji(Context context,String isKetua){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("is_penguji",isKetua);
        editor.apply();
    }
    public static String getIsPenguji(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getString("is_penguji","");
    }

    public static void setNilaiSemhasTemp(Context context,String semhas){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("nilai_semhas",semhas);
        editor.apply();
    }
    public static String getSemhasTemp(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getString("nilai_semhas","");
    }



    //Menyimpan waktu update data terbaru
    public static void setTimeUpdate(Context context,String timeUpdate) {
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("timeUpdate",timeUpdate);
        editor.apply();
    }

    public static String getTimeUpdate(Context context) {
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getString("timeUpdate","");
    }
    //

    //Digunakan untuk dapat sekali merequest data nilai
    //akibat adanya pencegahan nilai masuk ke apps sebelum ujian
    public static void setOnceGetNilai(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean("getNilaiOnce",true);
        editor.apply();
    }
    public static Boolean getOnceNilai(Context context) {
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return  pref.getBoolean("getNilaiOnce",false);
    }
    ///

    //Menghapus pref ujian
    public static void destroyPrefUjian(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        pref.edit().clear().apply();
    }
    public static boolean getIsDoneVerifikasi(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        return pref.getBoolean(getIdSkripsiTemp(context),false);
    }
    public static void setIsDoneVerifikasi(Context context, boolean isLogin) {
        SharedPreferences pref=context.getSharedPreferences(USER_PREF_UJIAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putBoolean(getIdSkripsiTemp(context),isLogin);
        editor.apply();
    }
}
