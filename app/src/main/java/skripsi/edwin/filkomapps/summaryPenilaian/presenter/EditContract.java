package skripsi.edwin.filkomapps.summaryPenilaian.presenter;

import java.util.HashMap;

public class EditContract {
    public interface View{

        void setDeskripsi(String deskripsi);
        void setNilai(String nilai);
        void setNilaiTemp(String nilaiTemp, int rbNilai1);
        void showProgress();
        void dismissProgress();
        void updateFail(String message);
        void updateSuccess();
    }
    public  interface Presenter{
        void getDeskripsi(String komponen,int nilai,int indikator);
        void getNilaiTemp(String nilai);
        void getNilai(String nilai);
        void saveNilai(String idIndikator, String idKomponen, HashMap<String, String> nilai);

    }
}
