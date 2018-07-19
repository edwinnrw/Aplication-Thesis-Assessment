package skripsi.edwin.filkomapps.model;

import java.io.Serializable;

public class GAP implements Serializable{
    private String idKomponen;
    private String idIndikator;

    public String getIdKomponen() {
        return idKomponen;
    }

    public void setIdKomponen(String idKomponen) {
        this.idKomponen = idKomponen;
    }

    public String getIdIndikator() {
        return idIndikator;
    }

    public void setIdIndikator(String idIndikator) {
        this.idIndikator = idIndikator;
    }
}
