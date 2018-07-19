package skripsi.edwin.filkomapps.model;

import java.util.HashMap;
import java.util.List;

public class DetailGAP {
    private String namaKomponen;
    private String namaIndikator;
    private List<HashMap<String,String>>nilai;

    public String getNamaKomponen() {
        return namaKomponen;
    }

    public void setNamaKomponen(String namaKomponen) {
        this.namaKomponen = namaKomponen;
    }

    public String getNamaIndikator() {
        return namaIndikator;
    }

    public void setNamaIndikator(String namaIndikator) {
        this.namaIndikator = namaIndikator;
    }

    public List<HashMap<String, String>> getNilai() {
        return nilai;
    }

    public void setNilai(List<HashMap<String, String>> nilai) {
        this.nilai = nilai;
    }
}
