package skripsi.edwin.filkomapps.helper;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import skripsi.edwin.filkomapps.dbPersistance.AppDatabase;
import skripsi.edwin.filkomapps.model.orm.Notifikasi;

public class NotifHelper {
    public static List<Notifikasi> getNotifikasi(final Context context) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Notifikasi>>() {
                @Override
                protected List<Notifikasi> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).notifDao().getNotifikasi();
                }
            }.execute();
            return (List<Notifikasi>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void insertNotif(final Context context, final Notifikasi notifikasi) {
        AppDatabase.dbInstance(context).notifDao().insertNotif(notifikasi);

    }
}
