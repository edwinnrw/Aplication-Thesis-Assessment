package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataUjian {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ruang")
    @Expose
    private String ruang;
    @SerializedName("tanggal")
    @Expose
    private long tanggal;
    @SerializedName("jam")
    @Expose
    private String jam;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("mahasiswa")
    @Expose
    private Mahasiswa mahasiswa;

    @SerializedName("pembimbing")
    @Expose
    private List<Pembimbing> pembimbing;

    @SerializedName("penguji")
    @Expose
    private List<Penguji> penguji;

    @SerializedName("berita_acara")
    @Expose
    private BeritaAcara beritaAcara;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public long getTanggal() {
        return tanggal;
    }

    public void setTanggal(long tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public List<Pembimbing> getPembimbing() {
        return pembimbing;
    }

    public void setPembimbing(List<Pembimbing> pembimbing) {
        this.pembimbing = pembimbing;
    }

    public List<Penguji> getPenguji() {
        return penguji;
    }

    public void setPenguji(List<Penguji> penguji) {
        this.penguji = penguji;
    }

    public BeritaAcara getBeritaAcara() {
        return beritaAcara;
    }

    public void setBeritaAcara(BeritaAcara beritaAcara) {
        this.beritaAcara = beritaAcara;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
