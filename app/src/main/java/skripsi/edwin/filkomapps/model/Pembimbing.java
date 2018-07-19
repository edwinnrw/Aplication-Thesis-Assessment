package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pembimbing {
    @SerializedName("id")
    @Expose
    private String idDosen;
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(String idDosen) {
        this.idDosen = idDosen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
