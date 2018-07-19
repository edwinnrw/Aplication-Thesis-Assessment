package skripsi.edwin.filkomapps.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import skripsi.edwin.filkomapps.dbPersistance.AppDatabase;
import skripsi.edwin.filkomapps.model.orm.User;

public class UserHelper {
    private static User user;

    public static User getUser(final Context context) {
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, User>() {
                @Override
                protected User doInBackground(Void... voids) {
                    UserHelper.user = AppDatabase.dbInstance(context).userDao().getUser();
                    return user;
                }
            }.execute();
            return (User) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void insertUser(final Context context, final User userData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).userDao().insertUser(userData);
                return null;
            }
        }.execute();
    }

    public static void deleteUser(final Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).userDao().deleteUser();
                return null;
            }
        }.execute();
    }
}
