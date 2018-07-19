package skripsi.edwin.filkomapps.helper;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import skripsi.edwin.filkomapps.dbPersistance.AppDatabase;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.model.orm.Indikator;
import skripsi.edwin.filkomapps.model.orm.Majelis;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.Notifikasi;
import skripsi.edwin.filkomapps.preference.PrefUtil;


/**
 * Created by EDN on 3/8/2018.
 */

public class ReceiveHelper {

    public static void insertNilaiTemp(Context context, String nilai_ujian){
            insertNilaiUjian(context,nilai_ujian);

    }
    public static void insertNilaiUjian(Context context,String json){
        try {
            JSONObject jsonResponse = new JSONObject("{nilai:"+json+"}");
            JSONArray jsonArray=jsonResponse.getJSONArray("nilai");
            List<Nilai>nilaiList=new ArrayList<>();
            for (int i=0; i<jsonArray.length();i++){
                Nilai nilai=new Nilai();
                JSONObject jsonResponse1 = new JSONObject(String.valueOf(jsonArray.get(i)));
                nilai.setIndikatorId(jsonResponse1.optString("id_indikator"));
                nilai.setIdDosen(jsonResponse1.optString("id_penilai"));
                nilai.setNilai(jsonResponse1.optInt("nilai"));
                AppDatabase.dbInstance(context).penilaianDao().insertNilai(nilai);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static  void  updateNilai(Context context,String json){
        try {
            Log.d("updatePoll",json);
            JSONObject jsonResponse = new JSONObject("{nilai_update:"+json+"}");
            JSONObject jsonObject=jsonResponse.getJSONObject("nilai_update");
            Nilai nilai=new Nilai();
            nilai.setIndikatorId(jsonObject.optString("id_indikator"));
            nilai.setIdDosen(jsonObject.optString("id_penilai"));
            nilai.setNilai(+jsonObject.optInt("nilai"));
            AppDatabase.dbInstance(context).penilaianDao().updateNilai(nilai);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void insertNotifikasi(RemoteMessage remoteMessage,Context context){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
        Notifikasi notifikasi=new Notifikasi();
        notifikasi.setBody(remoteMessage.getData().get("detail_notif"));
        notifikasi.setJenisNotif(remoteMessage.getData().get("jenis_notif"));
        notifikasi.setTime(time.format(cal.getTime()));
        notifikasi.setDate(date.format(cal.getTime()));
        AppDatabase.dbInstance(context).notifDao().insertNotif(notifikasi);
    }
    public  static  void  finishUjian(Context context){
        PenilaianHelper.deletePenilaian(context);
        MajelisHelper.deleteMajelis(context);
        PrefUtil.destroyPrefUjian(context);
    }


}
