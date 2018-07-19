package skripsi.edwin.filkomapps.verifikasihKehadiran.presenter;

import java.util.HashMap;
import java.util.List;

import skripsi.edwin.filkomapps.model.Dosen;

public class DialogContract {
    public  interface  View{
        void showListDosen(List<Dosen>dosenList);
        void showMessageError(String message);
        void showProgress();
        void dismissProgress();

    }
    public interface Presenter {
        void getListDosen(List<HashMap<String,String>> dataDosen);

    }
}
