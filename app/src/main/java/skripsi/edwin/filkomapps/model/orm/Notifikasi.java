package skripsi.edwin.filkomapps.model.orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "notifikasi")
public class Notifikasi  {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String body;
    private String time;
    private String date;
    private String jenisNotif;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJenisNotif() {
        return jenisNotif;
    }

    public void setJenisNotif(String jenisNotif) {
        this.jenisNotif = jenisNotif;
    }


}
