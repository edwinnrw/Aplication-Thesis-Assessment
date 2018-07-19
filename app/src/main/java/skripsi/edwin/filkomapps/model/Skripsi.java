package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Skripsi {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("judul_skripsi")
    @Expose
    private String judulSkripsi;
    @SerializedName("asal_judul")
    @Expose
    private String asalJudul;
    @SerializedName("bidang_penelitian")
    @Expose
    private String bidang;
    @SerializedName("tipe_penelitian")
    @Expose
    private String tipe_penelitian;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("matkul_pendukung")
    @Expose
    private List<Matkul> matkulPendukung;
    @SerializedName("nilai_semhas")
    @Expose
    private String semhas;
    @SerializedName("dokumen")
    @Expose
    private String dokumen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudulSkripsi() {
        return judulSkripsi;
    }

    public void setJudulSkripsi(String judulSkripsi) {
        this.judulSkripsi = judulSkripsi;
    }

    public String getAsalJudul() {
        return asalJudul;
    }

    public void setAsalJudul(String asalJudul) {
        this.asalJudul = asalJudul;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getTipe_penelitian() {
        return tipe_penelitian;
    }

    public void setTipe_penelitian(String tipe_penelitian) {
        this.tipe_penelitian = tipe_penelitian;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Matkul> getMatkulPendukung() {
        return matkulPendukung;
    }

    public void setMatkulPendukung(List<Matkul> matkulPendukung) {
        this.matkulPendukung = matkulPendukung;
    }

    public String getDokumen() {
        return dokumen;
    }

    public void setDokumen(String dokumen) {
        this.dokumen = dokumen;
    }

    public String getSemhas() {
        return semhas;
    }

    public void setSemhas(String semhas) {
        this.semhas = semhas;
    }
}
