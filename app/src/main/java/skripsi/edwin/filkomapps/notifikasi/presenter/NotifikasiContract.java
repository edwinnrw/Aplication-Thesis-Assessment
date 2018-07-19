package skripsi.edwin.filkomapps.notifikasi.presenter;

import java.util.List;

import skripsi.edwin.filkomapps.model.orm.Notifikasi;

public class NotifikasiContract {
    public interface View{
        void setNotifikasi(List<Notifikasi>notifikasi);
    }
    public interface Presenter{
        void getNotif();
    }
}
