package skripsi.edwin.filkomapps.model.orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;



@Entity(tableName = "majelis")
public class Majelis {
    @PrimaryKey
    @NonNull
    private String id;
    private String nama;
    private String peran;
    private String isHadir;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPeran() {
        return peran;
    }

    public void setPeran(String peran) {
        this.peran = peran;
    }

    public String getIsHadir() {
        return isHadir;
    }

    public void setIsHadir(String isHadir) {
        this.isHadir = isHadir;
    }
}
