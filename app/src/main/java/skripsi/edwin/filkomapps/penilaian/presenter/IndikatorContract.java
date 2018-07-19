package skripsi.edwin.filkomapps.penilaian.presenter;

import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.model.DataKomponen;

public class IndikatorContract {
    public interface View{

        void setDeskripsi(String des);
        void setKategoriNilai(String nilai);
        void setNilaiTempt(int nilai);
    }
    public  interface Presenter{
        void getDeskripsi(String komponen,int nilai,int indikator);
        void getNilai(int nilai);
        void saveNilaiTemp(DataKomponen dataKomponen, List<HashMap<String, String>> nilai);
        void getNilaiTemp(String komponen);

    }
}
