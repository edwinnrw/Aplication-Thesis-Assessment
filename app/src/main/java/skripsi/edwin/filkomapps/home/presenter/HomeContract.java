package skripsi.edwin.filkomapps.home.presenter;

import java.util.List;

import skripsi.edwin.filkomapps.model.JadwalUjian;

public class HomeContract {
    public interface View{
        void setJadwal(List<JadwalUjian> jadwal);
        void showProress();
        void dismisProgrss();
        void showMessage(String message);
        void showBadge();
        void hideBadge();
        void setEmptyJadwal();
    }
    public  interface Presenter{
        void getJadwal();
        void isNotif();

    }
}
