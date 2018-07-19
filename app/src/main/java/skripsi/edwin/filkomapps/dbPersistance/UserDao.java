package skripsi.edwin.filkomapps.dbPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import skripsi.edwin.filkomapps.model.orm.User;

@Dao
public interface UserDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertUser(User user);

  @Query("SELECT * FROM user")
  User getUser();

  @Query("DELETE FROM user")
  void deleteUser();
}
