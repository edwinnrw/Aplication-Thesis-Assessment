package skripsi.edwin.filkomapps.jadwalUjian.presenter.presenterRiwayat;

import java.util.List;

import skripsi.edwin.filkomapps.model.JadwalUjian;

public class ResultSearchRiwayatContract {
    public interface View{
        void showProgress();
        void dismisProgress();
        void showMessage(String message);
        void setJadwal(List<JadwalUjian> jadwalUjian);
        void setEmptyView();
    }
    public  interface Presenter{

        void getJadwal(List<String> peran, String search, String s);
    }
}
