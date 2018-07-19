package skripsi.edwin.filkomapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nohp")
    @Expose
    private String nohp;

    @SerializedName("jurusan")
    @Expose
    private String jurusan;
    @SerializedName("jenjang")
    @Expose
    private String jenjang;
    @SerializedName("prodi")
    @Expose
    private String prodi;
    @SerializedName("skripsi")
    @Expose
    private Skripsi skripsi;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
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

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getJenjang() {
        return jenjang;
    }

    public void setJenjang(String jenjang) {
        this.jenjang = jenjang;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public Skripsi getSkripsi() {
        return skripsi;
    }

    public void setSkripsi(Skripsi skripsi) {
        this.skripsi = skripsi;
    }
}
