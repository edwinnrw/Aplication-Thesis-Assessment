package skripsi.edwin.filkomapps.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.util.List;

import skripsi.edwin.filkomapps.model.orm.Indikator;


@Entity(tableName = "komponen")
public class DataKomponen implements Serializable {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    private String id;

    @SerializedName("komponen")
    @Expose
    private String komponen;

    @Ignore
    @SerializedName("indikator")
    @Expose
    private   List<Indikator>indikators;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKomponen() {
        return komponen;
    }

    public void setKomponen(String komponen) {
        this.komponen = komponen;
    }

    public List<Indikator> getIndikators() {
        return indikators;
    }

    public void setIndikators(List<Indikator> indikators) {
        this.indikators = indikators;
    }
}
