package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class Dosen {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("bidang_minor")
    @Expose
    private List<Bidang> minor;
    @SerializedName("bidang_mayor")
    @Expose
    private List<Bidang> mayor;
    @SerializedName("foto")
    @Expose
    private String img;
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

    public List<Bidang> getMinor() {
        return minor;
    }

    public void setMinor(List<Bidang> minor) {
        this.minor = minor;
    }

    public List<Bidang> getMayor() {
        return mayor;
    }

    public void setMayor(List<Bidang> mayor) {
        this.mayor = mayor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
