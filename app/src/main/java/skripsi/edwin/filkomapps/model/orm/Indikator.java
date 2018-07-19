package skripsi.edwin.filkomapps.model.orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

import skripsi.edwin.filkomapps.model.DataKomponen;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(indices = @Index("idKomponen"), tableName = "indikator",
        foreignKeys = @ForeignKey(entity = DataKomponen.class,
        parentColumns = "id",
        childColumns = "idKomponen",
        onDelete = CASCADE))
public class Indikator implements Serializable {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    private String id;

    @SerializedName("id_komponen")
    @Expose
    private String idKomponen;

    @SerializedName("indikator")
    @Expose
    private String indikator;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    public String getId() {
        return id;
    }

    public String getIdKomponen() {
        return idKomponen;
    }

    public void setIdKomponen(String idKomponen) {
        this.idKomponen = idKomponen;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
