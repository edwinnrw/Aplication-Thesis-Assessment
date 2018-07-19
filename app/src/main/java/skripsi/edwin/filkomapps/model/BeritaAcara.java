package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeritaAcara {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status_lulus")
    @Expose
    private String statusLulus;
    @SerializedName("nilai_akhir")
    @Expose
    private String nilaiAkhir;
    @SerializedName("catatan")
    @Expose
    private String catatan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusLulus() {
        return statusLulus;
    }

    public void setStatusLulus(String statusLulus) {
        this.statusLulus = statusLulus;
    }

    public String getNilaiAkhir() {
        return nilaiAkhir;
    }

    public void setNilaiAkhir(String nilaiAkhir) {
        this.nilaiAkhir = nilaiAkhir;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
