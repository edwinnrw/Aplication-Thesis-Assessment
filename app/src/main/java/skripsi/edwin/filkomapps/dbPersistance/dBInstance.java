package skripsi.edwin.filkomapps.dbPersistance;

import android.arch.persistence.room.Room;
import android.content.Context;

public class dBInstance {
    private  static  AppDatabase db;
    public static AppDatabase dbInstance(Context context){
        if (db==null){
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database-name").build();
        }
        return db;
    }
}
