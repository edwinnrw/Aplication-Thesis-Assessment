package skripsi.edwin.filkomapps.model.orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.io.Serializable;

import skripsi.edwin.filkomapps.model.Dosen;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by EDN on 3/6/2018.
 */
@Entity(indices = {@Index("idDosen"),@Index("indikatorId")},tableName = "nilai",
        primaryKeys = {"idDosen","indikatorId"},foreignKeys ={
    @ForeignKey(entity = Indikator.class,
        parentColumns = "id",
        childColumns = "indikatorId",
        onUpdate = CASCADE,
        onDelete = CASCADE)})
public class Nilai  implements Serializable {

    @SerializedName("id_penilai")
    @Expose
    @NonNull
    private String idDosen;

    @SerializedName("id_indikator")
    @Expose
    @NonNull
    private String indikatorId;

    @SerializedName("nilai")
    @Expose
    private int nilai;


    public String getIndikatorId() {
        return indikatorId;
    }

    public void setIndikatorId(String indikatorId) {
        this.indikatorId = indikatorId;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }


    public String getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(String idDosen) {
        this.idDosen = idDosen;
    }
}
