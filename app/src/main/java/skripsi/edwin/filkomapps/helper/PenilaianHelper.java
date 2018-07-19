package skripsi.edwin.filkomapps.helper;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import skripsi.edwin.filkomapps.dbPersistance.AppDatabase;
import skripsi.edwin.filkomapps.model.DataKomponen;
import skripsi.edwin.filkomapps.model.orm.CompareNilai;
import skripsi.edwin.filkomapps.model.orm.DetailNilai;
import skripsi.edwin.filkomapps.model.orm.KomponenIndikatorName;
import skripsi.edwin.filkomapps.model.orm.Nilai;
import skripsi.edwin.filkomapps.model.orm.NilaiDosenName;
import skripsi.edwin.filkomapps.model.orm.NilaiSaya;
import skripsi.edwin.filkomapps.model.orm.RataRataKomponen;
import skripsi.edwin.filkomapps.model.orm.StatusKelulusan;

public class PenilaianHelper {
    public static KomponenIndikatorName getKomponenIndikator(final Context context, final String condition) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, KomponenIndikatorName>() {
                @Override
                protected KomponenIndikatorName doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getKomponen(condition);
                }
            }.execute();
            return (KomponenIndikatorName) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<DataKomponen> getKomponenById(final Context context, final String id) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<DataKomponen>>() {
                @Override
                protected List<DataKomponen> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getKomponenById(id);
                }
            }.execute();
            return (List<DataKomponen>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void insertKomponen(final Context context, final List<DataKomponen> dataKomponen){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).penilaianDao().insertKomponen(dataKomponen);
                for (int i=0; i<dataKomponen.size(); i++){
                    AppDatabase.dbInstance(context).penilaianDao().insertIndikator(dataKomponen.get(i).getIndikators());
                }
                return null;
            }
        }.execute();
    }
    public static List<Nilai> checkPenilaianSaya(final Context context, final String idDosen, final String idKomponen) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Nilai>>() {
                @Override
                protected List<Nilai> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().checkNilai(idKomponen,idDosen);
                }
            }.execute();
            return (List<Nilai>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Nilai> getPenilaian(final Context context, final String idDosen) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Nilai>>() {
                @Override
                protected List<Nilai> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getNilai(idDosen);
                }
            }.execute();
            return (List<Nilai>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Nilai> getPenilaianByIndikator(final Context context, final String id) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Nilai>>() {
                @Override
                protected List<Nilai> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getNilaiByIndikator(id);
                }
            }.execute();
            return (List<Nilai>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<NilaiDosenName> getNilaiDosenName(final Context context, final String id) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<NilaiDosenName>>() {
                @Override
                protected List<NilaiDosenName> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getNilaiDosenName(id);
                }
            }.execute();
            return (List<NilaiDosenName>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<CompareNilai> getPenilaian(final Context context, final String idDosen,final String idDosen2) {

        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<CompareNilai>>() {
                @Override
                protected List<CompareNilai> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getCompareNilai(idDosen,idDosen2);
                }
            }.execute();
            return (List<CompareNilai>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void insertNilai(final Context context, final Nilai nilais){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).penilaianDao().insertNilai(nilais);
                return null;
            }
        }.execute();
    }
    public static void updateNilai(final Context context, final Nilai nilais){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).penilaianDao().updateNilai(nilais);
                return null;
            }
        }.execute();
    }
    public static Nilai getIndikatorNilai(final Context context, final String idIndikator, final String idDosen){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, Nilai>() {
                @Override
                protected Nilai doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getNilaiIndikator(idIndikator,idDosen);
                }
            }.execute();
            return (Nilai) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void insertStatusKelulusan(final Context context, final List<StatusKelulusan>status){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).penilaianDao().insertStatus(status);
                return null;
            }
        }.execute();
    }
    public static StatusKelulusan getStatusKelulusan(final Context context, final String condition){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, StatusKelulusan>() {
                @Override
                protected StatusKelulusan doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getStatusKelulusanSpesific(condition);
                }
            }.execute();
            return (StatusKelulusan) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<StatusKelulusan> getStatusKelulusan(final Context context){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<StatusKelulusan>>() {
                @Override
                protected List<StatusKelulusan> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getStatusKelulusan();
                }
            }.execute();
            return (List<StatusKelulusan>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<NilaiSaya> getNilaiSaya(final Context context, final String idDosen){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<NilaiSaya>>() {
                @Override
                protected List<NilaiSaya> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getSummaryNilai(idDosen);
                }
            }.execute();
            return (List<NilaiSaya>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<DetailNilai> getSummaryNilaiMajelis(final Context context, final String peran){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<DetailNilai>>() {
                @Override
                protected List<DetailNilai> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getSummaryNilaiMajelis(peran);
                }
            }.execute();
            return (List<DetailNilai>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<RataRataKomponen>getRataRataPembimbing(final Context context){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<RataRataKomponen>>() {
                @Override
                protected List<RataRataKomponen> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getRataRataKomponenPembimbing();
                }
            }.execute();
            return (List<RataRataKomponen>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<RataRataKomponen>getRataRataPenguji(final Context context){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<RataRataKomponen>>() {
                @Override
                protected List<RataRataKomponen> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getRataRataKomponenPenguji();
                }
            }.execute();
            return (List<RataRataKomponen>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<RataRataKomponen>getRataRataPenulisan(final Context context, final String peran,
                                                             final String peran2){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<RataRataKomponen>>() {
                @Override
                protected List<RataRataKomponen> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getRataRataPenulisan(peran,peran2);
                }
            }.execute();
            return (List<RataRataKomponen>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Double> getNilaiPublikasi(final Context context){
        try {
            AsyncTask asyncTask= new AsyncTask<Void, Void, List<Double>>() {
                @Override
                protected List<Double> doInBackground(Void... voids) {
                    return AppDatabase.dbInstance(context).penilaianDao().getNilaiPublikasi();
                }
            }.execute();
            return (List<Double>) asyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void deletePenilaian(final Context context){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase.dbInstance(context).penilaianDao().deleteNilai();
                AppDatabase.dbInstance(context).penilaianDao().deleteIndikator();
                AppDatabase.dbInstance(context).penilaianDao().deleteKomponen();
                AppDatabase.dbInstance(context).penilaianDao().deleteStatus();

                return null;
            }
        }.execute();
    }
}
