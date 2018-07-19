package skripsi.edwin.filkomapps.dbPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import skripsi.edwin.filkomapps.model.orm.Notifikasi;
import skripsi.edwin.filkomapps.model.orm.User;

@Dao
public interface NotifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNotif(Notifikasi notifikasi);

    @Query("SELECT * FROM notifikasi")
    List<Notifikasi> getNotifikasi();
}
