package skripsi.edwin.filkomapps.dbPersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.model.orm.CompareNilai;
import skripsi.edwin.filkomapps.model.orm.DetailNilai;
import skripsi.edwin.filkomapps.model.orm.Indikator;
import skripsi.edwin.filkomapps.model.orm.KomponenIndikatorName;
import skripsi.edwin.filkomapps.model.orm.KomponenWithIndikator;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.NilaiDosenName;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;
import skripsi.edwin.filkomapps.model.orm.RataRataKomponen;
import skripsi.edwin.filkomapps.model.orm.StatusKelulusan;

@Dao
public interface PenilaianDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertKomponen(List<DataKomponen> komponen);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIndikator(List<Indikator> indikator);

    @Query("SELECT k.komponen AS namaKomponen, i.indikator AS namaIndikator" +
            " FROM komponen k, indikator i WHERE i.idKomponen=k.id AND i.id=:condition")
    KomponenIndikatorName getKomponen(String condition);

    @Query("SELECT * FROM komponen WHERE id<>:id")
    List<DataKomponen> getKomponenById(String id);



    @Query("SELECT n.* FROM nilai n, indikator i WHERE i.id=n.indikatorId " +
            "AND i.idKomponen=:id AND n.idDosen=:idDosen")
    List<Nilai> checkNilai(String id, String idDosen);

    @Query("SELECT * FROM nilai  WHERE  idDosen=:idDosen")
    List<Nilai> getNilai(String idDosen);

    @Query("SELECT * FROM nilai  WHERE  indikatorId=:id")
    List<Nilai> getNilaiByIndikator(String id);

    @Query("SELECT m.nama AS namaDosen, n.nilai FROM nilai n, majelis m  " +
            "WHERE n.idDosen=m.id AND n.indikatorId=:id ORDER BY m.peran ASC")
    List<NilaiDosenName> getNilaiDosenName(String id);

    @Query("SELECT n.indikatorId,i.idKomponen, n.nilai AS penilai1, (SELECT nilai FROM nilai WHERE " +
            "indikatorId=n.indikatorId AND idDosen=:idDosen2) AS penilai2 FROM nilai n, indikator i WHERE" +
            " i.id=n.indikatorId AND n.idDosen=:idDosen1" +
            " AND n.nilai IS NOT NULL AND (SELECT nilai FROM nilai WHERE indikatorId=n.indikatorId " +
            "AND idDosen=:idDosen2) IS NOT NULL AND i.idKomponen<>6")
    List<CompareNilai> getCompareNilai(String idDosen1, String idDosen2);


    @Query("SELECT n.* FROM nilai n, indikator i,komponen k  WHERE n.indikatorId=i.id " +
            "AND i.idKomponen=k.id AND n.indikatorId=:idIndikator AND n.idDosen=:idDosen ORDER BY k.komponen ASC")
    Nilai getNilaiIndikator(String idIndikator,String idDosen);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertNilai(Nilai nilais);

    @Update(onConflict = OnConflictStrategy.FAIL)
    void  updateNilai(Nilai nilais);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStatus(List<StatusKelulusan> statusKelulusans);

    @Query("SELECT *  FROM status_kelulusan WHERE status=:condition")
    StatusKelulusan getStatusKelulusanSpesific(String condition);
    @Query("SELECT * FROM status_kelulusan")
    List<StatusKelulusan> getStatusKelulusan();
    @Transaction
    @Query("SELECT i.id AS idIndikator ,i.indikator,k.id AS idKomponen, k.komponen, i.keterangan," +
            "n.nilai FROM komponen k, nilai n, indikator i " +
            "WHERE n.indikatorId=i.id AND k.id=i.idKomponen AND n.idDosen=:idDosen ORDER BY k.id,n.indikatorId ASC")
    List<NilaiSaya> getSummaryNilai(String idDosen);
    @Query("SELECT i.id AS idIndikator ,i.indikator,k.id AS idKomponen, k.komponen, i.keterangan," +
            "m.nama AS namaDosen,n.nilai FROM komponen k, nilai n, indikator i, majelis m " +
            "WHERE n.indikatorId=i.id AND k.id=i.idKomponen AND m.id=n.idDosen AND m.peran=:peran " +
            "ORDER BY k.id,n.indikatorId ASC")
    List<DetailNilai> getSummaryNilaiMajelis(String peran);

    @Query("SELECT m.peran,m.isHadir,k.id, k.komponen, AVG(n.nilai) as avg  FROM komponen k, indikator i, nilai n,majelis m WHERE n.indikatorId=i.id " +
            "AND i.idKomponen=k.id AND m.id=n.idDosen AND (m.peran='pembimbing1' OR m.peran='pembimbing2') GROUP BY m.peran,k.id")
    List<RataRataKomponen>getRataRataKomponenPembimbing();
    @Query("SELECT m.peran,m.isHadir,k.id, k.komponen, AVG(n.nilai) as avg  FROM komponen k, indikator i, nilai n,majelis m WHERE n.indikatorId=i.id " +
            "AND i.idKomponen=k.id AND m.id=n.idDosen AND (m.peran='penguji1' OR m.peran='penguji2') GROUP BY m.peran,k.id")
    List<RataRataKomponen>getRataRataKomponenPenguji();

    @Query("SELECT m.peran,m.isHadir,k.id, k.komponen, AVG(n.nilai) as avg  FROM komponen k, indikator i, nilai n,majelis m WHERE n.indikatorId=i.id " +
            "AND i.idKomponen=k.id AND m.id=n.idDosen AND (m.peran=:peran1 OR m.peran=:peran2) AND k.id=2 GROUP BY m.peran,k.id")
    List<RataRataKomponen>getRataRataPenulisan(String peran1,String peran2);

    @Query("SELECT AVG(n.nilai)  FROM komponen k, indikator i, nilai n,majelis m WHERE n.indikatorId=i.id " +
            "AND i.idKomponen=k.id AND m.id=n.idDosen AND (m.peran='penguji1' OR m.peran='penguji2') AND k.id=6 GROUP BY m.peran,k.id")
    List<Double> getNilaiPublikasi();

    @Query("DELETE FROM komponen")
    void deleteKomponen();
    @Query("DELETE FROM indikator")
    void deleteIndikator();
    @Query("DELETE FROM nilai")
    void deleteNilai();
    @Query("DELETE FROM status_kelulusan")
    void deleteStatus();

}
