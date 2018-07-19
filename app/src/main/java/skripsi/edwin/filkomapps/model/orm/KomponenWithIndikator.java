package skripsi.edwin.filkomapps.model.orm;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

import skripsi.edwin.filkomapps.model.DataKomponen;

public class KomponenWithIndikator {
    @Embedded
    public DataKomponen dataKomponen;

    @Relation(parentColumn = "id",entityColumn = "idKomponen",entity = Indikator.class)
    public List<Indikator>indikators;
}
