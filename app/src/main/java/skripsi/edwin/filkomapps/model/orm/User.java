package skripsi.edwin.filkomapps.model.orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nik_nip")
    @Expose
    private String nikNip;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nohp")
    @Expose
    private String nohp;

    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNikNip() {
        return nikNip;
    }

    public void setNikNip(String nik_nip) {
        this.nikNip = nik_nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}
