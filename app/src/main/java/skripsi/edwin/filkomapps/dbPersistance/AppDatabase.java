package skripsi.edwin.filkomapps.dbPersistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.model.orm.Indikator;
import skripsi.edwin.filkomapps.model.orm.Majelis;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.Notifikasi;
import skripsi.edwin.filkomapps.model.orm.StatusKelulusan;
import skripsi.edwin.filkomapps.model.orm.User;

@Database(entities = {User.class, Notifikasi.class, Majelis.class, DataKomponen.class,
        Indikator.class, Nilai.class, StatusKelulusan.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase db;
    private static final String DATABASENAME = "filkom_apps";

    public static AppDatabase dbInstance(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, AppDatabase.DATABASENAME)
                    .build();

        }
        return db;
    }

    public abstract UserDao userDao();
    public abstract NotifDao notifDao();
    public abstract MajelisDao majelisDao();
    public abstract PenilaianDao penilaianDao();
}
