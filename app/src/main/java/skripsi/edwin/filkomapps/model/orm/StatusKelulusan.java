package skripsi.edwin.filkomapps.model.orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "status_kelulusan")
public class StatusKelulusan  implements Serializable {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("status_kelulusan")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
