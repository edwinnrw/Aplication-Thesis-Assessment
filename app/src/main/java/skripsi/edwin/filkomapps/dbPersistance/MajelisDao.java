package skripsi.edwin.filkomapps.dbPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import skripsi.edwin.filkomapps.model.orm.Majelis;
import skripsi.edwin.filkomapps.model.orm.User;

@Dao
public interface MajelisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMajelis(Majelis majelis);

    @Query("SELECT * FROM majelis")
    List<Majelis> getMajelis();

    @Query("SELECT * FROM majelis WHERE isHadir=1")
    List<Majelis> getMajelisHadir();

    @Query("SELECT * FROM majelis WHERE peran=:peran")
    Majelis getMajelisByPeran(String peran);

    @Query("SELECT * FROM majelis WHERE peran=:peran1 OR peran=:peran2")
    List<Majelis>getMajelisByTwoPeran(String peran1,String peran2);


    @Query("DELETE FROM majelis")
    void deleteMajelis();
}
