package skripsi.edwin.filkomapps.jadwalUjian.presenter.presenterJadwalTerbaru;

import java.util.List;

import skripsi.edwin.filkomapps.model.JadwalUjian;

public class ListJadwalContract {
    public interface View{
        void showProgress();
        void dismisProgress();
        void showMessage(String message);
        void setFilterJadwal(List<JadwalUjian> jadwalUjian);
        void setJadwal(List<JadwalUjian> jadwalUjian);
        void setEmptyView();
    }
    public  interface Presenter{
        void getJadwal();
        void filterJadwal(List<String> peran, String s);
    }
}
