package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by EDN on 1/15/2018.
 */

public class JadwalUjian implements Serializable {
    @SerializedName("id")
    @Expose
    private String infoUjianId;
    @SerializedName("tanggal")
    @Expose
    private long tanggal;
    @SerializedName("ruang")
    @Expose
    private String ruang;
    @SerializedName("jam")
    @Expose
    private String jam;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("is_penguji")
    @Expose
    private int is_penguji;
    @SerializedName("is_ketua")
    @Expose
    private int is_ketua;
    @SerializedName("nama")
    @Expose
    private String namaMahasiswa;
    @SerializedName("judul_skripsi")
    @Expose
    private String judulSkripsi;

    public String getInfoUjianId() {
        return infoUjianId;
    }

    public void setInfoUjianId(String infoUjianId) {
        this.infoUjianId = infoUjianId;
    }

    public long getTanggal() {
        return tanggal;
    }

    public void setTanggal(long tanggal) {
        this.tanggal = tanggal;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public int getIs_penguji() {
        return is_penguji;
    }

    public void setIs_penguji(int is_penguji) {
        this.is_penguji = is_penguji;
    }

    public int getIs_ketua() {
        return is_ketua;
    }

    public void setIs_ketua(int is_ketua) {
        this.is_ketua = is_ketua;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getJudulSkripsi() {
        return judulSkripsi;
    }

    public void setJudulSkripsi(String judulSkripsi) {
        this.judulSkripsi = judulSkripsi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
