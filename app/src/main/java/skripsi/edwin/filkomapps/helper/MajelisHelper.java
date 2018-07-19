package skripsi.edwin.filkomapps.helper;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import skripsi.edwin.filkomapps.dbPersistance.AppDatabase;
import skripsi.edwin.filkomapps.model.orm.Majelis;

public class MajelisHelper {

    public static List<Majelis> getMajelis(final Context context) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Majelis>>() {
                @Override
                protected List<Majelis> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).majelisDao().getMajelis();
                }
            }.execute();
            return (List<Majelis>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Majelis> getMajelisIsHadir(final Context context) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Majelis>>() {
                @Override
                protected List<Majelis> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).majelisDao().getMajelisHadir();
                }
            }.execute();
            return (List<Majelis>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Majelis getMajelisByPeran(final Context context, final String peran) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, Majelis>() {
                @Override
                protected Majelis doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).majelisDao().getMajelisByPeran(peran);
                }
            }.execute();
            return (Majelis) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Majelis> getMajelisByPeran(final Context context, final String peran, final String peran1) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Majelis>>() {
                @Override
                protected List<Majelis> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).majelisDao().getMajelisByTwoPeran(peran,peran1);
                }
            }.execute();
            return (List<Majelis> ) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void insertMajelis(final Context context, final Majelis majelisData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).majelisDao().insertMajelis(majelisData);
                return null;
            }
        }.execute();
    }

    public static void deleteMajelis(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).majelisDao().deleteMajelis();
                return null;
            }
        }.execute();
    }
}
